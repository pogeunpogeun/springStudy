package com.hhj.springStudy.member.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hhj.springStudy._common.MultipartUpload;
import com.hhj.springStudy.member.model.dao.MemberDAO;
import com.hhj.springStudy.member.model.dto.MemberDTO;
import com.hhj.springStudy.product.model.dto.ProductDTO;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Inject
	MemberDAO memberDao;
	String returnValue = "member";
	// list ---------------------------------------------------
	@RequestMapping("/list")
	public String list(
		Model model
		) {		
		String title = "회원목록";
		List<MemberDTO> list = memberDao.getSelectAll();
		model.addAttribute("title", title);
		model.addAttribute("list", list);
		return returnValue + "/list";
	}
	// view ---------------------------------------------------
	@RequestMapping("/view")
	public String view(
		Model model,
		@ModelAttribute MemberDTO dto
		) {		
		MemberDTO returnDto = memberDao.getSelectOne(dto);
		String title = "회원상세보기";
		model.addAttribute("title", title);
		model.addAttribute("dto", returnDto);
		return returnValue + "/view";
	}
	// chuga ---------------------------------------------------	
	@RequestMapping("/chuga")
	public String chuga(
		Model model
		) {
		String title = "회원등록";
		
		model.addAttribute("title", title);
		return returnValue + "/chuga";
	}
	// sujung ---------------------------------------------------
	@RequestMapping("/sujung")
	public String sujung(
		Model model,
		@ModelAttribute MemberDTO dto
		) {
		MemberDTO returnDto = memberDao.getSelectOne(dto);
		String title = "회원수정";	
		model.addAttribute("title", title);
		model.addAttribute("dto", returnDto);
		return returnValue + "/sujung";
	}
	// sakje ---------------------------------------------------
	@RequestMapping("/sakje")
	public String sakje(
		Model model,
		@ModelAttribute MemberDTO dto
		) {
		MemberDTO returnDto = memberDao.getSelectOne(dto);
		String title = "회원삭제";		
		model.addAttribute("title", title);
		model.addAttribute("dto", returnDto);
		return returnValue + "/sakje";
	}
	// chugaProc ---------------------------------------------------
		@RequestMapping("/chugaProc")
		public String chugaProc(
			Model model,
			@ModelAttribute MemberDTO dto
			) {
			String jumin = dto.getJumin1() + dto.getJumin2();
			dto.setJumin(jumin);
			
			int result = memberDao.setInsert(dto);
			if(result > 0) {
				return "redirect:/member/list";
			} else {
				return "redirect:/member/chuga";
			}
			
		}
	// attachChuga ---------------------------------------------------
		@RequestMapping("/chugaAttach")
		public String chugaAttach(
			Model model
			) {

			String title = "회원등록(attach)";		
			model.addAttribute("title", title);

			return returnValue + "/chugaAttach";			
		}
	// sujungProc ---------------------------------------------------
		@RequestMapping("/sujungProc")
		public String sujungProc(
			Model model,
			@ModelAttribute MemberDTO dto
			) {
			int result = memberDao.setUpdate(dto);
			
			if(result > 0) {
				return "redirect:/member/list";
			} else {
				return "redirect:/member/sujung?no=" + dto.getNo();
			}
		}
		
	// attachChugaProc ---------------------------------------------------
		@RequestMapping("/chugaAttachProc")
		public String chugaAttachProc(
			Model model,
			HttpServletRequest request,
			@ModelAttribute MemberDTO dto,
			@RequestParam("file") List<MultipartFile> multiFileList
			) {
			String jumin = dto.getJumin1() + dto.getJumin2();
			dto.setJumin(jumin);
			
			MultipartUpload mu = new MultipartUpload();
			List<String> list = mu.attachProc(multiFileList, "/springStudy/member");
			
			String attachInfo = "";
			for(int i = 0; i < list.size(); i++) {
				attachInfo += "|" + list.get(i);
			}
			attachInfo = attachInfo.substring(1);
			dto.setAttachInfo(attachInfo);
			
			int result = memberDao.setInsert(dto);
			if(result > 0) {
				return "redirect:/member/list";
			} else {
				return "redirect:/member/chuga";
			}

			}
			
	// sakjeProc ---------------------------------------------------
		@RequestMapping("/sakjeProc")
		public String sakjeProc(
			Model model,
			@ModelAttribute MemberDTO dto
			) {
			
			int result = memberDao.setDelete(dto);
			if(result > 0) {
				return "redirect:/member/list";
			} else {
				return "redirect:/member/sakje?no=" + dto.getNo();
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
	// loginProc ---------------------------------------------------	
		@RequestMapping("/loginProc")
		public String loginProc(
			Model model,
			HttpSession session,
			@ModelAttribute MemberDTO arguDto
			) {
			int result = memberDao.getLogin(arguDto);
			
			if(result > 0) {
				session.setAttribute("sessionNo", result);
				return "redirect:/member/list";
			} else {
				return "redirect:/member/login";
			}
		}
	// loginout ---------------------------------------------------
		@RequestMapping("/logout")
		public String logout(
			Model model,
			HttpSession session,
			@ModelAttribute MemberDTO arguDto
			) {
			session.invalidate();
			return "redirect:/member/login";			
		}	
	// download ---------------------------------------------------
		@RequestMapping("/download")
		public void download(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response
			) {
			String no_ = request.getParameter("no");
			String num_ = request.getParameter("num");
			int no = Integer.parseInt(no_);
			int num = Integer.parseInt(num_);
					
			MemberDTO arguDto = new MemberDTO();
			arguDto.setNo(no);
			
			MemberDTO returnDto = memberDao.getSelectOne(arguDto);
			String[] imsiArray = returnDto.getAttachInfo().split("[|]");
			
			for(int i = 0; i < imsiArray.length; i++) {
				if(i == num) {
					String[] tempArray = imsiArray[i].split(",");
					
					MultipartUpload mu = new MultipartUpload();
					mu.attachDownload(response, tempArray[0], tempArray[1], "/springStudy/member");
				}
			}
					
		}
		
}
