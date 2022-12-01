package com.hhj.springStudy.member.model.dao;

import java.util.List;

import com.hhj.springStudy.member.model.dto.MemberDTO;
import com.hhj.springStudy.product.model.dto.ProductDTO;

public interface MemberDAO {
	public List<MemberDTO> getSelectAll();
	public MemberDTO getSelectOne(MemberDTO paramDTO);
	public int setInsert(MemberDTO paramDTO);
	public int setUpdate(MemberDTO dto);
	public int setDelete(MemberDTO paramDTO);
	public int getLogin(MemberDTO paramDTO);
}
