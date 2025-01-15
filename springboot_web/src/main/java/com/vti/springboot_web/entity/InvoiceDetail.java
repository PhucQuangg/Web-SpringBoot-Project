package com.vti.springboot_web.entity;


import com.vti.springboot_web.entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "invoice_details")
@Getter
@Setter
public class InvoiceDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "unitPrice")
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "invoiceId", referencedColumnName = "id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    public InvoiceDetail() {
    }

    public InvoiceDetail(Integer id, int quantity, double unitPrice,Invoice invoice, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.invoice = invoice;
        this.product = product;
    }
}

