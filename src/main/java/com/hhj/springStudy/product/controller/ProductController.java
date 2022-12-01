package com.hhj.springStudy.product.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hhj.springStudy._common.MultipartUpload;
import com.hhj.springStudy.guestBook.model.dto.GuestBookDTO;
import com.hhj.springStudy.product.model.dao.ProductDAO;
import com.hhj.springStudy.product.model.dto.ProductDTO;
import com.hhj.springStudy.product.service.ProductService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Inject
	//ProductDAO productDao;
	ProductService productDao;
	String returnValue = "product";
	// list ---------------------------------------------------
	@RequestMapping("/list")
	public String list(
		Model model
		) {		
		String title = "상품목록";
		List<ProductDTO> list = productDao.getSelectAll();
		model.addAttribute("title", title);
		model.addAttribute("list", list);
		return returnValue + "/list";
	}
	// view ---------------------------------------------------
	@RequestMapping("/view")
	public String view(
		Model model,
		@ModelAttribute ProductDTO dto
		) {		
		ProductDTO returnDto = productDao.getSelectOne(dto);
		String title = "상품상세보기";
		model.addAttribute("title", title);
		model.addAttribute("dto", returnDto);
		return returnValue + "/view";
	}
	// chuga ---------------------------------------------------	
	@RequestMapping("/chuga")
	public String chuga(
		Model model
		) {
		String title = "상품등록";
		
		model.addAttribute("title", title);
		return returnValue + "/chuga";
	}
	// sujung ---------------------------------------------------
	@RequestMapping("/sujung")
	public String sujung(
		Model model,
		@ModelAttribute ProductDTO dto
		) {
		ProductDTO returnDto = productDao.getSelectOne(dto);
		String title = "상품수정";	
		model.addAttribute("title", title);
		model.addAttribute("dto", returnDto);
		return returnValue + "/sujung";
	}
	// sakje ---------------------------------------------------
	@RequestMapping("/sakje")
	public String sakje(
		Model model,
		@ModelAttribute ProductDTO dto
		) {
		ProductDTO returnDto = productDao.getSelectOne(dto);
		String title = "상품삭제";		
		model.addAttribute("title", title);
		model.addAttribute("dto", returnDto);
		return returnValue + "/sakje";
	}
	// chugaProc ---------------------------------------------------
		@RequestMapping("/chugaProc")
		public String chugaProc(
			Model model,
			@ModelAttribute ProductDTO dto
			) {
			
			int result = productDao.setInsert(dto);
			if(result > 0) {
				return "redirect:/product/list";
			} else {
				return "redirect:/product/chuga";
			}
			
		}
	// attachChuga ---------------------------------------------------
		@RequestMapping("/chugaAttach")
		public String chugaAttach(
			Model model
			) {

			String title = "상품등록(attach)";		
			model.addAttribute("title", title);

			return returnValue + "/chugaAttach";			
		}
	// sujungProc ---------------------------------------------------
		@RequestMapping("/sujungProc")
		public String sujungProc(
			Model model,
			@ModelAttribute ProductDTO dto
			) {
			int result = productDao.setUpdate(dto);
			
			if(result > 0) {
				return "redirect:/product/list";
			} else {
				return "redirect:/product/sujung?productNo=" + dto.getProductNo();
			}
		}
		
	// chugaAttachProc ---------------------------------------------------
		@RequestMapping("/chugaAttachProc")
		public String chugaAttachProc(
			Model model,
				/* HttpServletRequest request, */
			@ModelAttribute ProductDTO dto,
			@RequestParam("file") List<MultipartFile> multiFileList
			) {
			MultipartUpload mu = new MultipartUpload();
			List<String> list = mu.attachProc(multiFileList, "/springStudy/product");
			
			String attachInfo = "";
			for(int i = 0; i < list.size(); i++) {
				attachInfo += "|" + list.get(i);
			}
			attachInfo = attachInfo.substring(1);
			dto.setAttachInfo(attachInfo);
			
			int result = productDao.setInsert(dto);
			if(result > 0) {
				return "redirect:/product/list";
			} else {
				return "redirect:/product/chuga";
			}

			}
			
	// sakjeProc ---------------------------------------------------
		@RequestMapping("/sakjeProc")
		public String sakjeProc(
			Model model,
			@ModelAttribute ProductDTO dto
			) {
			
			int result = productDao.setDelete(dto);
			if(result > 0) {
				return "redirect:/product/list";
			} else {
				return "redirect:/product/sakje?productNo=" + dto.getProductNo();
			}
			
		}
	// login ---------------------------------------------------
		@RequestMapping("/login")
		public String login(
			Model model
			) {
			String title = "로그인";	
			model.addAttribute("title", title);
			return returnValue + "/login";
		}
//	// loginProc ---------------------------------------------------	
//		@RequestMapping("/loginProc")
//		public String loginProc(
//			Model model,
//			HttpSession session,
//			@ModelAttribute ProductDTO arguDto
//			) {
//			int result = productDao.getLogin(arguDto);
//			
//			if(result > 0) {
//				session.setAttribute("sessionNo", result);
//				return "redirect:/product/list";
//			} else {
//				return "redirect:/product/login";
//			}
//		}
//	// loginout ---------------------------------------------------
//		@RequestMapping("/logout")
//		public String logout(
//			Model model,
//			HttpSession session,
//			@ModelAttribute ProductDTO arguDto
//			) {
//			session.invalidate();
//			return "redirect:/product/login";			
//		}	
}
