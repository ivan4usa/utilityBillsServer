package com.ivan4usa.utilityBills.security;

import com.ivan4usa.utilityBills.entities.CustomUserDetails;
import com.ivan4usa.utilityBills.entities.User;
import com.ivan4usa.utilityBills.services.CustomUserDetailsService;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@Getter
@Setter
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Value("#{'${jwt.public_urls}'.split(',')}") private List<String> PUBLIC_URLS;
    @Value("${jwt.token_prefix}") private String TOKEN_PREFIX;
    @Value("${jwt.header_string}") private String HEADER_STRING;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    private CustomUserDetailsService customUserDetailsService;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest, @NonNull HttpServletResponse httpServletResponse,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        boolean isRequestToPublicApi = PUBLIC_URLS.stream().anyMatch(s-> httpServletRequest.getRequestURI().toLowerCase().equals(s));

        if (!isRequestToPublicApi && SecurityContextHolder.getContext().getAuthentication() == null) {
            String jwt = this.getJWTFromRequest(httpServletRequest);
            if (jwt != null) {
                if (jwtTokenProvider.validateToken(jwt)) {
                    User user = jwtTokenProvider.getUserFromToken(jwt);
                    CustomUserDetails userDetails = new CustomUserDetails(user);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    logger.error("Token not found");
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    httpServletResponse.getWriter().write("Unauthorized: The token is not Valid");
                    httpServletResponse.getWriter().flush();
                    return;
                }

            } else {
                logger.error("Token not found");
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpServletResponse.getWriter().write("Unauthorized: The token is not found");
                httpServletResponse.getWriter().flush();
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getJWTFromRequest(HttpServletRequest request) {
        String bearToken = request.getHeader(HEADER_STRING);
        if (StringUtils.hasText(bearToken) && bearToken.startsWith(TOKEN_PREFIX)) {
            return bearToken.split(" ")[1];
        }
        return null;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
