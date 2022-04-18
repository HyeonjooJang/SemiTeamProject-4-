package com.campus.myapp.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.campus.myapp.service.MapService;
import com.campus.myapp.service.ReviewService;
import com.campus.myapp.vo.ReviewVO;
import com.campus.myapp.vo.StoreVO;

@RestController
public class ReviewController {
   @Inject
   ReviewService service;
   
   @Inject
   MapService mapservice;
   //��۸��
   @GetMapping("/review/list")
   public Map<String, Object> list(String id) {
      StoreVO store= mapservice.getStore(id);
      List<ReviewVO> reviewList= service.reviewList(id);
     Map<String,Object> map=new HashMap<>();
     map.put("store", store);
     map.put("reviews", reviewList);
     return map;
   }
   //��ۼ���
   @PostMapping("/review/editOk")
   public int editOk(ReviewVO vo, HttpSession session) {
      vo.setUserid((String)session.getAttribute("logId"));
      return service.reviewEdit(vo);
   }
   //��ۻ���
   @GetMapping("/review/del")
   public int delOk(int reviewno, HttpSession session) {
      return service.reviewDel(reviewno, (String)session.getAttribute("logId"));
   }

   // ������
   @PostMapping("/review/writeOk")
   public ResponseEntity<String> reviewWriteOk(ReviewVO vo, String pid, HttpServletRequest request) {
      
      vo.setUserid((String)request.getSession().getAttribute("logId"));// �۾��� 
      vo.setId(pid);
      ResponseEntity<String> entity = null;
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));

      String path = request.getSession().getServletContext().getRealPath("/img/reviewimg"); // ���� ���ε带 ���� ���ε� ��ġ�� ���� �ּ�
      System.out.println("id-->"+vo.getId()+", >>>pid="+pid);

      try {
         // ���Ͼ��ε带 ó���ϱ� ���ؼ��� request��ü���� multipart��ü�� ����ȯ�Ͽ��� �Ѵ�.
         MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;

         // mr ������ ����ŭ MultipartFile��ü�� �����Ѵ�.
         List<MultipartFile> files = mr.getFiles("file");
         System.out.println("���ε� ���� �� = "+files.size());

         if(files!=null) { // if 111111

            int cnt = 1;// ���ε� ������ ���� filename1 ,filename2�� ���ϸ��� �����ϱ� ���� ����

            // ÷�����ϼ� ��ŭ �ݺ��Ͽ� ���ε��Ѵ�.
            for(int i=0; i<files.size(); i++) { // for 222222
               // 1. MultiaprtFile��ü ������
               MultipartFile mf = files.get(i);

               // 2. ���ε��� ���� ���ϸ��� ���ϱ�
               String orgFileName = mf.getOriginalFilename();
               System.out.println("orgFileName ==> "+orgFileName);

               // 3. rename�ϱ�
               if(orgFileName!=null && !orgFileName.equals("")) { // if 333333 ���ϸ��� �����ϸ�
                  File f = new File(path, orgFileName);

                  // ������ �����ϴ��� Ȯ�� -> true: ������ �����Ѵ�, false: ������ ����.
                  if(f.exists()) { // if 444444
                     for(int renameNum=1;;renameNum++) { // for 555555
                        // Ȯ���ڿ� ������ �и��Ѵ�.
                        int point = orgFileName.lastIndexOf(".");
                        String fileName = orgFileName.substring(0,point); // Ȯ���ڸ� ������ �����̸��� ����
                        String ext = orgFileName.substring(point+1); // Ȯ���ڸ� ����

                        f = new File(path, fileName+" ("+renameNum+")."+ext);
                        if(!f.exists()) { // ���� ������ ���� ��ü�� ������
                           orgFileName = f.getName();
                           break;
                        }
                     } // for 555555
                  } // if 444444

                  // 4. ���� ���ε�
                  try {
                     mf.transferTo(f); // ���� ���ε尡 �߻��� 
                  }catch(Exception ee){

                  }
                  // 5. ���ε� (���ο� ���ϸ�) vo�� ����
                  if(cnt==1) vo.setReviewimg(orgFileName);

                  cnt++;
               } //if 333333 
            } //

         } // if 111111
         System.out.println(vo.toString());

         // DB���
         service.reviewWrite(vo);
         // ���ڵ� �߰� ����
         HttpSession session=request.getSession();
         String fname=(String)session.getAttribute("menu");
       String msg = "<script>alert('���䰡 ��ϵǾ����ϴ�.');location.href='/map/main_map?id="+pid+"&fname="+fname+"';</script>";
         entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
      }catch(Exception e) {
         e.printStackTrace();
         // ���ڵ� �߰� ����

         // ���� �ȳ� �޼��� ����
         String msg = "<script>alert('���� ����� �����Ͽ����ϴ�.');history.back();</script>";

         // ������������ ������(javascript)
         entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST); // 400
      }
      return entity;
   }
}