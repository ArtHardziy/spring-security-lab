package by.es.springsecurity.config;

import by.es.springsecurity.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static by.es.springsecurity.model.Permission.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeReq) -> authorizeReq
                        .requestMatchers(HttpMethod.GET, "/api/v1/developers", "/api/v1/developers/{id}")
                        .hasAuthority(READ.getPermission())
                        .requestMatchers(HttpMethod.POST, "/api/v1/developers")
                        .hasAuthority(WRITE.getPermission())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/developers/{id}")
                        .hasAuthority(DELETE.getPermission())
                        .anyRequest()
                        .authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("password"))
                .authorities(Role.USER.getAuthorities())
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities(Role.ADMIN.getAuthorities())
                .build();

        UserDetails manager = User.withUsername("manager")
                .password(passwordEncoder.encode("manager"))
                .authorities(Role.MANAGER.getAuthorities())
                .build();
        return new InMemoryUserDetailsManager(user, admin, manager);
    }

   @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = """
        ROLE_ADMIN > ROLE_MANAGER
        ROLE_MANAGER > ROLE_USER
        """;
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
}
