package com.dnydys.security03.security;

import com.dnydys.security03.handler.MyAuthenticationFailureHandler;
import com.dnydys.security03.handler.MyAuthenticationSucessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthenticationSucessHandler authenticationSucessHandler;
    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    protected void  configure(HttpSecurity http) throws Exception{
        http.formLogin()// 表单登录
                // http.httpBasic() // HTTP Basic
                .loginPage("/authentication/require")// 登录跳转 URL
                .loginProcessingUrl("/login")// 处理表单登录 URL
                .successHandler(authenticationSucessHandler)// 处理登录成功
                .failureHandler(authenticationFailureHandler) // 处理登录失败
                .and()
                .authorizeRequests()//授权配置
                // 登录跳转 URL 无需认证
                .antMatchers("/authenication/require","/login.html").permitAll()
                .anyRequest() //所有请求
                .authenticated()//都需要认证
                .and().csrf().disable();
    }
}
