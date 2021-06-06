package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.mapper.MemberMapper;
import com.example.demo.vo.Member;

@Repository
public class MemberRepository{
	@Autowired
	MemberMapper memberMapper;

	public Member findById(String username) {
		return memberMapper.readAccount(username);
	}
	
	public Member createMember(Member member) {
		memberMapper.createMember(member);
		
		return member;
	}
	
	public Member getUserInfo(String username) {
		Member account = memberMapper.getFailCnt(username);
		return account;
	}
	
	public void loginFailCnt(String username) {
		memberMapper.failCntUpdate(username);
	}
	
	public void changeEnabled(String username) {
		memberMapper.changeEnabled(username);
	}
	
	public void resetFailCnt(String username) {
		memberMapper.resetFailCnt(username);
	}
	
	public List<Member> getAllAccount() {
		return (List<Member>) memberMapper.getAllMember();
	}
}