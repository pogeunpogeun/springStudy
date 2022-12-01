package com.hhj.springStudy._test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/test01") //http://localhost:8091/springStudy/test01
	public String test01() {
		return "_test/test01/exam01"; //WEB-INF/views/_test/test01/exam01.jsp
	}
	@RequestMapping("/test01Proc")
	public String test01Proc(
			Model model,
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="jumin1", defaultValue="") String jumin1,
			@RequestParam(value="jumin2", defaultValue="") String jumin2
		) {
//		System.out.println("name : " + name);
//		System.out.println("jumin1 : " + jumin1);
//		System.out.println("jumin2 : " + jumin2);
		model.addAttribute("name",name);
		model.addAttribute("jumin1",jumin1);
		model.addAttribute("jumin2",jumin2);
		return "_test/test01/exam01Result";
	}
	@RequestMapping("/test02")
	public String test02() {
		return "_test/test02/exam02";
	}
	@RequestMapping("/test02Proc")
	public String test02Proc(
		Model model,
		@RequestParam(value="name", defaultValue="") String name,
		@RequestParam(value="gender", defaultValue="") String gender,
		@RequestParam(value="jumin1", defaultValue="") String jumin1,
		@RequestParam(value="jumin2", defaultValue="") String jumin2
		) {
		model.addAttribute("name",name);
		model.addAttribute("gender",gender);
		model.addAttribute("jumin1",jumin1);
		model.addAttribute("jumin2",jumin2);
		
		return "_test/test02/exam02Result";		
	}
	@RequestMapping("/test03")
	public String test03() {
		return "_test/test03/exam03";
	}
	@RequestMapping("/test03Proc")
	public String test03Proc(
		Model model,
		HttpServletRequest request
//		@RequestParam(value="name", defaultValue="") String name,
//		@RequestParam(value="gender", defaultValue="") String gender,
//		@RequestParam(value="age", defaultValue="") String age,
//		@RequestParam(value="jumin1", defaultValue="") String jumin1,
//		@RequestParam(value="jumin2", defaultValue="") String jumin2
		) {
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String jumin1 = request.getParameter("jumin1");
		String jumin2 = request.getParameter("jumin2");
		String age_ = request.getParameter("age");
		
		int age = Integer.parseInt(age_);
		
		model.addAttribute("name",name);
		model.addAttribute("gender",gender);
		model.addAttribute("age",age);
		model.addAttribute("jumin1",jumin1);
		model.addAttribute("jumin2",jumin2);
		
		return "_test/test03/exam03Result";
	}
	@RequestMapping("/test04")
	public String test04() {
		return "_test/test04/exam04";
	}
	@RequestMapping("/test04Proc")
	public String test04Proc(
		Model model,
		@ModelAttribute Test04DTO dto
		) {
		//String name = dto.getName();
		String gender = dto.getGender();
		String jumin1 = dto.getJumin1();
		String jumin2 = dto.getJumin2();
		int age = dto.getAge();
		
		model.addAttribute("name",dto.getName());
		model.addAttribute("gender",gender);
		model.addAttribute("age",age);
		model.addAttribute("jumin1",jumin1);
		model.addAttribute("jumin2",jumin2);
		
		return "_test/test03/exam03Result";
	}
	@RequestMapping("/test05")
	public String test05() {
		return "_test/test05/exam05";
	}
	@RequestMapping("/test05Proc")
	public String test05Proc(
		Model model,
		@ModelAttribute Test05DTO dto
		) {
		String name = dto.getName();
		int kor = dto.getKor();
		int eng = dto.getEng();
		int mat = dto.getMat();
		int sci = dto.getSci();
		int his = dto.getHis();
		
		dto.setTot(kor + eng + mat + sci + his);
		dto.setAvg(dto.getTot() / 5.0);
		
		model.addAttribute("dto",dto);		
		return "_test/test05/exam05Result";
	}
	@RequestMapping("/test06")
	public String test06() {
		return "_test/test06/exam06";
	}
	@RequestMapping("/test06Proc")
	public String test06Proc(
		Model model,
		@ModelAttribute Test06DTO dto
		) {
		dto.setSalePrice(dto.getpPrice() - (dto.getpPrice() * dto.getSale()) / 100);
		
		model.addAttribute("dto",dto);
		return "_test/test06/exam06Result";
	}
	@RequestMapping("/test07")
	public String test07() {
		return "_test/test07/exam07";
	}
	@RequestMapping("/test07Proc")
	public ModelAndView test07Proc(
		Model model,
		@ModelAttribute Test07DTO dto
		) {
		dto.setSalePrice(dto.getpPrice() - (dto.getpPrice() * dto.getSale()) / 100);
		
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		//return new ModelAndView("_test/test07/exam07Result","map",map);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("_test/test07/exam07Result");
		mv.addObject("map", map);
		return mv;
	}
	@RequestMapping("/test08")
	public String test08() {
		return "_test/test08/exam08";
	}
	@RequestMapping("/test08Proc")
	public String test08Proc(
		Model model,
		String id,
		String passwd
		) {
		String dbId = "hong";
		String dbPw = "1234";
		String result = "";
		if(id.equals(dbId) && passwd.equals(dbPw)) {
			result = "로그인 성공";
		} else {
			result = "로그인 실패";
		}
		model.addAttribute("id",id);
		model.addAttribute("passwd",passwd);
		model.addAttribute("result",result);
		return "_test/test08/exam08Result";
	}
	@RequestMapping("/test09")
	public String test09() {
		return "_test/test09/exam09";
	}
	@RequestMapping("/test09Proc")
	public @ResponseBody Map<String, String> test09Proc( // 객체를 반환할때는 @ResponseBody 붙여야 함
		Model model,
		String id,
		String name,
		String email
		) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("email", email);
		
		return map;
	}
	@RequestMapping("/test10")
	public String test10(
		Model model	
		) {
		String attachPath = "C:/HHJ/attach";
		String uploadPath = attachPath + "/imsi";
		
		ArrayList<String> list = new ArrayList<>();
		
		java.io.File f1 = new java.io.File(uploadPath);
		java.io.File[] array = f1.listFiles();
			for(int i = 0; i < array.length; i++) {
				String imsi = "";
				if(array[i].isFile()) {
					imsi = "파일^" + array[i].getPath();
				} else {
					imsi = "폴더^" + array[i].getPath();
				}
				list.add(imsi);
			}
			model.addAttribute("list",list);
			return "_test/test10/exam10";
			
	}
	@RequestMapping("/test10Proc")
	public String test10Proc(
		Model model,
		HttpServletRequest request
		) {
		String fileInfo = request.getParameter("fileInfo");
		
		java.io.File f1 = new java.io.File(fileInfo);
		if(f1.exists()) {
			f1.delete();
		} else {
			System.out.println("존재하지 않습니다.");
		}
		return "redirect:/test10";
	}
}
