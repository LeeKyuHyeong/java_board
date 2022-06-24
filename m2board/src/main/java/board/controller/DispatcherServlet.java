package board.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	
	// 클래스 파일이 변경되면 자동 리로딩한다 톰캣이
	// 운영중에는 건드리기 힘든데 방법이없나 => 자동으로 리로딩해주는거다 다운시키고 다시 키고 하는게 아니다
	// service에 init을 넣어서 쓰면 파일 변경사항이 있을때 마다 자동으로 업데이트 되는것 아니냐
	// 그렇게되면 1개의 요청만 반응되고 그 뒤 요청들은 반응이 안된다 init으로 초기화는 필수다.
	
	private Map<String, AbstractController> actionMap = new HashMap<String, AbstractController>();
	
	@Override
	public void init() throws ServletException {
		String props = this.getClass().getResource("dispatcher.properties").getPath();
		
		Properties pr = new Properties();
		
		FileInputStream FIS = null;
		
		try {
			FIS = new FileInputStream(props);
			pr.load(FIS);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(FIS != null) try {FIS.close();} catch(Exception e) {};
		}
		
		for(Object obj : pr.keySet()) {
			String key = ((String) obj).trim();					//exp)/BoardInsert.do
			String className = pr.getProperty(key).trim();		//exp)board.controller.BoardInsert
			
			try {
				Class actionClass =  Class.forName(className);

				AbstractController controller = (AbstractController) actionClass.getConstructor().newInstance();
				
				actionMap.put(key, controller);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
		
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String action = requestURI.substring(request.getContextPath().length());
		System.out.println("requestURI : " + requestURI);
		System.out.println("action : " + action);
		
		AbstractController controller = null;
			
		ModelAndView mav = null;				
		
		controller = actionMap.get(action);
		
		mav = controller.handleRequestInternal(request, response);

		if(mav != null) {
			String viewName = mav.getViewName();
			
			if(viewName.startsWith("redirect:")) {
				response.sendRedirect(viewName.substring("redirect:".length()));
				return;
			}
			
			Map<String, Object> map = mav.getModel();
			
			for(String key : map.keySet()) {
				request.setAttribute(key, map.get(key));
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(mav.getViewName());
			dispatcher.forward(request, response);
			
		} else {
			System.out.println("DispatcherServlet에서 길을 잃었다~");
		}		
	}
}
