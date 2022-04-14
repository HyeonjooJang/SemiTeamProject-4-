package com.campus.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campus.myapp.vo.CodyVO;
import com.campus.myapp.vo.HeartVO;

@Mapper
@Repository
public interface CodyDAO {
	//�ڵ� ��õ �̹��� ����Ʈ ��������
	public List<CodyVO> codyRecommend(int temp, String sex);
	
	//�ڵ� �߰�
	public int codyInsert(CodyVO vo);
	//cname �ߺ�Ȯ��
	public Integer cnameCheck(String cname);
	//�ڵ� ����
	public List<CodyVO> codySelect(String cname);
	//�ڵ� ����
	public int codyUpdate(CodyVO vo);
	//�ڵ� ����
	public int codyDelete(String cname);
	
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
