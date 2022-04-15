package com.campus.myapp.controller;


import java.util.List;

import javax.inject.Inject;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;

import com.campus.myapp.service.MapService;
import com.campus.myapp.service.ReviewService;
import com.campus.myapp.vo.ReviewVO;
import com.campus.myapp.vo.StoreVO;

@RestController
@RequestMapping("/map/")
public class MapController {
   @Inject
   MapService service;
   @Inject
   ReviewService rvservice;


   @GetMapping("main_map")
public ModelAndView Main_map(String fname, String id) {
    //DB���� ���ĸ޴� �������� ==> ���� 
    ModelAndView mav = new ModelAndView();      
    if(id!=null && !id.trim().equals("")) {
      List<ReviewVO> list = rvservice.reviewList(id);
      mav.addObject("list",list);
    }
    mav.addObject("menu", fname);
    mav.addObject("placeid","id");
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
   //���� ���� ���� ���
 	@RequestMapping("reviewListAll")
 	public List<ReviewVO> memberListAll(ReviewVO vo) {
 		return service.reviewListAll(vo);
 	}
 	//���� ����
 	@GetMapping("reviewDeleteOk")
 	public int reviewDeleteOk(int reviewno) {
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