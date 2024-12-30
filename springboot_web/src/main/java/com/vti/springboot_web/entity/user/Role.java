package com.vti.springboot_web.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<User_Role> roleUsers;

    public Role() {
    }

    public Role(Long id, String name, Set<User_Role> roleUsers) {
        this.id = id;
        this.name = name;
        this.roleUsers = roleUsers;
    }
}
