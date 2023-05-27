package ru.itis.ediary.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.ediary.models.UserDetails;
import ru.itis.ediary.repositories.UserDetailsRepository;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userDetailsRepository.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("User with login <" + username + "> not found"));

        return new UserDetailsImpl(userDetails);
    }
}
