package com.campus.myapp.service;

import com.campus.myapp.vo.FoodVO;

public interface FoodService {
	
	//���� �̸� �ִ��� üũ
	public int checkFilename(String filename);
	
	public int foodInsert(FoodVO vo);

}
