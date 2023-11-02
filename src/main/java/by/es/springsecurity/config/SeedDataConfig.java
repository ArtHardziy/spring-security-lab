package by.es.springsecurity.config;

import by.es.springsecurity.model.Role;
import by.es.springsecurity.model.RoleType;
import by.es.springsecurity.model.User;
import by.es.springsecurity.repositories.UserRepo;
import by.es.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            var role = new Role();
            role.setRoleType(RoleType.ADMIN);
            var admin = User.builder()
                    .firstName("admin")
                    .lastName("admin")
                    .username("admin")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("admin"))
                    .role(role)
                    .build();

            userService.save(admin);
            log.debug("Created ADMIN user - {}", admin);
        }
    }
}
