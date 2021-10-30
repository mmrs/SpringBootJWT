package com.mmrs.mrt.recharge.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mmrs.mrt.recharge.entity.MPUser;
import com.mmrs.mrt.recharge.model.FunctionResult;
import com.mmrs.mrt.recharge.model.LoginUser;
import com.mmrs.mrt.recharge.repository.MPUserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	MPUserRepository mpUserRepository;
	@Autowired
	EntityManager entityManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<MPUser> userEntity = loadUserDetailsyUsername(username);
		UserDetails userDetails = userEntity.map(LoginUser::new).get();
		return userDetails;
	}

	public Optional<MPUser> loadUserDetailsyUsername(String username) throws UsernameNotFoundException {
		Optional<MPUser> userEntity = mpUserRepository.findByUserName(username);
		userEntity.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + username));
		return userEntity;
	}

	@Override
	public Optional<MPUser> findByUserName(String userName) {
		return mpUserRepository.findByUserName(userName);
	}

	@Override
	public FunctionResult getDataByProcedure(String userName) {

		StoredProcedureQuery procedureQuery = entityManager.createStoredProcedureQuery("TEST_PROCEDURE");

		procedureQuery.registerStoredProcedureParameter("USER_NAME", String.class, ParameterMode.INOUT);
		procedureQuery.registerStoredProcedureParameter("SECRET_KEY", String.class, ParameterMode.OUT);

		procedureQuery.setParameter("USER_NAME", userName);

		FunctionResult functionResult = new FunctionResult();
		functionResult.setData("SECRET_KEY", procedureQuery.getOutputParameterValue("SECRET_KEY")).setData("USER_NAME",
				procedureQuery.getOutputParameterValue("USER_NAME"));

		return functionResult;
	}
}
