
package com.vti.springboot_web.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {
    @Id
    @Column(name = "id")
    private Integer id; // Sử dụng kiểu dữ liệu String cho mã khách hàng

    @Column(name = "email")
    private String email;

    @Column(name = "matkhau")
    private String matkhau;

    @Column(name = "ngaysinh")
    private LocalDate ngaysinh;

    @Column(name = "ngaysua")
    private LocalDate ngaysua;

    @Column(name = "ngaytao")
    private LocalDate ngaytao;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "ten")
    private String ten;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer")
    private List<Cart> carts;

    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoices;

    public Customer() {
    }

    public Customer(Integer id, String email, String matkhau, LocalDate ngaysinh, LocalDate ngaysua, LocalDate ngaytao, String sdt, String ten) {
        this.id = id;
        this.email = email;
        this.matkhau = matkhau;
        this.ngaysinh = ngaysinh;
        this.ngaysua = ngaysua;
        this.ngaytao = ngaytao;
        this.sdt = sdt;
        this.ten = ten;
    }
}
