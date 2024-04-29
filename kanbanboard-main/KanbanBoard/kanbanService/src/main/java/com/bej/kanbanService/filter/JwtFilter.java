package com.bej.kanbanService.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;

public class JwtFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //expects the token to come from the header
        final String authHeader = request.getHeader("Authorization");
        if(request.getMethod().equals("OPTIONS")){
            //if the method is options the request can pass through not validation of token is required
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request,response);
        }
        else if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
            throw new ServletException("Missing or Invalid Token");
        }
        //extract token from the header
        String token = authHeader.substring(7);//Bearer => 6+1 = 7, since token begins with Bearer
        //extract the claims
        Claims claims = Jwts.parser().setSigningKey("mysecret").parseClaimsJws(token).getBody();
        // set the claims in the request attribute and pass it to the next handler
        request.setAttribute("claims",claims);
        //pass the claims in the request, anyone wanting to use it
        filterChain.doFilter(request,response);
    }
}


