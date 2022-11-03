package com.ftn.e2.isa.blood_simple.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.e2.isa.blood_simple.model.User;

@Repository
public interface UserRepository extends JpaRepository<com.ftn.e2.isa.blood_simple.model.User, String>{
	
	@Query("select U "
			+ "from User U "
			+ "where ( "
			+ "  U.role = 'MEDICAL_ADMIN' "
			+ "  and U.id not in ( "
			+ "    select admin "
			+ "    from MedicalCenter "
			+ "  )"
			+ ")")
	public List<User> getFreeAdmins();
}
