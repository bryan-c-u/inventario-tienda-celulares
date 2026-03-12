package com.inventario.tienda.dto;

import lombok.Data;

@Data
public class ProductoRequestDTO {

    private String nombre;

    private String descripcion;

    private Double precio;

    private Integer stock;

}