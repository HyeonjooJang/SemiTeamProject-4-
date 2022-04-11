package com.campus.myapp.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
	
	//ȸ���������� �������� �̵�
	@GetMapping("memberEdit")
	public ModelAndView memberEdit(HttpSession session) {
		String userid = (String) session.getAttribute("logId");
		ModelAndView mav = new ModelAndView();
		
		MemberVO vo = service.memberSelect(userid);
		
		mav.addObject("vo", vo);
		mav.setViewName("member/memberEdit");
		return mav;
	}
	
	
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
	
	//ȸ������ ����
	@PostMapping("memberEditOk")
	public ResponseEntity<String> memberEditOk(MemberVO vo, HttpServletRequest request, HttpSession session) {
		// session�� �α��� ���̵� Ȯ��
		
		vo.setUserid((String) request.getSession().getAttribute("logId"));
		
		ResponseEntity<String> entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text","html",Charset.forName("UTF-8")));
		
		//���� ���ε带 ���� ���ε� ��ġ�� �����ּ�
		String path = request.getSession().getServletContext().getRealPath("/img/memberimg");
		System.out.println("path-->"+path);
		try {
			//���Ͼ��ε带 ���� request��ü���� multipart��ü�� ���ؾ� �Ѵ�
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
			
			//mr�� ������ ����ŭ multipartfile��ü�� �����ؾ� ��
			List<MultipartFile> files = mr.getFiles("imgName");
			
			
			if(files != null) {//if - 1
				
				
					//1.multipartfile��ü ������
					MultipartFile mf = files.get(0);
					//2.���ε��� ���� ���ϸ� ���ϱ�
					String orgFileName = mf.getOriginalFilename();
					System.out.println("orgFileName = " + orgFileName);
					
					//3.rename�ϱ�
					if(orgFileName != null && !orgFileName.equals("")) {//if - 3
						File f = new File(path, orgFileName);
						
						if(f.exists()) {//if - 4 ���� ���� ����
							for(int renameNum=1;;renameNum++) {// for - 5
								
								int point = orgFileName.lastIndexOf(".");
								String fileName = orgFileName.substring(0, point);//���ϸ�
								String ext = orgFileName.substring(point+1);//Ȯ����
								
								f = new File(path,fileName+" ("+renameNum+")."+ext);//���ο� ���ϸ�
								if(!f.exists()) {//if - 6   ���λ����� ���� ��ü�� ������
									orgFileName = f.getName();
									break;
								}
							}// for - 5
						}//if - 4
						
						try {
							mf.transferTo(f);//���� ���ε尡 ����
						} catch (Exception ee) {
							ee.printStackTrace();
						}
						
						vo.setProfile(orgFileName);
					}//if - 3
			}//if - 1
			
			//db���
			service.memberUpdate(vo);
			session.setAttribute("logImg", vo.getProfile());
			//���ڵ� �߰� ����
			String msg = "<script>alert('�������� �����Ǿ����ϴ�.'); location.href='/';</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			//���ڵ� �߰� ����
			fileDelete(path, vo.getProfile());
			
			//���� �޼���
			String msg = "<script>alert('������ ������ �����Ͽ����ϴ�'); history.back();</script>";

			entity = new ResponseEntity<String>(msg,headers,HttpStatus.BAD_REQUEST);
			}
		return entity;
	}
	//���� �����
	public void fileDelete(String p, String f) {
		if(f != null) {//���ϸ��� �����ϸ� 
			File file = new File(p,f);
			file.delete();
		}
	}
	
	//ȸ��Ż��
	@GetMapping("memberDelete")
	public ModelAndView memberDelete(String userid) {
		
		
		int result = service.memberDelete(userid);//result�������� �������� �Ǵ� 0 or 1
		
		ModelAndView mav = new ModelAndView();
		if(result>0) {
			mav.setViewName("redirect:logout");  //list�� �̵��� ��Ʈ�ѷ� ȣ��
		}else {
			
			mav.setViewName("redirect:memberEdit");
		}
		return mav;
	}
	@GetMapping("masterPage")
	public ModelAndView masterPage() {
		ModelAndView mav = new ModelAndView();
		
		//mav.addObject("lst",service.memberSelectAll());
		
		mav.setViewName("master/master_member");
		return mav;
	}
	
	//ȸ�� ���� ���� ���
	@RequestMapping("memberListAll")
	public List<MemberVO> list() {
		return service.memberListAll();
	}
	
}
