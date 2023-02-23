package com.marondal.spring.test.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marondal.spring.test.mybatis.bo.RealEstateBO;
import com.marondal.spring.test.mybatis.model.RealEstate;

@Controller
public class RealEstateController {
	
	@Autowired
	private RealEstateBO realEstateBO;
	
	// 전달받은 id와 일치하는 데이터 json으로 response 추가
	@RequestMapping("/mybatis/test01")
	@ResponseBody
	public RealEstate realEstate(@RequestParam("id") int id) {
		
		RealEstate realEstate = realEstateBO.getRealEstate(id);
		
		
		return realEstate;
		
	}

}
