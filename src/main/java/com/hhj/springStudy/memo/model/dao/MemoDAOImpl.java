package com.hhj.springStudy.memo.model.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hhj.springStudy.memo.model.dto.MemoDTO;

@Repository
public class MemoDAOImpl implements MemoDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<MemoDTO> getSelectAll() {
		return sqlSession.selectList("memo.getSelectAll"); 
	}

	@Override
	public MemoDTO getSelectOne(MemoDTO paramDto) {
		return sqlSession.selectOne("memo.getSelectOne", paramDto); // DTO안의 기본키를 불러와야 하기 때문에 paramDto
	}

	@Override
	public int setInsert(MemoDTO paramDto) {
		return sqlSession.insert("memo.setInsert", paramDto); // 담을 정보가 DTO에 들어있기 때문에 paramDto
	}

	@Override
	public int setUpdate(MemoDTO paramDto) {
		return sqlSession.update("memo.setUpdate", paramDto); // 수정 할 레코드를 찾기 위한 기본키를 가져오기 위해 paramDto
	}

	@Override
	public int setDelete(MemoDTO paramDto) {
		return sqlSession.delete("memo.setDelete", paramDto); // 삭제 할 레코드를 찾기 위한 기본키를 가져오기 위해 paramDto
	}

}
