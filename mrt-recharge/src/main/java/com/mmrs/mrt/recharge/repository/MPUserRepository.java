package com.mmrs.mrt.recharge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmrs.mrt.recharge.entity.MPUser;

@Repository
public interface MPUserRepository extends JpaRepository<MPUser, String> {

	Optional<MPUser> findByUserName(String userName);

//	CALL TEST_PROCEDURE(:USER_NAME);
//	@Query(value = "{CALL TEST_PROCEDURE(:USER_NAME,:SECRET_KEY)}", nativeQuery = true)
//	Object getDataByProcedure(@Param(value = "USER_NAME") String userName);

}
