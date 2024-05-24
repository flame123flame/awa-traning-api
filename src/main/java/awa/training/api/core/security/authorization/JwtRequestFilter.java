 package awa.training.api.core.security.authorization;

 import awa.training.api.core.security.service.JwtUserDetailsService;
 import awa.training.api.feature.users.repository.UsersRepository;
 import io.jsonwebtoken.ExpiredJwtException;
 import io.jsonwebtoken.MalformedJwtException;
 import jakarta.servlet.FilterChain;
 import jakarta.servlet.ServletException;
 import jakarta.servlet.http.HttpServletRequest;
 import jakarta.servlet.http.HttpServletResponse;
 import lombok.extern.slf4j.Slf4j;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
 import org.springframework.stereotype.Component;
 import org.springframework.web.filter.OncePerRequestFilter;

 import java.io.IOException;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired(required=true)
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	
	@Autowired
	private UsersRepository usersRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	    final String requestTokenHeader = request.getHeader("Authorization");
	    String username = null;
	    String jwtToken = null;


	    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
	        jwtToken = requestTokenHeader.substring(7);
	        try {
	            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
	        } catch (IllegalArgumentException e) {
	            log.error("Unable to get JWT Token");
	        } catch (ExpiredJwtException e) {
	            log.error("JWT Token has expired");
	        } catch (MalformedJwtException e) {
	            log.error("Bad format JWT Token");
	            // throw new MalformedJwtException("Bad format JWT Token", e);
	        }
	    } else {
	        log.error("JWT Token does not begin with Bearer String");
	    }

	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	        UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

	        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
	            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	        }
	    }

	    filterChain.doFilter(request, response);
	}

}
