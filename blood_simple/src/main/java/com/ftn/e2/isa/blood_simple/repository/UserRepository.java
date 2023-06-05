package com.ftn.e2.isa.blood_simple.repository;

import com.ftn.e2.isa.blood_simple.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<com.ftn.e2.isa.blood_simple.model.User, Long> {

    User findByEmail(String email);

    User findByPersonalId(String personalId);

    User findByVerificationCode(String verificationCode);

    boolean existsByEmail(String email);

    boolean existsByPersonalId(String personalId);

    void deleteByPersonalId(String personalId);


//	@Query("select U "
//			+ "from User U "
//			+ "where ( "
//			+ "  U.role = 'MEDICAL_ADMIN' "
//			+ "  and U not in ( "
//			+ "    select M.medicalAdmins "
//			+ "    from MedicalCenter M"
//			+ "  )"
//			+ ")")
//	public List<User> getFreeAdmins();

    @Query("select U "
            + "from User U "
            + "where ( "
            + "  U.role = 'USER' " //zameni na user posle
            + ")")
	List<User> getUsers();

    @Query("select U "
            + "from User U "
            + "where ( "
            + "  U.role = 'SYSTEM_ADMIN' " //zameni na user posle
            + ")")
	List<User> getAllSystemAdmins();

    @Query("select U "
            + "from User U "
            + "where ( "
            + "  U.role = 'MEDICAL_ADMIN' " //zameni na user posle
            + ")")
	Set<User> getAllMedicalAdmins();



//    @Query("select u "
//            + "from User u join Appointment a on u.id = a.user_id "
//            + "where a.medical_center = ?1")
//    Set<User> findUsersWithBloodDonations(Long id);
}
