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
	public String sellerInfo(@RequestParam(value="id", required=false) Integer id, Model model) {
		
		
		Seller seller = null;
		// id 파라미터가 있으면 id로 조회한 결과 보여주기
		if(id != null) {
			seller = sellerBO.getSeller(id);

		} else {// id 파라미터가 없으면 최근 판매자 결과 보여주기
			seller = sellerBO.getLastSeller();
		}
		
		model.addAttribute("seller", seller);
		
		return "jsp/sellerInfo";
	}
	
	
	
	
	
	
	
	
	

}
