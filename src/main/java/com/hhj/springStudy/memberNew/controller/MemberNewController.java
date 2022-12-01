package com.hhj.springStudy.memberNew.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhj.springStudy.memberNew.model.dao.MemberNewDAO;
import com.hhj.springStudy.memberNew.model.dto.MemberNewDTO;

/*
 * http://localhost:8091/springStudy/memberNew/list
 * http://localhost:8091/springStudy/memberNew/view
 * http://localhost:8091/springStudy/memberNew/chuga
 * http://localhost:8091/springStudy/memberNew/sujung
 * http://localhost:8091/springStudy/memberNew/sakje
 */

@Controller
@RequestMapping("/memberNew") // 공통된 폴더명을 Mapping
public class MemberNewController {
	
	@RequestMapping("/list")
	public String list(
		Model model,
		@ModelAttribute MemberNewDTO dto, // searchGubun, searchData 를 받기 위한 DTO
		HttpServletRequest request
		) {
		//---------------------------------------------------------------------------------------검색 (1)
		String searchGubun = dto.getSearchGubun();
		String searchData = dto.getSearchData();
		// 공백체크 - 검색을 하지 않을때는 searchGubun, searchData가 null 처리 되기 때문에 공백체크 필수
		if(dto.getSearchGubun() == null || dto.getSearchGubun().trim().equals("")) {
			searchGubun = "";
		}
		if(dto.getSearchData() == null || dto.getSearchData().trim().equals("")) {
			searchData = "";
		}
		if(searchGubun.equals("") || searchData.equals("")) {
			searchGubun = "";
			searchData = "";
		}	
		//--------------------------------------------------------------------------------------- (1)
		//---------------------------------------------------------------------------------------페이징(start)
		String pageNumber_ = request.getParameter("pageNumber");
		// 넘어오는 String pageNumber_ 숫자인지 체크 확인 필요
		if(pageNumber_ == null || pageNumber_.trim().equals("")) {
			pageNumber_ = "1"; // pageNumber가 없을 경우 무조건 제일 첫 페이지로 보냄
		}
		int pageNumber = Integer.parseInt(pageNumber_);
		
		MemberNewDAO dao = new MemberNewDAO();
		
		MemberNewDTO arguDto = new MemberNewDTO(); // 검색과 관련된 내용을 담기 위한 DTO객체		
		arguDto.setSearchGubun(searchGubun);
		arguDto.setSearchData(searchData);
		
		int pageSize = 1; // 페이지당 게시글 개수
		int blockSize = 10; // 페이징 번호 개수
		int totalRecord = dao.getTotalRecord(arguDto) ; // DB 전체 레코드 개수(검색 포함)
		int startRecord = pageSize * (pageNumber -1) + 1;
		int lastRecord = pageSize * pageNumber;		
		if(lastRecord > totalRecord) { // lastRecord가 totalRecord보다 커졌을 경우 totalRecord로 설정
			lastRecord = totalRecord;
		}
		int dispNo = totalRecord - pageSize * (pageNumber - 1); // 화면상에 보여주기 위한 일련번호
		
		int totalPage = 0;
		int startPage = 1;
		int lastPage = 1;
		
		if(totalRecord > 0) {
			totalPage = totalRecord / pageSize + (totalRecord % pageSize == 0 ? 0 : 1);
			startPage = (pageNumber / blockSize - (pageNumber % blockSize != 0 ? 0 : 1)) * blockSize + 1;
			lastPage = startPage + blockSize - 1;
			
			if(lastPage > totalPage) {
				lastPage = totalPage; // pageSize만큼 레코드가 등록 되어 있지 않을때, 나머지 페이지 번호들을 보여줄 필요가 없기 때문에 totalPage로 설정
			}
			
		} else { // DB에 레코드가 하나도 없을 때
			totalPage = 1;
		}	
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("blockSize",blockSize);
		model.addAttribute("totalRecord",totalRecord);
		model.addAttribute("startRecord",startRecord);
		model.addAttribute("lastRecord",lastRecord);
		model.addAttribute("dispNo",dispNo);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("lastPage",lastPage);	
		
		// startRecord ~ lastRecord 를 getSelectAll에 보내기 위해 arguDto에 담는 과정
		arguDto.setStartRecord(startRecord);
		arguDto.setLastRecord(lastRecord);
		
		//---------------------------------------------------------------------------------------페이징(end)
		List<MemberNewDTO> list = dao.getSelectAll(arguDto);		
		model.addAttribute("list",list);
		//---------------------------------------------------------------------------------------검색 (2)
		model.addAttribute("searchGubun",searchGubun);
		model.addAttribute("searchData",searchData);
		//--------------------------------------------------------------------------------------- (2)
		//---------------------------------------------------------------------------------------검색 (3)
		String searchGubunEn = "";
		try {
			searchGubunEn = URLEncoder.encode(searchGubun,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			searchGubunEn = "";
		}
		String searchDataEn = "";
		try {
			searchDataEn = URLEncoder.encode(searchData,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			searchDataEn = "";
		}
		String searchQueryString = "searchGubun=" + searchGubunEn + "&searchData=" + searchDataEn;
		
		model.addAttribute("searchQueryString",searchQueryString);
		//--------------------------------------------------------------------------------------- (3)
		return "memberNew/list"; // servlet_context.xml 에 접두사(WEB_INF/views), 접미사(.jsp) 등록 되어있음
	}
	@RequestMapping("/listSearch")
	public String listSearch(
		Model model,
		@ModelAttribute MemberNewDTO dto
		) {
		// 공백체크
		String searchGubun = dto.getSearchGubun();
		String searchData = dto.getSearchData();
		if(dto.getSearchGubun() == null || dto.getSearchGubun().trim().equals("")) {
			searchGubun = "";
		}
		if(dto.getSearchData() == null || dto.getSearchData().trim().equals("")) {
			searchData = "";
		}
		if(searchGubun.equals("") || searchData.equals("")) {
			searchGubun = "";
			searchData = "";
		}		
		//Encoding 과정 (Decoding은 Encoding -> Decoding 으로 바꿔서 사용)		
		try {
			searchGubun = URLEncoder.encode(searchGubun,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			searchGubun = "";
		}
		try {
			searchData = URLEncoder.encode(searchData,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			searchData = "";
		}		

		// servlet_context.xml 에 접두사(WEB_INF/views), 접미사(.jsp) 등록 되어있음
		return "redirect:/memberNew/list?searchGubun=" + searchGubun + "&searchData=" + searchData; // Redirect로 처리
	}
	@RequestMapping("/view")
	public String view(
		Model model,
		@ModelAttribute MemberNewDTO dto
		) {
		MemberNewDAO dao = new MemberNewDAO();
		MemberNewDTO returnDto = dao.getSelectOne(dto);
		model.addAttribute("dto",returnDto);
		//---------------------------------------------------------------------------------------검색 (1)
		String searchGubun = dto.getSearchGubun();
		String searchData = dto.getSearchData();
		// 공백체크 - 검색을 하지 않을때는 searchGubun, searchData가 null 처리 되기 때문에 공백체크 필수
		if(dto.getSearchGubun() == null || dto.getSearchGubun().trim().equals("")) {
			searchGubun = "";
		}
		if(dto.getSearchData() == null || dto.getSearchData().trim().equals("")) {
			searchData = "";
		}
		if(searchGubun.equals("") || searchData.equals("")) {
			searchGubun = "";
			searchData = "";
		}				
		MemberNewDTO arguDto = new MemberNewDTO(); // 검색과 관련된 내용을 담기 위한 DTO객체		
		arguDto.setSearchGubun(searchGubun);
		arguDto.setSearchData(searchData);
		//--------------------------------------------------------------------------------------- (1)	
		//---------------------------------------------------------------------------------------검색 (2)
		model.addAttribute("searchGubun",searchGubun);
		model.addAttribute("searchData",searchData);
		//--------------------------------------------------------------------------------------- (2)
		//---------------------------------------------------------------------------------------검색 (3)
		String searchGubunEn = "";
		try {
			searchGubunEn = URLEncoder.encode(searchGubun,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			searchGubunEn = "";
		}
		String searchDataEn = "";
		try {
			searchDataEn = URLEncoder.encode(searchData,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			searchDataEn = "";
		}
		String searchQueryString = "searchGubun=" + searchGubunEn + "&searchData=" + searchDataEn;
		
		model.addAttribute("searchQueryString",searchQueryString);
		//--------------------------------------------------------------------------------------- (3)		
		return "memberNew/view";
	}
	@RequestMapping("/chuga")
	public String chuga() {
		
		return "memberNew/chuga";
	}
	@RequestMapping("/sujung")
	public String sujung(
		Model model,
		@ModelAttribute MemberNewDTO dto
		) {
		MemberNewDAO dao = new MemberNewDAO();
		MemberNewDTO returnDto = dao.getSelectOne(dto);
		
		model.addAttribute("dto",returnDto);
		return "memberNew/sujung";
	}
	@RequestMapping("/sakje")
	public String sakje(
		Model model,
		@ModelAttribute MemberNewDTO dto
		) {
		MemberNewDAO dao = new MemberNewDAO();
		MemberNewDTO returnDto = dao.getSelectOne(dto);
		
		model.addAttribute("dto",returnDto);
		return "memberNew/sakje"; // forwarding 
	}
	@RequestMapping("/chugaProc")
	public String chugaProc(
		Model model,
		@ModelAttribute MemberNewDTO dto	
		) {
		if(!dto.getPasswd().equals(dto.getPasswdChk())) {
			System.out.println("password & passwdChk mismatch");
			return "redirect:/memberNew/chuga";	
		}
		MemberNewDAO dao = new MemberNewDAO();
		int result = dao.setInsert(dto);
		if(result > 0) {
			return "redirect:/memberNew/list"; // URL덮어쓰기 - redirect
		} else {
			return "redirect:/memberNew/chuga";			
		}
	}
	@RequestMapping("/sujungProc")
	public String sujungProc(
		Model model,
		@ModelAttribute MemberNewDTO dto	
		) {
		MemberNewDAO dao = new MemberNewDAO();
		int result = dao.setUpdate(dto);
		if(result > 0) {
			return "redirect:/memberNew/view?no=" + dto.getNo();
		} else {
			return "redirect:/memberNew/sujung?no=" + dto.getNo();			
		}
	}
	@RequestMapping("/sakjeProc")
	public String sakjeProc(
		Model model,
		@ModelAttribute MemberNewDTO dto	
		) {
		MemberNewDAO dao = new MemberNewDAO();
		int result = dao.setDelete(dto);
		if(result > 0) {
			return "redirect:/memberNew/list";
		} else {
			return "redirect:/memberNew/sakje?no=" + dto.getNo();			
		}
	}
}

