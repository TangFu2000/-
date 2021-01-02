//package com.jsu.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebInitParam;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
////urlPatters 过滤器拦截的url
////http://localhost:8080/chapter/auth/testServlet
//@WebFilter(urlPatterns = {
//        "/*","*.do","*.html","/auth/test"},
//        initParams = {
//            @WebInitParam(name="encoding",value="UTF-8")
//        }
//)
//public class CharacterFilter implements Filter {
//
//    private String encoding;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        encoding=filterConfig.getInitParameter("encoding");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        //业务逻辑
//        HttpServletRequest request=(HttpServletRequest)servletRequest;
//        HttpServletResponse response=(HttpServletResponse)servletResponse;
//
//        request.setCharacterEncoding(encoding);
//
//        //将当前拦截的请求放行，让请求继续访问他要访问的资源
//        filterChain.doFilter(request,response);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
