package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDao;
import board.model.MemberDto;

public class BoardDeleteAction extends AbstractController{

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
		
		ModelAndView mav = new ModelAndView("/WEB-INF/board/result.jsp");
		if(boardDao.deleteBoard(no)) {
			mav.addObject("msg", "게시글 삭제되었습니다.");
			mav.addObject("url", "BoardList.do");
		} else {
			mav.addObject("msg", "게시글 삭제에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
		}		
		return mav;
	}
}
