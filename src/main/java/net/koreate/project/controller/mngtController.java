package net.koreate.project.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.koreate.project.service.UserService;
import net.koreate.project.util.SearchCriteria;
import net.koreate.project.vo.AuthVO;
import net.koreate.project.vo.MemberVO;

@Controller
@RequestMapping("/mngt/*")
public class mngtController {
	
	@Inject
	UserService us;
	
	@GetMapping("memberList")
	public String memberList(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		model.addAttribute("memberList", us.getMemberById(cri));
		model.addAttribute("pageMaker", us.getPageMaker(cri));
		return "mngt/memberList";
	}
	
	@PostMapping("user/delete")
	@ResponseBody
	public String MngtdeleteYN(MemberVO vo) throws Exception{
		System.out.println(vo);
		us.mngtDeleteYN(vo);
		return vo.getU_withdraw();
	}
	
	@PostMapping("user/changeAuth")
	@ResponseBody
	public List<AuthVO> changeAut(AuthVO auth) throws Exception{
		System.out.println(auth);
		List<AuthVO> authList = us.updateAuth(auth);
		return authList;
	}
	
	@GetMapping("boardList")
	public String boardList() {
		return "mngt/boardList";
	}

}
