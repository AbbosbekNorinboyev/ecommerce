package uz.pdp.ecommerce.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.entity.AuthUser;
import uz.pdp.ecommerce.repository.AuthUserRepository;

@Component
@RequiredArgsConstructor
public class SessionId {

    private final AuthUserRepository authUserRepository;

    public Long getSessionId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return authUserRepository.findByUsername(username)
                .map(AuthUser::getId)
                .orElseThrow(() -> new UsernameNotFoundException("AuthUser not found"));
    }
}
