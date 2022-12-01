package com.hhj.springStudy.guestBook.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhj.springStudy.guestBook.model.dao.GuestBookDAO;
import com.hhj.springStudy.guestBook.model.dto.GuestBookDTO;


import org.springframework.ui.Model;

@Controller
@RequestMapping("/guestBook")
public class GuestBookController {

	@Inject
	GuestBookDAO dao;
	String resultValue = "guestBook";
	//list-------------------------------------------------
	@RequestMapping("/list")
	public String list(
		Model model
		) {
		List<GuestBookDTO> list = dao.getSelectAll();
		String title = "방명록";
		
		model.addAttribute("list", list);
		model.addAttribute("title", title);
		return resultValue + "/list";
	}
//	//view-------------------------------------------------
//	@RequestMapping("view")
//	public String view(
//		Model model,
//		@ModelAttribute GuestBookDTO dto
//		) {
//		GuestBookDTO returnDto = dao.getSelectOne(dto);
//		
//		String title = "방명록상세보기";	
//		model.addAttribute("dto", returnDto);
//		model.addAttribute("title", title);
//		return resultValue + "view";
//	}
	//chuga-------------------------------------------------
	@RequestMapping("/chuga")
	public String chuga(
		Model model
		) {
		
		String title = "방명록추가";
		model.addAttribute("title", title);
		return resultValue + "/chuga";
	}
	//chugaProc-------------------------------------------------
	@RequestMapping("/chugaProc")
	public String chugaProc(
		Model model,
		@ModelAttribute GuestBookDTO dto
		) {
		int result = dao.setInsert(dto);
		
		if(result > 0) {
			return "redirect:/" + resultValue + "/list";
		} else {
			return "redirect:/" + resultValue + "/chuga";
		}
	}
	//sujung-------------------------------------------------
	@RequestMapping("/sujung")
	   public String sujung(
	      Model model,
	      @ModelAttribute GuestBookDTO arguDto
	      ) {
	   
	   String title = "방명록 수정";
	   GuestBookDTO returnDto = dao.getSelectOne(arguDto);
	   
	   model.addAttribute("title", title);
	   model.addAttribute("dto", returnDto);
	   
	   return resultValue + "/sujung";
	   }
	//sujungProc-------------------------------------------------
	@RequestMapping("/sujungProc")
	public String sujungProc(
		Model model,
		@ModelAttribute GuestBookDTO dto
		) {
		int result = dao.setUpdate(dto);
		
		if(result > 0) {
			return "redirect:/guestBook/list";
		} else {
			return "redirect:/guestBook/sujung?no=" + dto.getNo();
		}
	}
	//sakje-------------------------------------------------
	@RequestMapping("/sakje")
	public String sakje(
		Model model,
		@ModelAttribute GuestBookDTO dto
		) {
		GuestBookDTO returnDto = dao.getSelectOne(dto);
		
		String title = "방명록삭제";
		model.addAttribute("dto", returnDto);
		model.addAttribute("title", title);
		return resultValue + "/sakje";
	}
	//sakjeProc-------------------------------------------------
	@RequestMapping("/sakjeProc")
	public String sakjeProc(
		Model model,
		@ModelAttribute GuestBookDTO dto
		) {
		int result = dao.setDelete(dto);
		
		if(result > 0) {
			return "redirect:/guestBook/list";
		} else {
			return "redirect:/guestBook/sakje?no=" + dto.getNo();
		}
	}
	// loginProc ---------------------------------------------------	
		@RequestMapping("/loginProc")
		public String loginProc(
			Model model,
			HttpSession session,
			@ModelAttribute GuestBookDTO arguDto
			) {
			int result = dao.getLogin(arguDto);
			
			if(result > 0) {
				session.setAttribute("sessionNo", result);
				return "redirect:/guestBook/list";
			} else {
				return "redirect:/guestBook/login";
			}
		}
	// loginout ---------------------------------------------------
		@RequestMapping("/logout")
		public String logout(
			Model model,
			HttpSession session,
			@ModelAttribute GuestBookDTO arguDto
			) {
			session.invalidate();
			return "redirect:/guestBook/login";			
		}		
}
