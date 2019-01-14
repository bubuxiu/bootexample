
package com.buxiu.bootexample.interceptor;

import com.buxiu.bootexample.controller.model.RSResult;
import com.buxiu.bootexample.service.ITokenUserService;
import com.buxiu.bootexample.service.common.BizErrorCode;
import com.buxiu.bootexample.service.common.BizValue;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 请求拦截器
 * 客户端传递令牌方式: Header中X-Token 
 * 校验客户端令牌是否正确, 同时从令牌中获取用户Id
 * @author bubuxiu@gmail.com
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
	//private static final Logger logger = LoggerFactory.getLogger(ValidateTokenInterceptor.class);

	/**
	 * 发起服务请求的用户信息, 从令牌中获取
	 */
	@Autowired
	private ITokenUserService currentUserService;

	/**
	 * 校验客户端令牌是否正确, 同时从令牌中获取用户信息
	 * preHandle会在1.DispatcherServlet捕获每一个请求；2.DispatcherServlet将接收到的URL和相应的Controller进行映射；
	 * 3.在请求到达相应的Controller之前拦截器会进行请求处理；
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (request == null || response == null || handler == null) {
			return true;
		}

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		

		// logback MDC save
		MDC.put("req.host", getRemoteIpAddress(request));
		MDC.put("req.uri", request.getRequestURI());
		//MDC.put("userid", currentUserService.getUserId()+"");  
		
		
		//获取远程客户端ip地址
		String remoteIp = getRemoteIpAddress(request);
		currentUserService.setRemoteIp(remoteIp);

		// 仅处理包头前缀为com.buxiu.bootexample.controller的Controller方法
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		if (!handlerMethod.getBeanType().getPackage().getName().startsWith("com.buxiu.bootexample.controller")) {
			return true;
		}

		// 获取是否校验令牌注解, 如果一个方法不需校验令牌, 请加上注解@ValidateToken(false)
		CheckToken checkToken = handlerMethod.getMethodAnnotation(CheckToken.class);
		// 如果方法上的注解为空 则获取类的注解
        if (checkToken == null) {
            checkToken = handlerMethod.getMethod().getDeclaringClass().getAnnotation(CheckToken.class);
        } 

		if (checkToken != null && !checkToken.value()) {
			return true;
		}

		// 客户端传递令牌方式: Header中X-Token
		// 先取Header中X-Token
		String userToken = request.getHeader("User-Token");
		

		// 校验客户端令牌是否正确, 同时从令牌中获取用户信息
		RSResult result = null;
		if (userToken == null || userToken.isEmpty()) {
			result = new RSResult(BizErrorCode.EX_TOKEN_MISS);
		} else {
			try {
				Jws<Claims> claims = 
						Jwts.parser().setSigningKey(DigestUtils.md5(BizValue.GROUP_NAME)).parseClaimsJws(userToken);

				String loginIp = claims.getBody().get("loginIp").toString();
				if (loginIp == null || !loginIp.equals(remoteIp)) {
					// header中令牌不对, 可能被劫持
					result = new RSResult(BizErrorCode.EX_TOKEN_IPCHANGE);
				}
				else {
					// 获取用户Id
					currentUserService.setUserId(Integer.valueOf(claims.getBody().get("userId").toString()));
					currentUserService.setUserName(claims.getBody().get("userName").toString());
					currentUserService.setUserType(Integer.valueOf(claims.getBody().get("userType").toString()));
					// 记录客户端令牌
					currentUserService.setToken(userToken);
					// 记录客户端令牌过期时间
					currentUserService.setExpiration(claims.getBody().getExpiration());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				// header中令牌不对, 可能被篡改
				result = new RSResult(BizErrorCode.EX_TOKEN_ERROR);
			}
		}

		// 如果令牌不对, 直接返回错误消息
		if (result != null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = null;
			try {
				ObjectMapper mapper = new ObjectMapper();
				out = response.getWriter();
				out.append(mapper.writeValueAsString(result));
			} finally {
				if (out != null) {
					out.close();
				}
			}
			return false;
		}
		
		
		return true;
	}
	
	
	@Override  
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, 
    							Object handler, ModelAndView modelAndView) throws Exception {  
		
		// 清除MDC数据
        MDC.remove("req.host");  
        MDC.remove("req.uri");
        //MDC.remove("userid");
        
        return;
    }  
	
	/**
	 * 获取远程IP地址
	 * @return
	 */
	private String getRemoteIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-Forwarded-For");
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
				//根据网卡取本机配置的IP
				InetAddress inet=null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress= inet.getHostAddress();
			}
		}
		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
			if(ipAddress.indexOf(",")>0){
				ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
}
