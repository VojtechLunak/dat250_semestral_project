package hvl.dat250.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String token;
    private final String role;
    private final JwtUtil jwtUtil;

    public JwtAuthenticationToken(String token, JwtUtil jwtUtil) {
        super(Collections.emptyList());
        this.token = token;
        this.jwtUtil = jwtUtil;
        this.role = extractRoleFromToken(token);
        setAuthenticated(true);
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+role));
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    private String extractRoleFromToken(String token) {
        return jwtUtil.extractRole(token);
    }
}
