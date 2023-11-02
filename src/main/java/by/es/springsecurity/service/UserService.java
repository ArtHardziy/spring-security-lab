package by.es.springsecurity.service;

import by.es.springsecurity.model.User;
import by.es.springsecurity.repositories.UserRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    @Qualifier("DBUserDetailService")
    @Getter
    private final UserDetailsService userDetailsService;

    public User save(User newUserToSave) {
        if(newUserToSave.getId() == null) {
            newUserToSave.setCreatedAt(LocalDateTime.now());
        }

        newUserToSave.setUpdatedAt(LocalDateTime.now());
        return userRepo.save(newUserToSave);
    }

}
