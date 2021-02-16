package com.tongji.boying.security.component;

import com.tongji.boying.security.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录授权过滤器
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    /**
     * SpringSecurity定义的核心接口，用于根据用户名获取用户信息
     */
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException
    {
        //请求头有字段Authorization，并且以‘Bearer ’开头
        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(this.tokenHead))
        {
            String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            LOGGER.info("checking username:{}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
            {
                //SpringSecurity定义用于封装用户信息的类（主要是用户信息和权限）
                //这里首先从缓存中获取用户信息和用户权限
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                //验证用户名和是否已过期
                if (jwtTokenUtil.validateToken(authToken, userDetails))
                {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    LOGGER.info("authenticated user:{}", username);
                    /*
                    SecurityContextHolder使用了ThreadLocal机制来保存每个使用者的安全上下文。
                    这意味着，只要针对某个使用者的逻辑执行都是在同一个线程中进行，即使不在各个方法之间以参数的形式传递其安全上下文，
                    各个方法也能通过SecurityContextHolder工具获取到该安全上下文。只要在处理完当前使用者的请求之后注意清除ThreadLocal中的安全上下文
                     */
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
