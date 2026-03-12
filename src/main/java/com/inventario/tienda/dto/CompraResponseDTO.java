package com.inventario.tienda.dto;

import lombok.Data;

@Data
public class CompraResponseDTO {

    private Long id;
    private String productoNombre;
    private String clienteNombre;
    private Integer cantidad;

}