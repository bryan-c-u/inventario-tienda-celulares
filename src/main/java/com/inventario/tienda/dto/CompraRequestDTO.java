package com.inventario.tienda.dto;

import lombok.Data;

@Data
public class CompraRequestDTO {

    private Long productoId;
    private Long clienteId;
    private Integer cantidad;

}