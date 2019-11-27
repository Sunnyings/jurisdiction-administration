package com.example.demo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.example.demo.manage.Constants;
import com.example.demo.mapper.menu.SysMenuInfoMapper;
import com.example.demo.mapper.user.SysUserInfoMapper;
import com.example.demo.model.SessionUserBean;
import com.example.demo.model.SysUserInfo;
import com.example.demo.security.CustomAccessDeniedHandler;
import com.example.demo.security.SecurityUser;
import com.example.demo.security.ValidateCodefilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	
	SysUserInfo user;

	@Autowired
	SysMenuInfoMapper sysMenuInfoMapper;

	@Autowired
	SysUserInfoMapper mapper;

	@Override
	protected void configure(HttpSecurity http) throws Exception { // 配置策略
		http.headers().frameOptions().disable();
		http.csrf().disable();
		http
        .addFilterAt(new ValidateCodefilter(),UsernamePasswordAuthenticationFilter.class);
		http.authorizeRequests().antMatchers("/login_code").permitAll().antMatchers("/**").fullyAuthenticated().and().formLogin().loginPage("/login")
				.permitAll().successHandler(loginSuccessHandler()).failureForwardUrl("/error_msg")
                .permitAll().and().logout().permitAll().invalidateHttpSession(true).deleteCookies("JSESSIONID")
				.logoutSuccessHandler(logoutSuccessHandler()).and().sessionManagement().maximumSessions(10)
				.expiredUrl("/login");

		http.authorizeRequests().anyRequest().authenticated().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler());
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
		auth.eraseCredentials(false);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() { // 密码加密
		return new BCryptPasswordEncoder(4);
	}

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() { // 登出处理
		return new LogoutSuccessHandler() {
			@Override
			public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
					Authentication authentication) throws IOException, ServletException {
				try {
					SecurityUser user = (SecurityUser) authentication.getPrincipal();
					logger.info("USER : " + user.getUsername() + " LOGOUT SUCCESS !  ");
				} catch (Exception e) {
					logger.info("LOGOUT EXCEPTION , e : " + e.getMessage());
				}
				httpServletResponse.sendRedirect("/login");
			}
		};
	}

	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { // 登入处理
		return new SavedRequestAwareAuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				SysUserInfo userDetails = (SysUserInfo) authentication.getPrincipal();
				SessionUserBean sub = new SessionUserBean();
				sub.setId(userDetails.getUserUuid());
				sub.setAccount(userDetails.getUsername());
				sub.setRealName(userDetails.getRoleCode());
				sub.setLastIp(userDetails.getLastIp());
				sub.setLastTime(userDetails.getLastTime());
				// sub.setLastCity(IpUtil.getIpCity(uds.getLastIp()));
				if (userDetails.getAuthoritieList().size() > 0) {
					if ("ROOT".equals(userDetails.getAuthoritieList().get(0).toString())) {
						request.getSession().setAttribute(Constants.ROOT_INFO_SESSION_KEY, sub);
						userDetails.setLastTime(new Date());
						userDetails.setLastIp(getIpAddr(request));
						mapper.update(userDetails);
					} else {
						request.getSession().setAttribute(Constants.USER_INFO_SESSION_KEY, sub);
						userDetails.setLastTime(new Date());
						userDetails.setLastIp(getIpAddr(request));
						mapper.update(userDetails);
					}
				}
				super.onAuthenticationSuccess(request, response, authentication);// 跳转页面
			}
		};
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 配置静态文件不需要认证
		web.ignoring().antMatchers("/static/**");
	}

	@Bean
	public UserDetailsService userDetailsService() { // 用户登录实现
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
				user = sysMenuInfoMapper.findByUsername(s);
				List<String> roles = sysMenuInfoMapper.getRole(user.getUserUuid());
				ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				for (String roleCode : roles) {
					authorities.add(new SimpleGrantedAuthority(roleCode));
				}
				user.setAuthoritieList(authorities);
				if (user == null)
					throw new UsernameNotFoundException("Username " + s);
				return new SecurityUser(user);
			}
		};
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
