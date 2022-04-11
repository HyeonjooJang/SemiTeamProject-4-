package com.campus.myapp.controller;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/map/")
public class MapController {
	
	@GetMapping("main_map")
	public ModelAndView Main_map() {
		//DB���� ���ĸ޴� �������� ==> ���� 
		ModelAndView mav = new ModelAndView();
		// String menu = menuSerivce.getMenu()
		mav.addObject("menu","������");
		mav.setViewName("map/map");
		return mav;
	}
	
}
