package com.cos.photogramstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	// 1. 패스워드는 알아서 스프링부트가 체킹해주므로 내가 신경쓰지않아도된다
	// 2. 리턴이 잘되면 자동으로 세션을 생성해준다
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User userEntity = userRepository.findByUsername(username);
		if(userEntity ==null) {
			return null;
		}else {
			return new PrincipalDetails(userEntity); //UserDetails로 리턴해야되므로 내가 해당클래스를 상속한 클래스를 만들어서 리턴해준다
		}
	}

}
