package com.campus.myapp.dao;

import java.util.List;

import com.campus.myapp.vo.PagingVO;
import com.campus.myapp.vo.ReviewVO;

public interface ReviewDAO {
	
	// ������
	public int ReviewInsert(ReviewVO vo);
	// ������
	public List<ReviewVO> reviewList(PagingVO pVO);
	// �� ���ڵ� �� 
	public int totalRecord(PagingVO pVO);
	// �� 1�� ����
	public ReviewVO reviewSelect(int reviewno);
	// �� ����
	public int reviewUpdate(ReviewVO vo);
	// �� ����
	public int reviewDelete(int reviewno, String userid);
}
