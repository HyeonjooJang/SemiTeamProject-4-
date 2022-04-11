package com.campus.myapp.controller;

import java.nio.charset.Charset;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.campus.myapp.service.ReviewService;
import com.campus.myapp.vo.PagingVO;
import com.campus.myapp.vo.ReviewVO;

@Controller
public class ReviewController {
	@Inject
	ReviewService service;
	
	// �۸��
	@GetMapping("/")
	public ModelAndView reviewList(PagingVO pVO) {
		ModelAndView mav = new ModelAndView();
		
		// �� ���ڵ� ��
		pVO.setTotalRecord(service.totalRecord(pVO));
		
		// DBó��
		mav.addObject("list", service.reviewList(pVO));
		mav.addObject("pVO", pVO);
		
		mav.setViewName("/"); // 
		return mav;
	}
	
	// �۵�� ��
	@GetMapping("/")
	public ModelAndView reviewWrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/");
		return mav;
	}
	
	// �۵��
	@PostMapping("/")
	public ResponseEntity<String> boardWriteOk(ReviewVO vo, HttpServletRequest request) {
		
		vo.setIp(request.getRemoteAddr()); // ������ ������
		vo.setUserid((String)request.getSession().getAttribute("logId")); // �۾���-session�α��� ���̵� ���Ѵ�.
		
		ResponseEntity<String> entity = null; // �����Ϳ� ó�� ���¸� ������.
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type","text/html; charset=utf-8");
		//headers.setContentType(new MediaType("text","html", Charset.forName("UTF-8")));
		
		try {
			service.ReviewInsert(vo);
			// ������
			String msg = "<script>";
			msg += "alert('���䰡 ��ϵǾ����ϴ�.');";
			msg += "location.href='/myapp/board/boardList';";
			msg += "</script>";
			
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK); // ������: 200
		}catch(Exception e) {
			e.printStackTrace();
			// ��Ͼȵ�..
			String msg = "<script>";
			msg += "alert('�������� �����Ͽ����ϴ�.');";
			msg += "history.back();";
			msg += "</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		}
		
		return entity;
	}
	
	// �� ���� ����
	@GetMapping("/")
	public ModelAndView reviewView(int reviewno) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo", service.reviewSelect(reviewno)); // db���� select�� ������ ������
		mav.setViewName("/");
		
		return mav;		
	}
	
	// �� ���� ��
	@GetMapping("/")
	public ModelAndView boardEdit(int no) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo", service.reviewSelect(no));
		mav.setViewName("board/boardEdit");
		
		return mav;		
	}
	
	// �� ���� (DB)
	@PostMapping("/")
	public ResponseEntity<String> reviewEditOk(ReviewVO vo, HttpSession session) {
		ResponseEntity<String> entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text","html", Charset.forName("UTF-8")));
		
		vo.setUserid((String)session.getAttribute("logId"));
		
		try {
			
			int result = service.reviewUpdate(vo);
			if(result>0) {// ��������
				entity = new ResponseEntity<String>(getEditSuccessMessage(vo.getNo()), headers, HttpStatus.OK);
			}else { // ��������
				entity = new ResponseEntity<String>(getEditFailMessage(), headers, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			e.printStackTrace();
			// ��������
			entity = new ResponseEntity<String>(getEditFailMessage(), headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	// �� ����
	@GetMapping("/")
	public ModelAndView reviewDel(int no, HttpSession session) {
		String userid = (String)session.getAttribute("logId");
		
		int reviewno = 0;
		int result = service.reviewDelete(reviewno, userid);
		
		ModelAndView mav = new ModelAndView();
		if(result>0) { // ����
			mav.setViewName("redirect:reviewList"); // list�� �̵��� ��Ʈ�ѷ��� ȣ��
		}else { // �����ȵ�
			mav.addObject("no", no);
			mav.setViewName("redirect:reviewView");
		}
		return mav;
	}
	
	// ���� ���� �޼���
	public String getEditFailMessage() {
		String msg = "<script>";
		msg += "alert('���� ���� �����Ͽ����ϴ�.');";
		msg += "history.back();";
		msg += "</script>";
		return msg;
	}
	
	public String getEditSuccessMessage(int reviewno) {
		String msg = "<script>";
		msg += "alert('���� �����Ͽ����ϴ�.\\n���� ������� �̵��մϴ�.');";
		msg += "location.href='/myapp/map/reviewView?reviewno="+reviewno+"'";
		msg += "</script>";
		return msg;
	}
	
}
