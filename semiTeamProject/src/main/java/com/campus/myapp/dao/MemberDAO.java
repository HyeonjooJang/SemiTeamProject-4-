package com.campus.myapp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campus.myapp.vo.MemberVO;
@Mapper
@Repository
public interface MemberDAO {
	//ȸ�����
	public int memberInsert(MemberVO vo);
	
	//�α���
	public MemberVO loginCheck(MemberVO vo);
	
	//���̵� �ߺ� �˻�
	public int idCheck(String userid);
}

