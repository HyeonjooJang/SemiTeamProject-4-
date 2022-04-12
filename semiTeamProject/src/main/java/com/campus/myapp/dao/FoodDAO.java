package com.campus.myapp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campus.myapp.vo.FoodVO;

@Mapper
@Repository
public interface FoodDAO {
	
	//���� �̸� �ִ��� üũ
	public int checkFilename(String filename);
	
	//�̹� �ش� �̸��� ������ ����Ǿ� �ִ��� Ȯ��
	public int checkFoodName(String fname);
	
	//���� ���� ������ �߰�
	public int foodInsert(FoodVO vo);

	public String getfname();
	
	//�ش� ���� ������ ��������
	public FoodVO getFoodData(String searchFood);
	
	
	

}
