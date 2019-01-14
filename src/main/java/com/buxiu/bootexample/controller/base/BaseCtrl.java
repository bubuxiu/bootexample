package com.buxiu.bootexample.controller.base;

import java.io.UnsupportedEncodingException;


//import net.sf.json.JsonConfig;
//import net.sf.json.util.PropertyFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;




public class BaseCtrl {

	protected Logger log = LoggerFactory.getLogger(BaseCtrl.class);

//	@Qualifier("restTemplate")
//	@Autowired
//	protected RestTemplate restTemplate;

//	protected JsonConfig jsonConfig = new JsonConfig();

//	public BaseCtrl() {
//		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
//			public boolean apply(Object source, String name, Object value) {
////				if (value == null) {
////                    return true;
////                }
//				if (name.equals("curpage") || name.equals("pagesize")
//						|| name.equals("pagestart") || name.equals("sortord")
//						|| name.equals("term") || name.equals("password")
//						|| name.equals("newpass")) {
//					return true;
//				} else {
//					return false;
//				}
//			}
//		});
//	}
//
//	public void setRestTemplate(RestTemplate restTemplate) {
//		ClientHttpRequestInterceptor interceptor = new BaseInterceptor();
//		List<ClientHttpRequestInterceptor> list = new ArrayList<ClientHttpRequestInterceptor>();
//		list.add(interceptor);
//		restTemplate.setInterceptors(list);
//		this.restTemplate = restTemplate;
//	}
//
//	public RestTemplate getRestTemplate() {
//		return restTemplate;
//	}

	public HttpHeaders newHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	public String encodeStr(String str) {
		if (str == null)
			return "";
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
			// return URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public boolean isNull(String str) {
		return str == null || "".equals(str);
	}

	public String isNull(String str, String defval) {
		return isNull(str) ? defval : str;
	}

	public int isNull(String str, int defval) {
		return isNull(str) ? defval : Integer.parseInt(str);
	}

	 


	/**
	 * 返回文件保存路径
	 * 
	 * @param type
	 *            文件所属资源的类型
	 * @param id
	 *            资源id
	 * @param subtype
	 *            资源子类别
	 * @return
	 */
	protected String realPath(String type, String id, String subtype) {
		return "/public/" + type + "/" + id + "/" + subtype;
	}

	protected String realPath(String type, int id, String subtype) {
		return "/public/" + type + "/" + id + "/" + subtype;
	}

	/**
	 * 返回文件的真实路径
	 * 
	 * @param type
	 *            文件所属资源的类型
	 * @param id
	 *            资源id
	 * @param subtype
	 *            资源子类别
	 * @param filename
	 * @return
	 */
	protected String realUrl(String type, String id, String subtype,
			String filename) {
		if (isNull(filename))
			return "";
		return "/public/" + type + "/" + id + "/" + subtype + "/" + filename;
	}

	protected String realUrl(String type, int id, String subtype,
			String filename) {
		if (isNull(filename))
			return "";
		return "/public/" + type + "/" + id + "/" + subtype + "/" + filename;
	}

	protected String realName(String filepath) {
		if (isNull(filepath))
			return "";
		return filepath.substring(filepath.lastIndexOf("/") + 1);
	}
}
