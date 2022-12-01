package com.hhj.springStudy.memo.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhj.springStudy.memo.model.dao.MemoDAO;
import com.hhj.springStudy.memo.model.dto.MemoDTO;
import com.hhj.springStudy.memo.service.MemoService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/memo/")
public class MemoController {
	
	@Inject
	MemoDAO memoDao;
	@Inject
	MemoService memoService;
	String returnValue = "memo";
	
	int imsiProcGubun = 1; // imsiProcGubun = 0: dao, imsiProcGubun = 1: service
	
	// list ----------------------------------------------
	@RequestMapping("list")
	public String getSelectAll(
		Model model
		) {
		List<MemoDTO> list = memoDao.getSelectAll();
		
		if(imsiProcGubun == 0) { // dao 처리
			list = memoDao.getSelectAll();
		} else { // service 처리
			list = memoService.getSelectAll();
		}
		
		String title = "메모목록";
		model.addAttribute("title",title);
		model.addAttribute("list",list);
		return returnValue + "/list";
	}
	// view ----------------------------------------------
	@RequestMapping("view")
	public String getSelectOne(
		Model model,
		@ModelAttribute MemoDTO arguDto
		) {
		MemoDTO returnDto = memoDao.getSelectOne(arguDto);
		
		if(imsiProcGubun == 0) { // dao 처리
			returnDto = memoDao.getSelectOne(arguDto);
		} else { // service 처리
			returnDto = memoService.getSelectOne(arguDto);
		}
		
		String title = "메모상세보기";
		model.addAttribute("title",title);
		model.addAttribute("dto", returnDto);
		return returnValue + "/view";
	}
	// chuga ----------------------------------------------
	@RequestMapping("chuga")
	public String setInsert(
		Model model
		) {
		String title = "메모추가";
		model.addAttribute("title",title);
		return returnValue + "/chuga";
	}
	// sujung ----------------------------------------------
	@RequestMapping("sujung")
	public String setUpdate(
		Model model,
		@ModelAttribute MemoDTO arguDto
		) {
		MemoDTO returnDto = memoDao.getSelectOne(arguDto);
		
		if(imsiProcGubun == 0) { // dao 처리
			returnDto = memoDao.getSelectOne(arguDto);
		} else { // service 처리
			returnDto = memoService.getSelectOne(arguDto);
		}
		
		String title = "메모수정";
		model.addAttribute("dto", returnDto);
		model.addAttribute("title",title);
		return returnValue + "/sujung";
	}
	// sakje ----------------------------------------------
	@RequestMapping("sakje")
	public String setDelete(
		Model model,
		@ModelAttribute MemoDTO arguDto
		) {		
		MemoDTO returnDto = memoDao.getSelectOne(arguDto);
		
		if(imsiProcGubun == 0) { // dao 처리
			returnDto = memoDao.getSelectOne(arguDto);
		} else { // service 처리
			returnDto = memoService.getSelectOne(arguDto);
		}
		
		String title = "메모삭제";
		model.addAttribute("dto", returnDto);
		model.addAttribute("title",title);
		return returnValue + "/sakje";
	}
	// chugaProc ----------------------------------------------
	@RequestMapping("chugaProc")
	public String chugaProc(
		Model model,
		@ModelAttribute MemoDTO arguDto
		) {
		int result; 
		
		if(imsiProcGubun == 0) { // dao 처리
			result = memoDao.setInsert(arguDto);
		} else { // service 처리
			result = memoService.setInsert(arguDto);
		}
		
		if(result > 0) {
			return "redirect:/" + returnValue + "/list";
		} else {
			return "redirect:/" + returnValue + "/chuga";
		}
	}
	// sujungProc ----------------------------------------------
	@RequestMapping("sujungProc")
	public String sujungProc(
		Model model,
		@ModelAttribute MemoDTO arguDto
		) {
		int result;
		
		if(imsiProcGubun == 0) { // dao 처리
			result = memoDao.setUpdate(arguDto);
		} else { // service 처리
			result = memoService.setUpdate(arguDto);
		}
		
		if(result > 0) {
			return "redirect:/" + returnValue + "/view?no=" + arguDto.getNo();
		} else {
			return "redirect:/" + returnValue + "/sujung?no=" + arguDto.getNo();
		}
	}
	// sakjeProc ----------------------------------------------
	@RequestMapping("sakjeProc")
	public String sakjeProc(
		Model model,
		@ModelAttribute MemoDTO arguDto
		) {
		int result;
		
		if(imsiProcGubun == 0) { // dao 처리
			result = memoDao.setDelete(arguDto);
		} else { // service 처리
			result = memoService.setDelete(arguDto);
		}
		
		if(result > 0) {
			return "redirect:/" + returnValue + "/list";
		} else {
			return "redirect:/" + returnValue + "/sakje?no=" + arguDto.getNo();
		}
	}
}
