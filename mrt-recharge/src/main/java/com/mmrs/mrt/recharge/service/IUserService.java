package com.mmrs.mrt.recharge.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mmrs.mrt.recharge.entity.MPUser;
import com.mmrs.mrt.recharge.model.FunctionResult;

public interface IUserService extends UserDetailsService {

	public Optional<MPUser> loadUserDetailsyUsername(String username) throws UsernameNotFoundException;

	public Optional<MPUser> findByUserName(String userName);
	
	public FunctionResult getDataByProcedure(String userName);
}
