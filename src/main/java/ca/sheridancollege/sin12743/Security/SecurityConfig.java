package ca.sheridancollege.sin12743.Security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    public InMemoryUserDetailsManager
    userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user1 = User.withUsername("Bartender")
                .password(passwordEncoder.encode("123"))
                .roles("USER","VIEWDRINK","ADDDRINK","DELETEDRINK","EDITDRINK")//ALWAYS UPPERCASE
                .build();

        UserDetails user2 = User.withUsername("Customer")
                .password(passwordEncoder.encode("123"))
                .roles("CUSTOMER", "VIEWDRINK")//ALWAYS UPPERCASE
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;

    }
}