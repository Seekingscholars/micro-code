package com.moxa.micro.core.shiro.filters;

import com.moxa.micro.core.constant.CommonConstant;
import com.moxa.micro.core.shiro.JwtToken;
import com.moxa.micro.core.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class JwtFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            executeLogin(request, response);
        } catch (Exception e) {
            JwtUtil.responseError(response, 401, CommonConstant.TOKEN_IS_INVALID_MSG);
            return false;
        }
        return true;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(CommonConstant.X_ACCESS_TOKEN);
        JwtToken jwtToken = new JwtToken(token);
        getSubject(request, response).login(jwtToken);
        return true;
    }
}
