package com.irsan.sinaukoding.filter;

import com.irsan.sinaukoding.model.UserData;
import com.irsan.sinaukoding.service.UserDetailsPerpusServiceImpl;
import com.irsan.sinaukoding.util.Constant;
import com.irsan.sinaukoding.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsPerpusServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username = null;
        String jwtToken = null;
        UserData userData = new UserData();
        String strUrl = request.getRequestURL().toString();
        String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (strUrl.contains("v2/api-docs") || strUrl.contains("swagger-resources") || strUrl.contains("swagger-ui")) {
            log.info("Your access swagger documentation");
        } else {
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.replace("Bearer ", "");
                try {
                    userData = jwtTokenUtil.extractToken(jwtToken);
                    username = userData.getUsername();
                } catch (IllegalArgumentException | ExpiredJwtException | SignatureException e) {
                    log.error(e.getMessage());
                }
            } else {
                log.error("Header is not set / can't find token");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        request.setAttribute(Constant.HEADER_DATA, userData);
        filterChain.doFilter(request, response);

    }
}
