package com.marondal.spring.test.jstl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marondal.spring.test.jstl.bo.WeatherBO;
import com.marondal.spring.test.jstl.model.Weather;

@Controller
@RequestMapping("/jstl/weather")
public class WeatherController {
	
	@Autowired
	private WeatherBO weatherBO;

	@GetMapping("/list")
	public String list(Model model) {
		
		// 날씨 정보 테이블의 데이터를 조회하고, 조회된 데이터를 model 객체에 추가 한다. 
		List<Weather> weatherHistory = weatherBO.getWeatherHistory();
		
		model.addAttribute("weatherHistory", weatherHistory);
		
		return "jstl/weather/list";
	}
	
	@GetMapping("/input")
	public String input() {
		return "jstl/weather/input";
	}
	
	@GetMapping("/add")
	@ResponseBody
	public String addWeather(
			@DateTimeFormat(pattern="yyyy년 MM월 dd일")
			@RequestParam("date") Date date
			, @RequestParam("weather") String weather
			, @RequestParam("temperatures") double temperatures
			, @RequestParam("precipitation") double precipitation
			, @RequestParam("microDust") String microDust
			, @RequestParam("windSpeed") double windSpeed) {
		
		int count = weatherBO.addWeather(date, weather, temperatures, precipitation, microDust, windSpeed);
		
		
		return "삽입결과 : " + count;
		
	}
	
	
	
	
	
	
	
}
