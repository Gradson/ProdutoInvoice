package br.com.gradson.atech;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CorsFilter implements Filter {

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
		
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;

		response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, origin, content-type, accept, authorization, Access-Control-Request-Headers");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		response.setHeader("Access-Control-Max-Age", "1209600");
		response.setHeader("Access-Control-Expose-Headers", "X-Auth-Token");

		filterChain.doFilter(servletRequest, servletResponse);

	}

	@Override
	public void init(final FilterConfig arg0) throws ServletException {}

	@Override
	public void destroy() {}

}

