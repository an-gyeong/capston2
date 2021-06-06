package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Member;

// src/main/resources/mappers�� ����
@Mapper
public interface MemberMapper {
	Member readAccount(String id);
	void createMember(Member member);
	//List<String> readAuthorites(String id); //check
	
	void failCntUpdate(String id); // 실패횟수 update
	Member getFailCnt(String id); // 실패횟수, isEnabled 조회
	void changeEnabled(String id); // 계정활성화여부 (1이면 0, 0이면 1로)
	void resetFailCnt(String id);  // 실패횟수 초기화
	
	List<Member> getAllMember(); // 모든 유저 가져오기 (리스트)\
	//탈퇴
	//void deleteUserSelf(IdAndPassword idAndPassword);
}
