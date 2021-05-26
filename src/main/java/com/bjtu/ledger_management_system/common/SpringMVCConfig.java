//package com.bjtu.ledger_management_system.common;
//
///**
// * 启用跨域配置
// * 编写SpringMVCConfig类使用FilterConfig中的配置
// * @author 胡江浩
// *
// */
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//@SuppressWarnings("deprecation")
//@SpringBootConfiguration
//public class SpringMVCConfig extends WebMvcConfigurerAdapter{
//    @Autowired
//    private FilterConfig filterConfig;
//
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(filterConfig).addPathPatterns("/**");
//    }
//}
