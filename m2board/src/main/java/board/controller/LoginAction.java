package board.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.MemberDao;
import board.model.MemberDto;
import cookie.Cooker;

public class LoginAction extends AbstractController{

	
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("password");
		
		int setid = 0;
		if(request.getParameter("setid") != null) {
			setid = Integer.parseInt(request.getParameter("setid"));
		}
		
		MemberDto memberDto = new MemberDto();
		memberDto.setId(id);
		memberDto.setPassword(pwd);
		
		MemberDao memberDao = MemberDao.getInstance();
		
		MemberDto userInfo = memberDao.getUser(memberDto);
		
		if(userInfo != null) {		//로그인 성공
			try {
				Cookie cookie = Cooker.createCookie("id", userInfo.getId(), setid == 1 ? 60 * 60 * 24 * 30 : 0);
				response.addCookie(cookie);
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			HttpSession session = request.getSession();			
			session.setAttribute("userInfo", userInfo);			
			
			return new ModelAndView("redirect:BoardList.do");
		} else {					//실패
			ModelAndView mav = new ModelAndView("/WEB-INF/board/result.jsp");
			mav.addObject("msg", "로그인에 실패하였습니다.");
			mav.addObject("url", "Login.do");//			
			return mav;
		}
		
	}
	
}
