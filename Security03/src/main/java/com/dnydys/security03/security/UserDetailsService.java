package com.dnydys.security03.security;

import com.dnydys.security03.domain.MyUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 模拟一个用户，替代数据库获取逻辑
        MyUser userInfo = new MyUser();
        userInfo.setUserName(username);
        userInfo.setPassword(this.passwordEncoder.encode("admin"));
        // 输出加密后的密码
        log.info("加密后的密码::::::::"+userInfo.getPassword());

        return new User(username,userInfo.getPassword(),userInfo.isEnabled(),
                userInfo.isAccountNonExpired(),userInfo.isCredentialsNonExpired(),
                userInfo.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
                );
    }
}
