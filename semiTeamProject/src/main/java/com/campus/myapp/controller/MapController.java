package com.campus.myapp.controller;


import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD

=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
>>>>>>> 55ac794671c6b48b32c52a077644482e1e70a20a
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;

import com.campus.myapp.service.MapService;

import com.campus.myapp.vo.ReviewVO;
import com.campus.myapp.vo.StoreVO;

@RestController
@RequestMapping("/map/")
public class MapController {
	@Inject
	MapService service;
	
	@GetMapping("main_map")
	public ModelAndView Main_map(String fname) {
		//DB���� ���ĸ޴� �������� ==> ���� 
<<<<<<< HEAD
		ModelAndView mav = new ModelAndView();

		//String fname = service.getfname();

		mav.addObject("menu",fname);
		mav.addObject("placeid","p1");
=======
		ModelAndView mav = new ModelAndView();		 
		mav.addObject("menu", fname);
		mav.addObject("placeid","id");
>>>>>>> 55ac794671c6b48b32c52a077644482e1e70a20a
		mav.setViewName("map/map");
		return mav;
	}
	
	//���� ������������ �̵�
	@GetMapping("myReview")
	public ModelAndView myReview() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("map/myReview");
		return mav;
	}
	
	//���� ���� ���� ���� ���
	@RequestMapping("myReviewListAll")
	public List<ReviewVO> myReviewListAll(String userid) {
		return service.myReviewListAll(userid);
	}
	//���� ���� ����
	@GetMapping("myreviewDeleteOk")
	public int myreviewDeleteOk(int reviewno) {
		return service.myreviewDeleteOk(reviewno);
	}
	
	//�������� 
	@PostMapping("addplace")
	public int addplace(@RequestBody StoreVO[] places) {
		int n=0;
		try {
			for(StoreVO vo:places) {
				n+=service.addplace(vo);
			}
		}catch(Exception e) {
			
		}
			//System.out.println(vo);
		return n; 
	}
}