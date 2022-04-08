package com.campus.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;





//���ͼ�Ʈ�� ��Ʈ���ϴ� Ŭ����
public class MasterInterceptor implements HandlerInterceptor {
	//��Ʈ�ѷ��� ȣ��Ǳ� ���� ����� �޼ҵ�
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
		//false -> �α��� ��������
		//true -> ����ó��
		
		HttpSession session = request.getSession();//���ǰ�ü ������
		String logId = (String) session.getAttribute("logId");//�α��� ���� ���ϱ�
		
		if(logId.equals("master")) {//�α����� �� ���
			return true;
		}else {
			response.sendRedirect("/");
			return false;
		}
	}
}
