package vn.free.register.jwt;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.free.register.security.CustomUserDetailService;
import vn.free.register.security.UserPrincipal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString().replaceAll("-", "");
        ThreadContext.put("token", token);
        logger.info("Create token request in Thread context:  " + token);

        try {
            String jwt = getJWT(httpServletRequest);
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                Long userId = jwtTokenProvider.getUserIdFromJWT(jwt);
                UserPrincipal userPrincipal = (UserPrincipal) userDetailService.loadUserById(userId);
                if (userPrincipal != null) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userPrincipal,
                                    null,
                                    userPrincipal.getAuthorities());
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authenticationToken);
                }
            }

        } catch (Exception e) {
            log.error("Set authentication ...fail.", e);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    public String getJWT(HttpServletRequest httpServletRequest) {

        String bearerToken = httpServletRequest.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
