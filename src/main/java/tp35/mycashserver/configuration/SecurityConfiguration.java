package tp35.mycashserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                //.requestMatchers("/api/**")
//                .anyRequest().permitAll();
//                //.anyRequest().authenticated()
//                //.and().httpBasic();

                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/user").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}
