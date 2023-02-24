package com.marondal.spring.test.mybatis.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.spring.test.mybatis.dao.RealEstateDAO;
import com.marondal.spring.test.mybatis.model.RealEstate;

@Service
public class RealEstateBO {
	
	@Autowired
	private RealEstateDAO realEstateDAO;
	
	// 전달 받은 id와 일치하는 데이터 조회 리턴
	public RealEstate getRealEstate(int id) {
		
		RealEstate realEstate = realEstateDAO.selectRealEstate(id);
		
		return realEstate;
		
	}
	
	// 전달받은 월세 금액보다 낮은 매물 정보들 리턴
	public List<RealEstate> getRealEstateListByRentPrice(int rentPrice) {
		List<RealEstate> realEstateList = realEstateDAO.selectRealEstateListByRentPrice(rentPrice);
		return realEstateList;
	}
	
	
	public List<RealEstate> getRealEstateListByAreaPrice(int area, int price) {
		
		 return realEstateDAO.selectRealEstateListByAreaPrice(area, price);
	}
	
	public int addRealEstateByObject(RealEstate realEstate) {
		
		return realEstateDAO.insertRealEstateByObject(realEstate);
	}
	
	public int addRealEstate(int realtorId, String address, int area, String type, int price, int rentPrice) {
		return realEstateDAO.insertRealEstate(realtorId, address, area, type, price, rentPrice);
	}
	

}
