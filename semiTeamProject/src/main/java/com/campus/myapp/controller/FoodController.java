package com.campus.myapp.controller;




import java.io.File;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.campus.myapp.service.FoodService;
import com.campus.myapp.vo.FoodVO;

@Controller
public class FoodController {
	
	@Inject
	FoodService service;
	
	@GetMapping("/food/main_food")
	public ModelAndView foodPage() {
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("food/main_food");		
		return mav;
		
	}
	
	@PostMapping("/getFoodRecommend")
	@ResponseBody
	public List<FoodVO> foodRecommend(String weather){
		
		//���� ��õ
		//�켱 �� �ִ� ���Ŀ��� 2��
		
		//���� ��õ ȭ������ ���� ���� ����Ʈ
		List<FoodVO> vo = new ArrayList<FoodVO>();
		
		//�̺�Ʈ ��ġ ������ ����Ʈ
		//List<FoodVO> event = new ArrayList<FoodVO>();
		
		//���� ��ġ, ���� ��ġ, �µ� �ش� �ϴ� ���� ������ ����Ʈ
		List<FoodVO> foods = new ArrayList<FoodVO>();
		
		
		
		int cnt=0;
		//cnt<=2���� �϶�
		//1. ���� ��¥�� ��ġ�ϴ� �̺�Ʈ ��¥ �ִ��� Ȯ�� -- 0��, 1��, 2�� �̻�...(������ �ִٸ� ���� 1�� �����ؼ� ��� ����Ʈ�� �߰�)
		//���� ��¥
		LocalDate now = LocalDate.now();
		
		List<FoodVO> event = service.getEqualEvent(now.toString());
		
		if(event.size()>=2) {
			//1�� �����ϱ�
			Collections.shuffle(event);
			vo.add(event.get(0));
			cnt=1;
		}else if(event.size()==1) {
			//1��
			vo.add(event.get(0));
			cnt=1;
		}
		//0���� ��� - cnt = 0
		
		
		
		//2. ���� ������ ��ġ�ϴ� ���� �ִ��� Ȯ��
		/////////////////////////���� ������ �޾ƿ;� �Ѵ�.....
		
		
		
		//3. ���� ������ ��ġ�ϴ� ���� �ִ��� Ȯ��
		int month = now.getMonthValue();
		
		
		//4. ���� �µ��� �ش��ϴ� ���� �ִ��� Ȯ��
		
		
		
		//2,3,4, ��� ����Ʈ�� �߰� <- (1���� 0���̸� 2�� ����, 1�� �̻��̸� 1�� ����)
		
		
		
		
		//�켱 ���� ���� ���Ŀ��� 3��
		//priorty==N�� ���� ����Ʈ �������� ���� ������ 3��
				
		 return vo;
		
	}
	
