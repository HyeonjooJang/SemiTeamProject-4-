package com.campus.myapp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campus.myapp.vo.FoodVO;

@Mapper
@Repository
public interface FoodDAO {
	
	//���� �̸� �ִ��� üũ
	public int checkFilename(String filename);
	
	//���� ���� ������ �߰�
	public int foodInsert(FoodVO vo);
	

}
