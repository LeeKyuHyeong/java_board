package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.MemberDao;
import board.model.MemberDto;

public class MemberRegisterAction extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = new MemberDto();
		
		memberDto.setId(request.getParameter("user_id"));
		memberDto.setName(request.getParameter("user_name"));
		memberDto.setPassword(request.getParameter("user_pw"));
		memberDto.setBirth(request.getParameter("user_birth"));
		memberDto.setPhone(request.getParameter("user_phone"));
		memberDto.setZipcode(request.getParameter("zipcode"));
		memberDto.setAddress1(request.getParameter("address1"));
		memberDto.setAddress2(request.getParameter("address2"));
		
		System.out.println(memberDto);
		MemberDao memberDao = MemberDao.getInstance();
				
		if(memberDao.registerMember(memberDto)) {	//회원 등록 성공
			return new ModelAndView("/WEB-INF/board/success.jsp", "memberDto", memberDto);
		} else {	//회원 등록 실패
			ModelAndView mav = new ModelAndView("/WEB-INF/board/result.jsp");
			mav.addObject("msg", request.getParameter("user_id") + "회원 등록에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
			return mav;
		}			
	}	
}