	@GetMapping("/master/master_food")
	public ModelAndView foodAdminPage() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("master/master_food");
		return mav;
	}
	
	//���� �̸� �ߺ� Ȯ��
	@PostMapping("/fileNameCheck")
	@ResponseBody
	public int fileNameCheck(@RequestParam("fileName") String fileName) {
		
		return service.checkFilename(fileName);
	}
	
	//���� �߰�
	@PostMapping("/master/foodAdd")
	public ResponseEntity<String> foodAddOk(FoodVO vo, HttpServletRequest request){
		
		ResponseEntity<String> entity = null;
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8"))); 
		
		//�̹� �����ͺ��̽��� �ش� �̸��� ������ �ִ��� Ȯ��
		int result = service.checkFoodName(vo.getFname());
		
		if(result>0) {
			
			String msg = "<script>alert('�ش� ������ �̹� ����Ǿ� �ֽ��ϴ�.'); history.back(); </script>";
			
			entity = new ResponseEntity(msg, headers,HttpStatus.BAD_REQUEST );
			
		}else {		
			//���� ���ε带 ���� ���ε� ��ġ�� ���� �ּ�
			String path = request.getSession().getServletContext().getRealPath("/img/foodimg/upload");
			
			System.out.println(path);
			
			//event�� no�� �������ֱ�
			if((vo.getEvent()).equals("no")) {
				vo.setEvent(null);
			}
			
			if((vo.getWeather()).equals("allweather")) {
				vo.setWeather(null);
			}
			
			System.out.println(vo.getEvent()); 
			System.out.println(vo.getPriority());
			
			try {
				
				//���� ���ε带 ���� request ��ü���� multipart ��ü�� ����ȯ
				MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
				
				MultipartFile file = mr.getFile("filename");
				
				String fileName = file.getOriginalFilename();
				System.out.println(fileName);
				
				File f = new File(path, fileName);
				
				//���� ���ε�
				try {
					file.transferTo(f);
					System.out.println("���� ���ε�");
					vo.setFoodimg(fileName);
					
				}catch(Exception ee) {
					
				}
				//DB�� �߰�
				service.foodInsert(vo);
				
				String msg = "<script>alert('������ �߰��� �Ϸ�Ǿ����ϴ�.');location.href='/master/master_food'; </script>";
			
				entity = new ResponseEntity(msg, headers,HttpStatus.OK );
				
			}catch(Exception e) {
				e.printStackTrace();
				//���� ����
				deleteFile(path, vo.getFoodimg());
				
				String msg = "<script>alert('������ �߰��� �����Ͽ����ϴ�.'); history.back(); </script>";
				entity = new ResponseEntity(msg, headers,HttpStatus.BAD_REQUEST );
			}
		}
		return entity;
	}
	
	@PostMapping("/getFoodData")
	@ResponseBody
	public FoodVO sendFoodData(@RequestParam("searchFood") String searchFood) {
		
		return service.getFoodData(searchFood);
		
	}
	
	
	 @PostMapping("/master/foodModify") 
	 public ResponseEntity<String> foodModifyOk(FoodVO vo, HttpServletRequest request){
		 
		 ResponseEntity<String> entity = null;
		 
		 HttpHeaders headers = new HttpHeaders();
		 
		 headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
		 
		 System.out.println(vo.getFoodimg());
		 
		
		 if((vo.getEvent()).equals("no")) {
			 vo.setEvent(null);
		 }
		 if((vo.getWeather()).equals("allweather")) {
			 vo.setWeather(null);
		 }
		 
		 //DB���� ���� �̸� ��������
		 String priorFile = service.getFileName(vo.getFname());
		 System.out.println(priorFile+"<<<");
		 
		 //���� ���ε带 ���� ���ε� ��ġ�� ���� �ּ�
		 String path = request.getSession().getServletContext().getRealPath("/img/foodimg/upload");
		 
			 
			 try {
				 //���� ���� �̹��� ���� �ø���
				 MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
				 
				 MultipartFile file = mr.getFile("filename");
				 
				 String fileName = file.getOriginalFilename();
				 
				 System.out.println(fileName);
				 
				 File f = new File(path, fileName);
				 
				///���ϳֱ�	 
				 try {
					 file.transferTo(f);
					 System.out.println("���� ���ε�");
					 
					 if(file!=null &&!file.isEmpty()) {
						 //÷���ߴٸ�
						 deleteFile(path, priorFile);
					 }
				 
					 vo.setFoodimg(fileName);
				 }catch(Exception ex) {
					 
				 }
				 //DB ������Ʈ
				 
				 service.foodUpdate(vo);
				 
				 String msg = "<script>alert('���� ������ �Ϸ�Ǿ����ϴ�.');location.href='/master/master_food';</script>";
				 
				 entity = new ResponseEntity(msg, headers, HttpStatus.OK);
				 
				 
				 
			 }catch(Exception e) {
				 
				 e.printStackTrace();
				 
				 deleteFile(path, vo.getFoodimg());
				 
				 String msg = "<script>alert('���� ������ �����Ͽ����ϴ�.');history.back();</script>";
				 entity = new ResponseEntity(msg, headers, HttpStatus.BAD_REQUEST);
				 
			 }
		
		 return entity;

	 }



	
	//���� ����
	public void deleteFile(String p, String f) {
		
		if(f!=null) {
			
			File file = new File(p,f);
			file.delete();
		}
		
	}
	
	
}
