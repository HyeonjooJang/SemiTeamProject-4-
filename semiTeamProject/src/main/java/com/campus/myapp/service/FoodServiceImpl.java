package com.campus.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.campus.myapp.dao.FoodDAO;
import com.campus.myapp.vo.FoodVO;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Inject
	FoodDAO dao;

	@Override
	public int checkFilename(String filename) {
		
		return dao.checkFilename(filename);
	}
	
	@Override
	public int checkFoodName(String fname) {
		
		return dao.checkFoodName(fname);
	}

	@Override
	public int foodInsert(FoodVO vo) {
		
		return dao.foodInsert(vo);
	}

	
	@Override
	public FoodVO getFoodData(String searchFood) {
		
		return dao.getFoodData(searchFood);

	}

	@Override
	public String getFileName(String fname) {
		
		return dao.getFileName(fname);
	}

	@Override
	public int foodUpdate(FoodVO vo) {
		
		return dao.foodUpdate(vo);
	}

	@Override
	public List<FoodVO> getEqualEvent(String event) {
		
		return dao.getEqualEvent(event);
	}

}
