//package com.bjtu.ledger_management_system.common;
//
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = "/*", filterName = "CORSFilter")
//public class CORSFilter implements Filter {
//    @Override
//    public void destroy() {
//    }
//
//    /**
//     * 此过滤器只是处理跨域问题
//     * @param servletRequest
//     * @param servletResponse
//     * @param chain
//     * @throws ServletException
//     * @throws IOException
//     */
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        String origin = req.getHeader("Origin");
//        if(origin == null) {
//            origin = req.getHeader("Referer");
//        }
//        resp.setHeader("Access-Control-Allow-Origin", origin);//这里不能写*，*代表接受所有域名访问，如写*则下面一行代码无效。谨记
//        resp.setHeader("Access-Control-Allow-Credentials", "true");//true代表允许携带cookie
//
//        resp.addHeader("Access-Control-Allow-Methods", "*");
//        resp.addHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,token,username,client");
//        if (((HttpServletRequest) req).getMethod().equals("OPTIONS")) {
//            servletResponse.getWriter().println("ok");
//            return;
//        }
//
//        System.out.println("111111111111111111111" + origin);
//        chain.doFilter(servletRequest,servletResponse);
//        System.out.println("222222222222222222222");
//    }
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//}
//
