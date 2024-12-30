package com.vti.springboot_web.service.user;

import com.vti.springboot_web.entity.user.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    User findByUserName(String username);
}
