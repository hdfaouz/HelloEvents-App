package com.enaa.helloevents.Filter;

import com.enaa.helloevents.configuration.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    public JwtFilter(UserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     final String autHeader = request.getHeader("Authorization");

     String username = null;
     String jwt = null;

     if(autHeader != null && autHeader.startsWith("Bearer ")){
         jwt = autHeader.substring(7);
         username = jwtUtils.extractUsername(jwt);
     }

     if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
         UserDetails userDetails = userDetailsService.loadUserByUsername(username);

         if(jwtUtils.validateToken(jwt, userDetails)){
             UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
             authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(authenticationToken);
         }
     }

     filterChain.doFilter(request,response);
    }
}
