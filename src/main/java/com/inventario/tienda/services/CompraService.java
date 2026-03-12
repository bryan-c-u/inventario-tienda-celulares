package com.inventario.tienda.services;

import org.springframework.stereotype.Service;

import com.inventario.tienda.dto.CompraRequestDTO;
import com.inventario.tienda.dto.CompraResponseDTO;
import com.inventario.tienda.entity.Cliente;
import com.inventario.tienda.entity.Compra;
import com.inventario.tienda.entity.Producto;
import com.inventario.tienda.repository.ClienteRepository;
import com.inventario.tienda.repository.CompraRepository;
import com.inventario.tienda.repository.ProductosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProductosRepository productoRepository;
    private final ClienteRepository clienteRepository;

    public CompraResponseDTO realizarCompra(CompraRequestDTO request){

        Producto producto = productoRepository.findById(request.getProductoId())
        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Cliente cliente = clienteRepository.findById(request.getClienteId())
        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        if(request.getCantidad() <= 0){
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }

        if(producto.getStock() < request.getCantidad()){
            throw new RuntimeException("Stock insuficiente");
        }

        producto.setStock(producto.getStock() - request.getCantidad());
        productoRepository.save(producto);

        Compra compra = new Compra();
        compra.setCantidad(request.getCantidad());
        compra.setProducto(producto);
        compra.setCliente(cliente);

        Compra compraGuardada = compraRepository.save(compra);

        CompraResponseDTO response = new CompraResponseDTO();
        response.setId(compraGuardada.getId());
        response.setProductoNombre(producto.getNombre());
        response.setClienteNombre(cliente.getNombre());
        response.setCantidad(compraGuardada.getCantidad());

        return response;
    }
}