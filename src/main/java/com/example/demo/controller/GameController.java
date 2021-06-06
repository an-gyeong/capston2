package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.GameOneService;
import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;



import java.util.List;
import com.example.demo.vo.GameOne;
import com.example.demo.vo.IdCheck;

@Controller
public class GameController {
    @Autowired
    MemberService memberService;
    
    @Autowired
    GameOneService gameOneService;
    
    
    Logger log = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/game/insertMember")
    public String test(Member member) {
    	
    	System.out.println(member);
    	memberService.createMember(member);
    	
    	return "game/main";
    }
    
    @GetMapping("/game/game1") //
    public String dispGame(){
        return  "game/game1";
    }
    @PostMapping("/game/game1")
    public String insertRanking(GameOne gameOne) {
    	System.out.println(gameOne);
    	gameOneService.createRanking(gameOne);
    	
    	return "redirect:/game/main";
    	
    }

   /* @GetMapping("/game/join")//회원가입
    public String dispJoin(Member member) {
    	return  "game/join"; 
    }*/

    @GetMapping("/game/main") //메인
    public String dispMain(){return  "game/main"; }

    @GetMapping("/game/ra") //랭킹
    public String dispRa(){
        return  "game/ra";
    }

    @GetMapping("/game/ranking") //랭킹
    public String dispRanking(Model model){
    	List<GameOne> list = gameOneService.getReadRanking();
    	model.addAttribute("rankingList",list);
    	
    	for (GameOne b : list) {
    		System.out.println(b);
    	}
    	
    	
        return  "game/ranking";
    }

  // 회원가입 페이지
    @GetMapping("/game/signup")
    public String dispSignup() {
        return "game/signup";
    }

    // 회원가입 처리
    @PostMapping("/game/signup")
    public String execSignup(Member member) {
    	memberService.createMember(member);

        return "redirect:/game/main";
    }
    
    // 아이디 중복검사
    @ResponseBody
    @PostMapping("/signup/idcheck")
    public Map<String, Object> checkIdWithAjax(@RequestBody IdCheck idCheck) {
    	System.out.println(idCheck);
		Map<String, Object> response = new HashMap<>();
		
		if (memberService.isIdDuplicated(idCheck.getId())) {
			response.put("duplicate", true);
		} else {
			response.put("success", true);
		}
		
		return response; // JSON
    }
    
	// LOGIN SUCCESS	
	@RequestMapping("/loginSuccess")
	public String loginSuccess() {
		return "redirect:/game/main";
	}

	// LOGIN Fail	
	@GetMapping("/loginFail")
	@ResponseBody
	public String loginFail() {
		return "로그인에 실패하였습니다.";
	}
    
    // 로그아웃 결과 페이지
    @GetMapping("/logout/result")
    public String dispLogout() {
        return "redirect:/game/main";
    }
}
