package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.MemberDto;

public class BoardDelete extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		MemberDto userInfo = (MemberDto) session.getAttribute("userInfo");
		
		if(userInfo == null) {
			ModelAndView mav = new ModelAndView("/WEB-INF/board/result.jsp");
			mav.addObject("msg", "먼저 로그인 하셔야합니다.");
			mav.addObject("url", "Login.do");
			return mav;
		}
		
		Long no = Long.parseLong(request.getParameter("no"));
		
		return new ModelAndView("/WEB-INF/board/delete.jsp", "no", no);
	}
	
}
