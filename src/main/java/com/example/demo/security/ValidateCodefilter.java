package com.example.demo.security;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.manage.Constants;

public class ValidateCodefilter extends OncePerRequestFilter{
	public static final String SPRING_SECURITY_CAPTCHA_KEY = Constants.LOGIN_CODE_SESSION_KEY;
	private String captchaParameter = SPRING_SECURITY_CAPTCHA_KEY;
	private boolean captchaValid = true;
	private boolean captchaNoCase = true;
	 @Autowired
   private AuthenticationFailureHandler authenticationFailureHandler;
	//private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override//校验验证码
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String uri= request.getRequestURI();
        //如果为get请求并且请求uri为/login（也就是我们登录表单的form的action地址）
        if( request.getMethod().equalsIgnoreCase("post") && request.getRequestURI().equalsIgnoreCase("/login"))
        {
        	 logger.info("ValidateCodefilter执行了----" + "request.getRequestURI()=" + uri);
        	if (captchaValid) {
    			String reqCaptcha = StringUtils.trimAllWhitespace(request.getParameter(captchaParameter));
    			String sesCaptcha = "";
    			if (request.getSession(false) != null) {
    				sesCaptcha = StringUtils.trimAllWhitespace((String) request.getSession().getAttribute(captchaParameter));
    			}

    			if (logger.isDebugEnabled()) {
    				logger.debug("The request captcha is" + reqCaptcha);
    				logger.debug("The session captcha is" + sesCaptcha);
    			}

    			if (captchaNoCase) {
    				if(StringUtils.hasLength(reqCaptcha)){
    					reqCaptcha = reqCaptcha.toLowerCase();
    				}
    				if(StringUtils.hasLength(sesCaptcha)){
    					sesCaptcha = sesCaptcha.toLowerCase();
    				}
    			}

    			if (!StringUtils.hasLength(sesCaptcha) || !StringUtils.hasLength(reqCaptcha) || !reqCaptcha.equals(sesCaptcha)) {
    				 // 重定向
    				//redirectStrategy.sendRedirect("/login");
					/* throw new BadCredentialsException ("验证码错误!"); */
    				throw new AuthenticationException("验证码不正确！");
    			}
    		}
        }
        filterChain.doFilter(request,response);
	}
}