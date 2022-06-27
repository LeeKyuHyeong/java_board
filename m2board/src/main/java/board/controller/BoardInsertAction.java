package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDao;
import board.model.BoardDto;
import board.model.MemberDto;

public class BoardInsertAction extends AbstractController{

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
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BoardDto boardDto = new BoardDto();
		
		boardDto.setTitle(title);
		boardDto.setContent(content);
		boardDto.setMemberDto(userInfo);
		
		
		BoardDao boardDao = BoardDao.getInstance();
		if(boardDao.insertBoard(boardDto)) {
			return new ModelAndView("redirect:BoardList.do");
		} else {
			ModelAndView mav = new ModelAndView("/WEB-INF/board/result.jsp");
			mav.addObject("msg", "게시글 등록에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
			return mav;
		}
	}

}
