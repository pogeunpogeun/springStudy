package com.hhj.springStudy.guestBook.model.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hhj.springStudy.guestBook.model.dto.GuestBookDTO;

@Repository
public class GuestBookDAOImpl implements GuestBookDAO {
	
	@Inject
	SqlSession sqlSession;
	@Override
	public List<GuestBookDTO> getSelectAll() {
		return sqlSession.selectList("guestBook.getSelectAll");
	}

	@Override
	public GuestBookDTO getSelectOne(GuestBookDTO paramDto) {
		return sqlSession.selectOne("guestBook.getSelectOne", paramDto);
	}

	@Override
	public int setInsert(GuestBookDTO paramDto) {
		return sqlSession.insert("guestBook.setInsert", paramDto);
	}

	@Override
	public int setUpdate(GuestBookDTO paramDto) {
		return sqlSession.update("guestBook.setUpdate", paramDto);
	}

	@Override
	public int setDelete(GuestBookDTO paramDto) {
		return sqlSession.delete("guestBook.setDelete", paramDto);
	}

	@Override
	public int getLogin(GuestBookDTO paramDto) {
		int result = 0;
		if(sqlSession.selectOne("guestBook.getLogin", paramDto) != null) {
			result = sqlSession.selectOne("guestBook.getLogin", paramDto);
		} 
		return result;
	}
	
}
