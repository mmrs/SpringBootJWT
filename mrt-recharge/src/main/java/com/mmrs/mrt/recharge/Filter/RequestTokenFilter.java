package com.mmrs.mrt.recharge.Filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mmrs.mrt.recharge.entity.MPUser;
import com.mmrs.mrt.recharge.model.LoginUser;
import com.mmrs.mrt.recharge.service.UserService;
import com.mmrs.mrt.recharge.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestTokenFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		log.info("Servlet Path: " + request.getServletPath());

		String userName = null;
		String token = null;
		final String authorizationHeader = request.getHeader("Authorization");
		if (StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
			userName = jwtUtil.extractUserName(token);
		}
		if (StringUtils.isNotBlank(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {

			Optional<MPUser> userEntity = userService.loadUserDetailsyUsername(userName);

			if (userEntity.isPresent()) {
				if (jwtUtil.validateToken(token, userEntity.get())) {
					UserDetails userDetails = userEntity.map(LoginUser::new).get();
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		}

		filterChain.doFilter(request, response);
	}
}
