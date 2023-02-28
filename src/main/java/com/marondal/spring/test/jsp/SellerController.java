package com.marondal.spring.test.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marondal.spring.test.jsp.bo.SellerBO;
import com.marondal.spring.test.jsp.model.Seller;

@Controller
@RequestMapping("/jsp/seller")
public class SellerController {
	
	@Autowired
	private SellerBO sellerBO;
	
	@PostMapping("/add")
	@ResponseBody
	public String addSeller(
			@RequestParam("nickname") String nickname
			, @RequestParam("profileUrl") String profileUrl
			, @RequestParam("temperature") double temperature) {
		
		int count = sellerBO.addSeller(nickname, profileUrl, temperature);
		
		return "삽입결과 : " + count;	
	}
	
	@GetMapping("/input")
	public String sellerInput() {
		return "jsp/sellerInput";
		
	}
	
	@GetMapping("/info")
	public String sellerInfo(Model model) {
		// 필요한 데이터 조회
		Seller seller = sellerBO.getLastSeller();
		
		model.addAttribute("seller", seller);
		
		return "jsp/sellerInfo";
	}
	
	
	
	
	
	
	
	
	

}
