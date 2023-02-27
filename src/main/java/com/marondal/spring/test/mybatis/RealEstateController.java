package com.marondal.spring.test.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marondal.spring.test.mybatis.bo.RealEstateBO;
import com.marondal.spring.test.mybatis.model.RealEstate;

@Controller
@RequestMapping("/mybatis")
public class RealEstateController {
	
	@Autowired
	private RealEstateBO realEstateBO;
	
	// 전달받은 id와 일치하는 데이터 json으로 response 추가
	@RequestMapping("/test01/1")
	@ResponseBody
	public RealEstate realEstate(@RequestParam("id") int id) {
		
		RealEstate realEstate = realEstateBO.getRealEstate(id);
		
		
		return realEstate;
		
	}
	
	// 전달받은 월세 금액보다 낮은 매물 정보를 json으로 response 담기
	@RequestMapping("/test01/2")
	@ResponseBody
	public List<RealEstate> realEstateByRentPrice(@RequestParam("rent") int rentPrice) {
		
		List<RealEstate> realEstateList = realEstateBO.getRealEstateListByRentPrice(rentPrice);
		
		return realEstateList;
	}
	
	// 가격과 면적을 전달 받고, 조건에 맞는 정보 json으로 response에 담는다
	@RequestMapping("/test01/3")
	@ResponseBody
	public List<RealEstate> realEstateByAreaPrice(
			@RequestParam("area") int area
			, @RequestParam("price") int price) {
		
		List<RealEstate> realEstate = realEstateBO.getRealEstateListByAreaPrice(area, price);
		
		return realEstate;
	}
	
	@RequestMapping("/test02/1")
	@ResponseBody
	public String addRealEstateByObject() {
		
		RealEstate realEstate = new RealEstate();
		realEstate.setRealtorId(3);
		realEstate.setAddress("푸르지용 리버 303동 1104호");
		realEstate.setArea(89);
		realEstate.setType("매매");
		realEstate.setPrice(100000);
		
		int count = realEstateBO.addRealEstateByObject(realEstate);
		
		return "삽입결과 : " + count;
		
		
	}
	
	@RequestMapping("/test02/2")
	@ResponseBody
	public String addRealEstate(@RequestParam("realtorId") int realtorId) {
		
		int count = realEstateBO.addRealEstate(realtorId, "썅떼빌리버 오피스텔 814호", 45, "월세", 100000, 120);
		
		
		return "삽입결과 : " + count;
	}
	
	@RequestMapping("/test03")
	@ResponseBody
	public String updateRealEstate() {
		// id가 22인 매물의 타입을 전세로 바꾸고 가격을 70000 으로 변경한다. 
		int count = realEstateBO.updateRealEstate(22, "전세", 70000);
		
		return "수정 결과 : " + count;
		
	}
	
	@RequestMapping("/test04")
	@ResponseBody
	public String deleteRealEstate(@RequestParam("id") int id) {
		
		int count = realEstateBO.deleteRealEstate(id);
		
		return "삭제 결과 : " + count;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
