package ru.itis.ediary.security.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itis.ediary.models.UserDetails;
import ru.itis.ediary.security.filters.AuthenticationFilter;
import ru.itis.ediary.security.filters.AuthorizationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig {
    PasswordEncoder passwordEncoder;
    UserDetailsService userDetailsServiceImpl;
    String authPath = AuthorizationFilter.AUTHENTICATION_PATH;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthorizationFilter authorizFilter,
                                           AuthenticationFilter authFilter) throws Exception {
        http.csrf().disable();
        authFilter.setFilterProcessesUrl(authPath);
        http.addFilter(authFilter)
                .addFilterBefore(authorizFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.authorizeHttpRequests((authorize) -> authorize
                .antMatchers("/auth/token").permitAll()
                .antMatchers(HttpMethod.POST, "/parent", "/teacher", "/student").permitAll()

                .antMatchers(HttpMethod.GET, "/student/**", "/teacher/**", "/task/**", "/timetable/**",
                        "/parent/**", "/group/**", "/lesson/**").authenticated()

                .antMatchers("/student/**", "/teacher/**", "/task/**", "/timetable/**",
                        "/parent/**", "/group/**", "/lesson/**").hasAuthority(UserDetails.Role.TEACHER.toString())

                .antMatchers("/parent/**").hasAuthority(UserDetails.Role.PARENT.toString())

                .anyRequest().authenticated());

        http.logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return http.build();
    }

    @Autowired
    public void build(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder);
    }
}
