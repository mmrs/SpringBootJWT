package com.dbbl.mrt.recharge.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dbbl.mrt.recharge.entity.MPUser;

public class LoginUser extends BaseModel implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String pdata;
	boolean enabled;
	private List<GrantedAuthority> authorities;

	public LoginUser(MPUser mpUser) {
		userName = mpUser.getUserName();
		pdata = mpUser.getPData();
		enabled = mpUser.getStatus() == 1;
		authorities = Arrays.stream(mpUser.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	public LoginUser() {
		super();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return pdata;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
