package com.dbc.prova.service;

import com.dbc.prova.domain.User;
import com.dbc.prova.repository.UserRepository;
import com.dbc.prova.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addHistory(String email, String history) {
        User user = find(email);

        user.getUserHistory().add(history);

        userRepository.save(user);
    }

    private User find(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("email", email));
    }

}
