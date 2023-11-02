package by.es.springsecurity.service;

import by.es.springsecurity.model.Role;
import by.es.springsecurity.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DBUserDetailService implements UserDetailsService {

    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userFromDb = userRepo.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User does not exist with username: [%s]", username)));
        var userAuthorities = userFromDb.getRoles().stream()
                .map(Role::getRoleType)
                .flatMap(roleType -> roleType.getAuthorities().stream())
                .collect(Collectors.toSet());
        return new User(username, userFromDb.getPassword(), userAuthorities);
    }
}
