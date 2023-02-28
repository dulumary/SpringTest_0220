package com.marondal.spring.test.jsp.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.marondal.spring.test.jsp.model.Seller;

@Repository
public interface SellerDAO {
	
	public int insertSeller(
			@Param("nickname") String nickname
			, @Param("profileUrl") String profileUrl
			, @Param("temperature") double temperature);

	public Seller selectLastSeller();
}
