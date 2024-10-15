package com.example.board.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.board.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private User user;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> roleList = new ArrayList<>();
		
		roleList.add(()->{
			return "ROLE_" + user.getRole();
		});
		
		return null;
	}

	@Override
	public String getPassword() {

		
		return "{noop}" + user.getPassword();
		// "{noop}" = 암호화하지 않겠다 
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	//만료된 계정인지 리턴시켜주는 메서드(만료안됨 : true)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
		
	}

	//계정이 잠겼는지 리턴시켜주는 메서드 (안잠김 : true)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//자격증명 만료된건지 리턴 (오래된 비밀번호 변경 시키려는 팝업같은거)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정 비활성화 여부를 리턴 (휴면계정)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
