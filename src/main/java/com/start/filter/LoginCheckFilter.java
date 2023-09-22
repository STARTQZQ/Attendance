package com.start.filter;

import com.alibaba.fastjson.JSON;
import com.start.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 检查用户是否完成登入
 */
@Slf4j
@WebFilter(filterName="loginCheckFilter",urlPatterns = "/*")
@Component
public class LoginCheckFilter implements Filter {
    //路径匹配器，指出通配符
    public static final AntPathMatcher PATH_MATCHER =new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        //1,获取本次请求的url
        String requestUrl=request.getRequestURI();
        log.info("拦截到请求：{}",requestUrl);

        //定义不需要处理的请求路径
        String[] urls=new String[]{
                "/admins/login",
                "/admins/logout",
                "/backend/**",
                "/face/**",
                "/staff/login",
                "/staff/logout",
        };
        //判断是否需要处理
        boolean check=check(urls,requestUrl);

        //如果不需要处理，则直接放行
        if(check){
            log.info("本次请求{}不需要处理",requestUrl);
            filterChain.doFilter(request,response);
            return;
        }
        //判断登入状态，如果已经登入则直接放行
        if(request.getSession().getAttribute("admins")!=null){
            log.info("{}管理员以登入",request.getSession().getAttribute("admins"));
            filterChain.doFilter(request,response);
            return;
        }
        else if (request.getSession().getAttribute("staff")!=null){
            log.info("{}用户以登入",request.getSession().getAttribute("staff"));
            filterChain.doFilter(request,response);
            return;
        }
        //如果未登入则直接返回登入结果，通过输出流的方式向客户端页面响应数据
        log.info("未登入");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;


//        log.info("拦截到请求{}",request);
//        filterChain.doFilter(request,response);


    }

    /**
     * 路径匹配，看是否放行
     * @param urls
     * @param requestURL
     * @return
     */
    public Boolean  check(String[] urls,String  requestURL){
        for(String url:urls){
            boolean match=PATH_MATCHER.match(url,requestURL);
            if(match){
                return true;
            }
        }
        return false;
    }

}
