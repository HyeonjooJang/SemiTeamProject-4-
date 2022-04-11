package com.campus.myapp.service;

import java.util.List;


import com.campus.myapp.vo.PagingVO;
import com.campus.myapp.vo.ReviewVO;

public interface ReviewService {
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
