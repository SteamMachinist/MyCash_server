package tp35.mycashserver.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    UNREGISTERED,
    REGISTERED,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
