package com.dbbl.mrt.recharge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dbbl.mrt.recharge.entity.MPUser;
import com.dbbl.mrt.recharge.model.LoginUser;
import com.dbbl.mrt.recharge.repository.MPUserRepository;
import com.dbbl.mrt.recharge.repository.ProcedureCallerRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	MPUserRepository mpUserRepository;
	@Autowired
	ProcedureCallerRepository procedureCallerRepository;

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
	public Object getDataByProcedure(String userName) {
		return procedureCallerRepository.getData(userName);
	}
}
