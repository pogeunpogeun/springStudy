package com.hhj.springStudy.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhj.springStudy.cart.model.dao.CartDAO;
import com.hhj.springStudy.cart.model.dto.CartDTO;
import com.hhj.springStudy.cart.service.CartService;
import com.hhj.springStudy.memo.service.MemoService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Inject
	CartDAO cartDao;
	@Inject
	CartService cartService;
	String returnValue = "cart";
	
	int imsiProcGubun = 1; // imsiProcGubun = 0: dao, imsiProcGubun = 1: service
	
	// list ----------------------------------------------
	@RequestMapping("list")
	public String getSelectAll(
		Model model
		) {
		List<CartDTO> list = cartDao.getSelectAll();
		
		if(imsiProcGubun == 0) { // dao 처리
			list = cartDao.getSelectAll();
		} else { // service 처리
			list = cartService.getSelectAll();
		}
		
		String title = "장바구니";
		model.addAttribute("title",title);
		model.addAttribute("list",list);
		return returnValue + "/list";
	}
	// chugaProc ----------------------------------------------
	@RequestMapping("chugaProc")
	public String chugaProc(
		Model model,
		@ModelAttribute CartDTO arguDto
		) {
		int result = cartService.setInsert(arguDto);
	
		if(result > 0) {
			return "redirect:/" + returnValue + "/list";
		} else {
			return "redirect:/" + returnValue + "/chuga";
		}
	}
	// chugaProc ----------------------------------------------
	@RequestMapping("sujungProc")
	public String sujungProc(
		Model model,
		HttpServletRequest request
		) {
		String totalCounter_ = request.getParameter("totalCounter");
		int totalCounter = Integer.parseInt(totalCounter_);
		
		List<String> list = new ArrayList<>();
		
		for(int i = 0; i < totalCounter; i++) {
			String cartNo_ = request.getParameter("cartNo_" + i);
			String amount_ = request.getParameter("amount_" + i);
			
			int cartNo = Integer.parseInt(cartNo_);
			int amount = Integer.parseInt(amount_);
			
			CartDTO arguDto = new CartDTO();
			arguDto.setAmount(amount);
			arguDto.setCartNo(cartNo);
			
			int imsiResult = cartService.setUpdate(arguDto);	
			if(imsiResult <= 0) {
				list.add(cartNo + "," + amount);
			}
		}
		return "redirect:/" + returnValue + "/list";
	}
	// chugaProc ----------------------------------------------
		@RequestMapping("sakjeProc")
		public String sakjeProc(
			Model model,
			@ModelAttribute CartDTO arguDto
			) {
			int result = cartService.setInsert(arguDto);
		
			if(result > 0) {
				return "redirect:/" + returnValue + "/list";
			} else {
				return "redirect:/" + returnValue + "/chuga";
			}
		}
	// cartClear ----------------------------------------------
		@RequestMapping("clearProc")
		public String clearProc(
			Model model,
			HttpSession session,
			int memberNo
			) {
			if(session.getAttribute("sessionNo") != null) {
				int sessionNo = (int) session.getAttribute("sessionNo");
				if(sessionNo == memberNo) {
					int result = cartService.cartClear(memberNo);				
				}
			
			}
			return "redirect:/" + returnValue + "/list";
		}
}
