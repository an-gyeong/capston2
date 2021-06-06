package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.mapper.GameOneMapper;
import com.example.demo.vo.GameOne;


@Repository
public class GameOneRepository {
	@Autowired
	GameOneMapper gameOneMapper;
	public GameOne createRanking(GameOne gameOne) {
		gameOneMapper.createRanking(gameOne);
		return gameOne;
	}
	
	public List<GameOne> getReadRanking() {
		return (List<GameOne>) gameOneMapper.getReadRanking();
	}
}
