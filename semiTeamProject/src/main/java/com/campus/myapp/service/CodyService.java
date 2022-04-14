package com.campus.myapp.service;

import java.util.List;

import com.campus.myapp.vo.CodyVO;
import com.campus.myapp.vo.HeartVO;

public interface CodyService {
	//�ڵ� ��õ �̹��� ����Ʈ ��������
	public List<CodyVO> codyRecommend(int temp, String sex);
	//�ڵ� �߰�
	public int codyInsert(CodyVO vo);
	//cname �ߺ�Ȯ��
	public Integer cnameCheck(String cname);
	
	//�ڵ� ����Ʈ(all)
	public List<CodyVO> codyListAll();
	//�ڵ� ����Ʈ(����)
	public List<CodyVO> codyGenderList(String sex);
	//�ڵ� ����Ʈ(style)
	public List<CodyVO> codyStyleList(String style, String sex);
	//�ڵ� ����Ʈ(heart)
	public List<HeartVO> codyHeartList(String userid);
	
	//���ƿ� �߰�
	public int heartInsert(String userid, String cname, String heartinfo);
	//���ƿ� ����
	public int heartDelete(String userid, String cname);
	//���ƿ� �˻�
	public List<HeartVO> heartSelect(String userid);
}
