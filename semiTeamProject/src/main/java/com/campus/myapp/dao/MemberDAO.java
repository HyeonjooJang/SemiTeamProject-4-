package com.campus.myapp.dao;

import java.util.List;

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
	
	//ȸ���������� ����
	public MemberVO memberSelect(String userid);
	
	//ȸ����������
	public int memberUpdate(MemberVO vo);
	
	//ȸ��Ż��
	public int memberDelete(String userid);
	
	//�ɹ����� ���� ���
	public List<MemberVO> memberListAll(MemberVO vo);
	
	//ȸ������-Ż��
	public int memberDeleteOk(String userid);
	
	//���̵� ã��
	public MemberVO findId(MemberVO vo);

	//��й�ȣ ã��
	public MemberVO findPwd(MemberVO vo);
	
	//��й�ȣ �缳��
	public int resetPwdOk(MemberVO vo);
	
	
}

