package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.MemberDao;

public class CheckID extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("user_id");
		
		MemberDao memberDao = MemberDao.getInstance();
		
		int cnt = memberDao.checkID(id);	

		return new ModelAndView("/WEB-INF/board/checkid.jsp","cnt",cnt);
	}

}
