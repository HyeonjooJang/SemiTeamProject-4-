package com.campus.myapp.service;

import java.util.List;

import com.campus.myapp.vo.CodyVO;

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
}
