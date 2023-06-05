package tp35.mycashserver.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tp35.mycashserver.model.Role;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class JwtSecurityFilterConfig {
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain configure(final HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/static/**").permitAll()
                                .requestMatchers("/swagger-ui").permitAll()
                                .requestMatchers("/swagger-ui/**", "/v3/**").permitAll()
                                .requestMatchers("/api/auth/new", "/api/auth/login").permitAll()
                                .requestMatchers("/api/auth/register").hasAnyAuthority(Role.UNREGISTERED.getAuthority(), Role.ADMIN.getAuthority())
                                .requestMatchers("/api/**").hasAnyAuthority(Role.UNREGISTERED.getAuthority(), Role.REGISTERED.getAuthority(), Role.ADMIN.getAuthority())
                                .requestMatchers("/admin/**").hasAuthority(Role.ADMIN.getAuthority()))


                .sessionManagement(configurer ->
                        configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
