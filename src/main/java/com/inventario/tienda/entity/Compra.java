package com.inventario.tienda.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}