package com.hhj.springStudy.memberNew.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hhj.springStudy._common.DB;
import com.hhj.springStudy.memberNew.model.dto.MemberNewDTO;

public class MemberNewDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//SQL 명령
	private final String QUERY_SETINSERT = "insert into memberNew values (seq_memberNew.nextval,?,?,?,?,sysdate)";
	
	public int getTotalRecord(MemberNewDTO paramDto) { // searchGubun과 searchData를 가져오기 위한 매개변수 paramDto
		String searchYN = "Y";
		String searchGubun = paramDto.getSearchGubun();
		String searchData = paramDto.getSearchData();
		
		if(searchGubun == null || searchData == null || searchGubun.trim().equals("") || searchData.trim().equals("")) {
			searchYN = "N";
			searchGubun = "";
			searchData = "";
		}	
		int result = 0;
		try {
			conn = DB.dbConn();
			String sql = "";
			sql += "select count(*) totalCounter from memberNew where 1=1 ";
			if(searchYN.equals("Y")) {
				if(searchGubun.equals("id_name")) {
					sql += " and (id like ? or name like ?) ";				
				} else {
					sql += " and " + searchGubun + " like ? ";
				}
			}
			//System.out.println("1 - " + sql);
			pstmt = conn.prepareStatement(sql);
			
			if(searchYN.equals("Y")) {
				if(searchGubun.equals("id_name")) {
					pstmt.setString(1, '%' + searchData + '%');
					pstmt.setString(2, '%' + searchData + '%');
				} else {
					pstmt.setString(1, '%' + searchData + '%');
				}
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("totalCounter");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return result;
	}
	public List<MemberNewDTO> getSelectAll(MemberNewDTO paramDto) {
		List<MemberNewDTO> list = new ArrayList<>();
		try {
			conn = DB.dbConn();
			String sql = "";
			sql += " select * from memberNew where 1=1 "; // where을 위에 선언하고 밑 쿼리문은 다 and로 연결
			// 검색관련 쿼리문-----------------------------------------------------------------------------------
			if(paramDto.getSearchGubun().equals("") || paramDto.getSearchData().equals("")) { // 검색을 하지 않는 경우 
				
			} else if (paramDto.getSearchGubun().equals("id_name")) {
				sql += " and (id like ? or name like ?) ";
			} else {
				sql += " and " + paramDto.getSearchGubun() + " like ? ";
			}
			
			sql += " order by no desc ";
			// 페이징 쿼리문-------------------------------------------------------------------------------------
			String pageSql = "";
			pageSql += " select * from (select A.*, Rownum Rnum from (" + sql + ") A) ";
			pageSql += " where Rnum >=? and Rnum <=? ";
			
			//System.out.println("2 - " + pageSql);
			
			pstmt = conn.prepareStatement(pageSql);
			// -----------------------------------------------------------------------------------------------
			int k = 0;
			if(paramDto.getSearchGubun().equals("") || paramDto.getSearchData().equals("")) { // 검색을 하지 않는 경우 
				
			} else if (paramDto.getSearchGubun().equals("id_name")) {
				pstmt.setString(++k, '%' + paramDto.getSearchData() + '%');
				pstmt.setString(++k, '%' + paramDto.getSearchData() + '%');
			} else {
				pstmt.setString(++k, '%' + paramDto.getSearchData() + '%');
			}	
			pstmt.setInt(++k, paramDto.getStartRecord());
			pstmt.setInt(++k, paramDto.getLastRecord());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberNewDTO dto = new MemberNewDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setRegiDate(rs.getDate("regiDate"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return list;
	}
	public MemberNewDTO getSelectOne(MemberNewDTO paramDto) {
		MemberNewDTO dto = new MemberNewDTO();
		try {
			conn = DB.dbConn();
			String sql = "select * from memberNew where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, paramDto.getNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setRegiDate(rs.getDate("regiDate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return dto;
	}
	public int setInsert(MemberNewDTO paramDto) {
		int result = 0;
		try {
			conn = DB.dbConn();
			String sql = "insert into memberNew values (seq_memberNew.nextval,?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramDto.getId());
			pstmt.setString(2, paramDto.getPasswd());
			pstmt.setString(3, paramDto.getName());
			pstmt.setString(4, paramDto.getPhone());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return result;
	}
	public int setUpdate(MemberNewDTO paramDto) {
		int result = 0;
		try {
			conn = DB.dbConn();
			String sql = "update memberNew set phone=? where no=? and passwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramDto.getPhone());
			pstmt.setInt(2, paramDto.getNo());
			pstmt.setString(3, paramDto.getPasswd());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}	
		return result;
	}
	public int setDelete(MemberNewDTO paramDto) {
		int result = 0;
		try {
			conn = DB.dbConn();
			String sql = "delete from memberNew where no=? and passwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, paramDto.getNo());
			pstmt.setString(2, paramDto.getPasswd());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return result;
	}
}
