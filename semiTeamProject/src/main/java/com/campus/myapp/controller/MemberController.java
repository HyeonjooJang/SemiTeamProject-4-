package com.campus.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;

import com.campus.myapp.service.MemberService;
import com.campus.myapp.vo.MemberVO;
@RestController
@RequestMapping("/member/")
public class MemberController {
	@Inject
	MemberService service;

	// �α���â���� �̵�
	@GetMapping("login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login");
		return mav;
	}
	//ȸ���������� �̵�
	@GetMapping("memberForm")
	public ModelAndView memberForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/memberForm");
		return mav;

	}
	/*
	//ȸ���������� �������� �̵�
	@GetMapping("memberEdit")
	public ModelAndView memberEdit() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/memberEdit");
		return mav;
	}
	*/
	
	// ȸ�����
	@PostMapping("memberOk")
	public ModelAndView memberFormOk(MemberVO vo) {
		ModelAndView mav = new ModelAndView();
		
		int cnt = service.memberInsert(vo);

		mav.addObject("cnt", cnt);
		mav.setViewName("member/memberResult");
		return mav;
	}
	//�α���
	@PostMapping("loginOk")
	public ModelAndView loginOk(MemberVO vo, HttpSession session) {
		
		MemberVO vo2 = service.loginCheck(vo);

		ModelAndView mav = new ModelAndView();
		// �α��� ������ ���ǿ� id�� �̸� ����
		if (vo2 != null) {// �α��� ����
			session.setAttribute("logId", vo2.getUserid());
			session.setAttribute("logSex", vo2.getSex());
			session.setAttribute("logImg", vo2.getProfile());
			session.setAttribute("logStatus", "Y");
			mav.setViewName("redirect:/");// '/' <- Ȩ���� �̵�
		} else {// �α��� ����
			mav.setViewName("redirect:login");
		}
		return mav;
	}
	// �α׾ƿ�
	@GetMapping("logout")
	public ModelAndView logout(HttpSession session) {
	
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
	// ���̵� �ߺ��˻�
	@PostMapping("memberIdCheck")
	public int inCheck(String userid) {
		int cnt = service.idCheck(userid);// �ߺ��̸�1 �ƴ�0
		return cnt;
	}
}
