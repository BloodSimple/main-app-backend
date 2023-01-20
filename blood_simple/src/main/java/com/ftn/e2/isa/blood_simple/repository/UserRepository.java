package com.ftn.e2.isa.blood_simple.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.e2.isa.blood_simple.model.User;

@Repository
public interface UserRepository extends JpaRepository<com.ftn.e2.isa.blood_simple.model.User, Long>{

	public User findByEmail(String email);
	public User findByPersonalId(String personalId);
	public User findByVerificationCode(String verificationCode);

	public boolean existsByEmail(String email);
	public boolean existsByPersonalId(String personalId);

	public void deleteByPersonalId(String personalId);


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
	public List<User> getUsers();
	
	@Query("select U "
			+ "from User U "
			+ "where ( "
			+ "  U.role = 'SYSTEM_ADMIN' " //zameni na user posle
			+ ")")
	public List<User> getAllSystemAdmins();

	@Query("select U "
			+ "from User U "
			+ "where ( "
			+ "  U.role = 'MEDICAL_ADMIN' " //zameni na user posle
			+ ")")
	public Set<User> getAllMedicalAdmins();
}
