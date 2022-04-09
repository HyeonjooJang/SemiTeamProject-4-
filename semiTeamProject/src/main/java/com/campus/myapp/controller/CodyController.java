package com.campus.myapp.controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.campus.myapp.service.CodyService;
import com.campus.myapp.vo.CodyVO;

@Controller
public class CodyController {

	@Inject
	CodyService service;

	// �ڵ� ����������
	@GetMapping("/cody/main_cody")
	public ModelAndView codyPage(int temp, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// String sex = (String)session.getAttribute("logSex");
		mav.addObject("temp", temp);
		// List<CodyVO> vo = service.codyRecommend(temp, "f");//�ӽ÷� ����
		// mav.addObject("vo", vo);
		mav.setViewName("cody/main_cody");
		return mav;
	}

	// �ڵ� ������ ������
	@GetMapping("/master/master_cody")
	public String masterPage() {
		return "master/master_cody";
	}

	// cname �ߺ�Ȯ��
	@PostMapping("/cnameCheck")
	@ResponseBody
	public Integer userpwdOk(String cname) {
		return service.cnameCheck(cname);
	}
	
	// codyInsert
	@PostMapping("/codyInsert")
	@ResponseBody
	public int codyInsert(CodyVO vo, HttpServletRequest request, MultipartHttpServletRequest mr) {
		// ���� ���ε�
		mr = (MultipartHttpServletRequest) request;
		MultipartFile file = mr.getFile("filename");

		String path = request.getSession().getServletContext().getRealPath("/img/codyimg/codyupload");
		System.out.println(path);

		String filename = file.getOriginalFilename();
		File uploadFile = new File(path, filename);

		// ���� ���� ���ε�
		try {
			file.transferTo(uploadFile);
			System.out.println("���� ���ε� ����");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���� ���ε� ����");
		}

		return service.codyInsert(vo);
	}

	// ������������ �̵�
	@GetMapping("/cody/sub_cody")
	public String subPage() {
		return "cody/sub_cody";
	}

	// �ڵ� ���

}
