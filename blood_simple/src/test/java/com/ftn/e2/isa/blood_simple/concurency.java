package com.ftn.e2.isa.blood_simple;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.e2.isa.blood_simple.model.Appointment;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.repository.AppointmentRepository;
import com.ftn.e2.isa.blood_simple.repository.UserRepository;
import com.ftn.e2.isa.blood_simple.service.*;
import org.springframework.dao.PessimisticLockingFailureException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class concurency {
	
	
    @Autowired
	private QrService service;
    @Autowired
    private AppointmentRepository repo;
    @Autowired
    private UserRepository userRepo;
    
    
	@Before
	public void setUp() throws Exception {
		}

	@Test(expected = PessimisticLockingFailureException.class)	//expected = PessimisticLockingFailureException.class
	public void testOptimisticLockingScenario() throws Throwable {	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    Date parsedDate = dateFormat.parse("2022-11-26 19:00:00");
	    ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 1");
		        Appointment a = service.findAppointmentByContentData("35445421760011", "2022-11-26 19:00:00") ;// izvrsavanje transakcione metode traje oko 200 milisekundi
		        System.out.println(" Thread 1 iscitao: " + a);

			}
		});
		Future<?> future2 = executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 2");

	        try { Thread.sleep(20); } catch (InterruptedException e) { }// otprilike 150 milisekundi posle prvog threada krece da se izvrsava drugi
		        /*
		         * Drugi thread pokusava da izvrsi transakcionu metodu findOneById dok se prvo izvrsavanje iz prvog threada jos nije zavrsilo.
		         * Metoda je oznacena sa NO_WAIT, sto znaci da drugi thread nece cekati da prvi thread zavrsi sa izvrsavanjem metode vec ce odmah dobiti PessimisticLockingFailureException uz poruke u logu:
		         * [pool-1-thread-2] o.h.engine.jdbc.spi.SqlExceptionHelper : SQL Error: 0, SQLState: 55P03
		         * [pool-1-thread-2] o.h.engine.jdbc.spi.SqlExceptionHelper : ERROR: could not obtain lock on row in relation "product"
		         * Prema Postgres dokumentaciji https://www.postgresql.org/docs/9.3/errcodes-appendix.html, kod 55P03 oznacava lock_not_available
		         */
		        Appointment a1 = service.findAppointmentByContentData("35445421760011", "2022-11-26 19:00:00") ;// izvrsavanje transakcione metode traje oko 200 milisekundi
		        System.out.println(" Thread 2 iscitao: " + a1);

			}
		});
		try {
		    future2.get(); // podize ExecutionException za bilo koji izuzetak iz drugog child threada
		} catch (ExecutionException e) {
		    System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas PessimisticLockingFailureException
		    throw e.getCause();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}

}