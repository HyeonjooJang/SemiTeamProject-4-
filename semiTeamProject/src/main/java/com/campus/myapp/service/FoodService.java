package com.campus.myapp.service;


import com.campus.myapp.vo.FoodVO;

public interface FoodService {
	
	//���� �̸� �ִ��� üũ
	public int checkFilename(String filename);
	
	//�̹� �ش� �̸��� ������ ����Ǿ� �ִ��� Ȯ��
	public int checkFoodName(String fname);
	
	//���� ���� ������ �߰�
	public int foodInsert(FoodVO vo);
	
	//�ش� ���� ������ ��������
	public FoodVO getFoodData(String searchFood);

	public String getfname();
	
	//����Ǿ� �ִ� ���� �̹��� ���� �̸� ��������
	public String getFileName(String fname);
	
	//���� ���� ����
	public int foodUpdate(FoodVO vo);

}
