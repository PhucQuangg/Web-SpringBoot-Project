package com.vti.springboot_web.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "invoices")
@Getter
@Setter
public class Invoice {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "issueDate")
    private LocalDate issueDate;
    @Column(name = "totalAmount")
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetail> invoiceDetails;

    public Invoice() {
    }

    public Invoice(Integer id, LocalDate issueDate, double totalAmount, Customer customer) {
        this.id = id;
        this.issueDate = issueDate;
        this.totalAmount = totalAmount;
        this.customer = customer;
    }
}
