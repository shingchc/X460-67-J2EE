/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uci.javaee7.flix.servlets;

import edu.uci.javaee7.flix.util.Log;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Shing-Cheung
 */
@WebFilter(filterName = "HeaderFilter", urlPatterns = {"/about"}, initParams = {
    @WebInitParam(name = "headers", value = "true")})
public class HeaderFilter implements Filter {
    boolean headers = true;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if ("true".equals(filterConfig.getInitParameter("headers")))    {
            headers = true; 
        }   else    {
            headers = false;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Enumeration<String> headerNames = req.getHeaderNames();
        String logString = "Header: ";
        while (headerNames.hasMoreElements())   {
            String header = headerNames.nextElement();
            String hElement = req.getHeader(header);
            header += " - " + hElement;
            Log.log.info(logString + " " + header);
        }
        
        chain.doFilter(request, response);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
