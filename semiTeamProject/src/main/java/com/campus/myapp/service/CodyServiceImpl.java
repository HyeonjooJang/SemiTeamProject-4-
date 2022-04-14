package com.campus.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.campus.myapp.dao.CodyDAO;
import com.campus.myapp.vo.CodyVO;
import com.campus.myapp.vo.HeartVO;

@Service
public class CodyServiceImpl implements CodyService {
	@Inject
	CodyDAO dao;

	//�ڵ� ��õ�̹���
	@Override
	public List<CodyVO> codyRecommend(int temp, String sex) {
		return dao.codyRecommend(temp, sex);
	}
	//�ڵ� �߰�
	@Override
	public int codyInsert(CodyVO vo) {
		return dao.codyInsert(vo);
	}
	//cname �ߺ�Ȯ��
	@Override
	public Integer cnameCheck(String cname) {
		return dao.cnameCheck(cname);
	}
	//�ڵ� ��������
	@Override
	public List<CodyVO> codySelect(String cname) {
		return dao.codySelect(cname);
	}
	//�ڵ� ����
	@Override
	public int codyUpdate(CodyVO vo) {
		return dao.codyUpdate(vo);
	}
	//�ڵ� ����
	@Override
	public int codyDelete(String cname) {
		return dao.codyDelete(cname);
	}
	
	//��ü
	@Override
	public List<CodyVO> codyListAll() {
		return dao.codyListAll();
	}
	//����
	@Override
	public List<CodyVO> codyGenderList(String sex) {
		return dao.codyGenderList(sex);
	}
	//���ƿ� ���
	@Override
	public List<HeartVO> codyHeartList(String userid) {
		return dao.codyHeartList(userid);
	}
	//��Ÿ��
	@Override
	public List<CodyVO> codyStyleList(String style, String sex) {
		return dao.codyStyleList(style, sex);
	}
	//���ƿ� �߰�
	@Override
	public int heartInsert(String userid, String cname, String heartinfo) {
		return dao.heartInsert(userid, cname, heartinfo);
	}
	//���ƿ� ����
	@Override
	public int heartDelete(String userid, String cname) {
		return dao.heartDelete(userid, cname);
	}
	//���ƿ� ����
	@Override
	public List<HeartVO> heartSelect(String userid) {
		return dao.heartSelect(userid);
	}

}
