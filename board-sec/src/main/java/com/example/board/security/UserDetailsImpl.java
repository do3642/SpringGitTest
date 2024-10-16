package com.example.board.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.board.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails, OAuth2User{
	
	private static final long serialVersionUID = 1L;
	private User user;
	
	//구글에서 조회한 유저 정보
	private Map<String, Object> attributes;
	
	//기본, 카카오 로그인 시 사용하는 생성자
	public UserDetailsImpl(User user) {
		this.user = user;
	}
	
	//OAuth2를 이용해서 로그인할 경우 사용할 생성자
	public UserDetailsImpl(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	//------------------------------
	
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

		
//		return "{noop}" + user.getPassword();
		// "{noop}" = 암호화하지 않겠다
		return  user.getPassword();
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

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
