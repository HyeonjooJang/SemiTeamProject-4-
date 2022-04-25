package com.campus.myapp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
import com.campus.myapp.vo.HeartVO;

@Controller
public class CodyController {

	@Inject
	CodyService service;

	// �ڵ� ����������
	@GetMapping("/cody/main_cody")
	public ModelAndView codyPage(int temp, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String sex = (String) session.getAttribute("logSex");
		//

		List<CodyVO> vo = service.codyRecommend(temp, sex); // �ӽ÷� ����
		Collections.shuffle(vo);// ����Ʈ ���� ����
		List<CodyVO> cvo = new ArrayList<CodyVO>();

		for (int i = 0; i < 5; i++) {
			cvo.add(vo.get(i));
		}

		mav.addObject("vo", cvo);

		//
		mav.setViewName("cody/main_cody");
		return mav;
	}

	// �ڵ� ������ ������(����)
	@GetMapping("/master/master_modify_cody")
	public ModelAndView masterModify() {
		ModelAndView mav = new ModelAndView();
		List<CodyVO> allVO = service.codyListAll();
		mav.addObject("vo", allVO);
		if (allVO.size() % 3 == 0) {
			mav.addObject("len", allVO.size() / 3);
		} else {
			mav.addObject("len", allVO.size() / 3 + 1);
		}
		mav.setViewName("master/master_modify_cody");
		return mav;
	}
	// �ڵ� ������ ������(����)
	@GetMapping("/master/masterGenderList")
	public ModelAndView masterGenderList(String sex) {
		ModelAndView mav = new ModelAndView();
		List<CodyVO> genderVO = service.codyGenderList(sex);
		mav.addObject("vo", genderVO);
		if (genderVO.size() % 3 == 0) {
			mav.addObject("len", genderVO.size() / 3);
		} else {
			mav.addObject("len", genderVO.size() / 3 + 1);
		}
		mav.setViewName("master/master_modify_cody");
		return mav;
	}

	// �ڵ� ������ ������(������)
	@GetMapping("/master/master_modify_form")
	public ModelAndView modifyForm(String cname) {
		ModelAndView mav = new ModelAndView();
		List<CodyVO> vo = service.codySelect(cname);
		mav.addObject("vo", vo);
		mav.setViewName("master/master_modify_form");
		return mav;
	}

	// �ڵ� ����
	@PostMapping("/codyUpdate")
	@ResponseBody
	public int codyUpdate(CodyVO vo) {
		return service.codyUpdate(vo);
	}

	// �ڵ� �߰� ������
	@GetMapping("/master/master_add_cody")
	public String masterAdd() {
		return "master/master_add_cody";
	}

	// cname �ߺ�Ȯ��
	@PostMapping("/cnameCheck")
	@ResponseBody
	public Integer userpwdOk(String cname) {
		return service.cnameCheck(cname);
	}

	// �ڵ� �߰�
	@PostMapping("/codyInsert")
	@ResponseBody
	public int codyInsert(CodyVO vo, HttpServletRequest request, MultipartHttpServletRequest mr) {
		// ���� ���ε�
		mr = (MultipartHttpServletRequest) request;
		MultipartFile file = mr.getFile("filename");

		String path = request.getSession().getServletContext().getRealPath("/img/codyimg/codyupload");
		//String path = "/img/codyimg/codyupload";
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

	// �ڵ� ����
	@GetMapping("/codyDelete")
	public String codyDelte(String cname, HttpServletRequest request) {
		System.out.println("��Ʈ�ѷ� ����");
		// ���� ����
		
		String path = request.getSession().getServletContext().getRealPath("/img/codyimg/codyupload");
		//String path = "/img/codyimg/codyupload";
		System.out.println(path);

		File file = new File(path, cname);

		// ���� ���� ���ε�
		try {
			file.delete();
			service.codyDelete(cname);
			System.out.println("���� ���� ����");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���� ���� ����");
		}
		
		return "redirect:/master/master_modify_cody"; 
	}

	// ����������(��ü����)
	@GetMapping("/codyListAll")
	public ModelAndView codyListAll(CodyVO vo) {
		ModelAndView mav = new ModelAndView();
		List<CodyVO> allVO = service.codyListAll();
		mav.addObject("vo", allVO);
		if (allVO.size() % 3 == 0) {
			mav.addObject("len", allVO.size() / 3);
		} else {
			mav.addObject("len", allVO.size() / 3 + 1);
		}
		mav.setViewName("cody/sub_cody");
		return mav;
	}

	// ����������(����)
	@GetMapping("/codyGenderList")
	public ModelAndView codyGenderList(CodyVO vo, String sex) {
		ModelAndView mav = new ModelAndView();
		List<CodyVO> genderVO = service.codyGenderList(sex);
		mav.addObject("vo", genderVO);
		if (genderVO.size() % 3 == 0) {
			mav.addObject("len", genderVO.size() / 3);
		} else {
			mav.addObject("len", genderVO.size() / 3 + 1);
		}
		mav.setViewName("cody/sub_cody");
		return mav;
	}

	// ����������(���ƿ�)
	@GetMapping("/codyHeartList")
	public ModelAndView codyGenderList(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String userid = (String) session.getAttribute("logId");
		List<HeartVO> heartVO = service.codyHeartList(userid);
		mav.addObject("hVO", heartVO);
		if (heartVO.size() % 3 == 0) {
			mav.addObject("hlen", heartVO.size() / 3);
		} else {
			mav.addObject("hlen", heartVO.size() / 3 + 1);
		}
		mav.setViewName("cody/sub_cody");
		return mav;
	}

	// ����������(��Ÿ��)
	@GetMapping("/codyStyleList")
	public ModelAndView codyStyleList(CodyVO vo, String style, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String sex = (String) session.getAttribute("logSex");
		List<CodyVO> styleVO = service.codyStyleList(style, sex);
		mav.addObject("vo", styleVO);
		if (styleVO.size() % 3 == 0) {
			mav.addObject("len", styleVO.size() / 3);
		} else {
			mav.addObject("len", styleVO.size() / 3 + 1);
		}
		mav.setViewName("cody/sub_cody");
		return mav;
	}

	// ���ƿ� �߰�
	@PostMapping("/heartInsert")
	@ResponseBody
	public int heartInsert(String cname, HttpSession session) {
		String userid = (String) session.getAttribute("logId");
		return service.heartInsert(userid, cname, "Y");
	}

	// ���ƿ� �߰�
	@PostMapping("/heartDelete")
	@ResponseBody
	public int heartDelete(String cname, HttpSession session) {
		String userid = (String) session.getAttribute("logId");
		return service.heartDelete(userid, cname);
	}

	// ���ƿ� �˻�
	@GetMapping("/heartSelect")
	@ResponseBody
	public List<HeartVO> heartSelect(HttpSession session) {
		String userid = (String) session.getAttribute("logId");
		return service.heartSelect(userid);
	}

}
