package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDao;
import board.model.BoardDto;
import board.model.MemberDto;

public class BoardUpdate extends AbstractController{

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
		
		BoardDao boardDao = BoardDao.getInstance();
		
		BoardDto boardDto = boardDao.getBoardView(no);
		
		if(boardDto != null)
			return new ModelAndView("/WEB-INF/board/update.jsp", "boardDto", boardDto);
		else {
			ModelAndView mav = new ModelAndView("/WEB-INF/board/result.jsp");
			mav.addObject("msg", "삭제된 글입니다.");
			mav.addObject("url", "BoardList.do");
			return mav;
		}
	}
	
}
