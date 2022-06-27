package cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Cooker {
	private Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();

	public Cooker(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookieMap.put(cookies[i].getName(), cookies[i]);
			}
		}
	}

	public static Cookie createCookie(String name, String value) throws UnsupportedEncodingException {
		return new Cookie(name, URLEncoder.encode(value, "UTF-8"));
	}

	public static Cookie createCookie(String name, String value, int maxAge) throws UnsupportedEncodingException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
		cookie.setMaxAge(maxAge);
		return cookie;
	}

	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}

	public String getValue(String name) throws UnsupportedEncodingException {
		Cookie cookie = cookieMap.get(name);
		if (cookie != null) {
			return URLDecoder.decode(cookie.getValue(), "UTF-8");
		} else {
			return null;
		}
	}

	public boolean isExist(String name) { // 쿠키 존재 유무 확인
		return cookieMap.get(name) != null;
	}

}
