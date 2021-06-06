package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.GameOneRepository;
import com.example.demo.vo.GameOne;
import com.example.demo.vo.Member;
@Service
public class GameOneService {
	
	@Autowired
	GameOneRepository GameOneRepository;
	
	@Transactional
	public String createRanking(GameOne gameOne) {
		
		return GameOneRepository.createRanking(gameOne).getID();
		
	}
	
	public List<GameOne> getReadRanking(){
		return GameOneRepository.getReadRanking();
	}

}
