package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.MemberMapper;
import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Member;


@Service
public class MemberService implements UserDetailsService {
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	MemberRepository memberRepository;
	
    Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Transactional
	public String createMember(Member member) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		
		return memberRepository.createMember(member).getId();
		
	}
	
	// 아이디 중복검사
	public boolean isIdDuplicated(String id) {
		if (memberRepository.findById(id) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		log.info("## loadUserByUsername ##");
		
		Member member = memberRepository.findById(username);
		
		if( member == null ) {
			log.debug("## 계정정보가 존재하지 않습니다. ##");
			throw new UsernameNotFoundException(username);
		}
		
		//member.setAuthorities(getAuthorities(username));
		return member;
	}
		

	// 사용자 탈퇴
		
	//public void deleteUserSelf(IdAndPassword idAndPassword) {
			
}
