package com.campus.myapp.service;

import java.util.List;

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
	
	//����Ǿ� �ִ� ���� �̹��� ���� �̸� ��������
	public String getFileName(String fname);
	
	//���� ���� ����
	public int foodUpdate(FoodVO vo);
	
	//���� ��¥�� �̺�Ʈ�� ��ġ�ϴ� ���� ��������
	public List<FoodVO> getEqualEvent(String event);
	
	//���� ������ ��ġ�ϴ� ���� ���� ��������
	public List<FoodVO> getEqualWeather(String todayWeather);
	
	//���� ������ ��ġ�ϴ� ���� ���� ��������
	public List<FoodVO> getEqualSeason(String season);
	
	//�⺻ ���� �ƴ� ���� ����� ������ ���ϴ� ����� ������ �ִ� ���� ���� ��������
	public List<FoodVO> getEqualTemp(int temp);
	
	//�켱���� N�� ���� ��������
	public List<FoodVO> getPriorityN(String priorityN);
	
	//���� ī�װ��� �ش� �ϴ� ���� ���� ��������
	public List<FoodVO> getCategoryFood(String foodType);
	
	//��ü ���� ��������
	public List<FoodVO> getAllFood();
	
	//��Ÿ ���� ��������
	public List<FoodVO> getEtcFood();

}
