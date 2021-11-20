package com.github.yangricardo.api_spring_boot.modules.auth.services;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.yangricardo.api_spring_boot.modules.users.model.User;
import com.github.yangricardo.api_spring_boot.modules.users.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
public class TokenAuthenticationFilterService extends OncePerRequestFilter {

    @Autowired
    TokenIssuerService tokenIssuerService;

    @Autowired
    IUserService userService;

    public TokenAuthenticationFilterService(TokenIssuerService tokenIssuerService, IUserService userService) {
        this.tokenIssuerService = tokenIssuerService;
        this.userService = userService;
    }

    protected String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }

    private void authenticate(String tokenFromHeader) {
        Long id = tokenIssuerService.getTokenId(tokenFromHeader);

        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorizationProfiles());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenFromHeader = getTokenFromHeader(request);
        boolean tokenValid = tokenIssuerService.isTokenValid(tokenFromHeader);
        if (tokenValid) {
            this.authenticate(tokenFromHeader);
        }

        filterChain.doFilter(request, response);
    }

}
