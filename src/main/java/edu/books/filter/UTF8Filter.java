package edu.books.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class UTF8Filter extends OncePerRequestFilter {

	 @Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws ServletException, IOException {
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
	        chain.doFilter(request, response);
	    }

}