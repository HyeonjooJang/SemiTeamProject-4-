package com.campus.myapp.service;

import java.util.List;
import com.campus.myapp.vo.ReviewVO;

public interface ReviewService {
	 //��۵��
    public int reviewWrite(ReviewVO vo);
    //��۸��
    public List<ReviewVO> reviewList(String id);
    //��ۼ���
    public int reviewEdit(ReviewVO vo);
    //��ۻ���
    public int reviewDel(int reviewno, String uesrid);
    public ReviewVO getFileName(int reviewno); // ���ϸ� ����
    
    //���� ����
  	public double staravg(ReviewVO id);
  	
  	//���� ����
  	public int reviewcnt(ReviewVO id);
  	// �� 1�� ����
 	public ReviewVO reviewSelect(int reviewno);
	public int reviewUpdate(ReviewVO vo);
	
	
}