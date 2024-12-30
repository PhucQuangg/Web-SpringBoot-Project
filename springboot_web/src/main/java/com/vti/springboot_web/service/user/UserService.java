package com.vti.springboot_web.service.user;

import com.vti.springboot_web.entity.user.User;
import com.vti.springboot_web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }
}
