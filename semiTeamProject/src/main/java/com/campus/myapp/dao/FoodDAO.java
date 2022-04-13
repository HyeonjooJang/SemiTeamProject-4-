package com.campus.myapp.dao;

import java.util.List;

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
	
	//�ش� ���� ������ ��������
	public FoodVO getFoodData(String searchFood);
	
	//����Ǿ� �ִ� ���� �̹��� ���� �̸� ��������
	public String getFileName(String fname);
	
	//���� ���� ����
	public int foodUpdate(FoodVO vo);
	
	//���� ��¥�� �̺�Ʈ�� ��ġ�ϴ� ���� ��������
	public List<FoodVO> getEqualEvent(String event);
	
	//���� ������ ��ġ�ϴ� ���� ���� ��������
	

}
