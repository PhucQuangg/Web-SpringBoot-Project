package com.vti.springboot_web.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String passWord;
    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "telephone")
    private String telephone;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<User_Role> userRoles;

    public User() {
    }

    public User(Long id, String userName, String passWord, Boolean enabled, String fullName, Boolean gender, String address, String email, String telephone, Set<User_Role> userRoles) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.enabled = enabled;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
        this.userRoles = userRoles;
    }
}
