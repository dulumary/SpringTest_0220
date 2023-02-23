package com.marondal.spring.test.mybatis.bo;

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

}
