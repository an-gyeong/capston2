package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.GameOne;

@Mapper
public interface GameOneMapper {
	void createRanking(GameOne gameone);
	List<GameOne> getReadRanking();
}
