package board.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDao;
import board.model.BoardDto;
import board.model.MemberDto;
import cookie.Cooker;

public class BoardList extends AbstractController{

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
		Long pg = 1L;	//pg값이 안넘어오면 default값 1
		if(request.getParameter("pg") != null) {
			pg = Long.parseLong(request.getParameter("pg"));
		}
		int pageSize = 10;						//한 페이지 게시물 수
		long startnum =(pg-1) * pageSize + 1; 	//페이지 시작번호
		long endnum = pg * pageSize;
		
		
		String logInfo = userInfo.getName() + "(" + userInfo.getId() + ")님이 로그인하셨습니다."; 

		BoardDao boardDao = BoardDao.getInstance();
		long recordCnt = boardDao.getRecordCnt();	//전체 게시글 수
		long pageCnt = recordCnt / pageSize;		//전체 페이지 수
		
		if(recordCnt % pageSize != 0) {
			pageCnt++;			//딱 떨어지지 않을때
		}
		
		int blockSize = 10;		//한 블럭 페이지 수
		
		long startPage = (pg-1) / blockSize * blockSize+ 1;			//현재블럭 첫페이지
		long endPage = (pg-1) / blockSize * blockSize + blockSize;	//현재블럭 마지막페이지
		
		if(endPage > pageCnt) endPage = pageCnt;		//마지막페이지 이후의 번호는 찍지않음
		
		List<BoardDto> list = boardDao.getBoardList(startnum, endnum);
		
		ModelAndView mav = new ModelAndView("/WEB-INF/board/list.jsp");
		mav.addObject("logInfo", logInfo);
		mav.addObject("list", list);
		mav.addObject("pageCnt", pageCnt);
		mav.addObject("pg", pg);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		
		return mav;
	}

}
