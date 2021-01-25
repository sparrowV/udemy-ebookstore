package ge.odvali.ebookstore.security.jwt;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ge.odvali.ebookstore.security.jwt.Constants.TOKEN_HEADER;
import static ge.odvali.ebookstore.security.jwt.Constants.TOKEN_PREFIX;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    public JwtAuthorizationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        String header = req.getHeader(TOKEN_HEADER);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
//            throw new ResponseStatusException(401,"please provide a bearer token!",null);
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            res.setContentType("application/json");
            res.getWriter().write("{\"error\":\"Please provide a bearer token!\"}");
            res.getWriter().flush();
            res.getWriter().close();
            return;
        }

        try {
            Authentication authentication = jwtUtils.getAuthentication(header.substring(TOKEN_PREFIX.length()), req);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            res.setStatus(HttpStatus.BAD_REQUEST.value());
            res.getWriter().write(e.getMessage());
            res.getWriter().flush();
            res.getWriter().close();
        }
        chain.doFilter(req, res);
    }
}