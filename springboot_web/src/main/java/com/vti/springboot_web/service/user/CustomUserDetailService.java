package com.vti.springboot_web.service.user;

import com.vti.springboot_web.entity.user.CustomUserDetails;
import com.vti.springboot_web.entity.user.User;
import com.vti.springboot_web.entity.user.User_Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("Khong tim thay user");
        }
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<User_Role> roles = user.getUserRoles();
        for(User_Role user_role:roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(user_role.getRole().getName()));
        }
        return new CustomUserDetails(user,grantedAuthorities);
    }
}
