package com.example.demo.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String pw;
	private String name;
	private String email;
	
	private int failCnt; // 로그인 실패 횟수
	private boolean isAccountNonExpired; // 1이면 만료되지 않은 계정
	private boolean isAccountNonLocked;  // 1이면 계정이 잠겨있지 않음
	private boolean isCredentialsNonExpired; // 1이면 만료되지 않은 자격 증명 계정
	private boolean isEnabled;   // 1이면 사용가능한 계정
	
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public String getUsername() {
		return this.id;
	}
	
	@Override
	public String getPassword() {
		return this.pw;
	}
	
	public void setPassword(String password) {
		this.pw = password;
	}
}
