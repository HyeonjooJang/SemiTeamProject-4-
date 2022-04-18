package com.campus.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campus.myapp.vo.ReviewVO;
@Mapper
@Repository
public interface ReviewDAO {
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


}