package com.inventario.tienda.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inventario.tienda.dto.ProductoRequestDTO;
import com.inventario.tienda.dto.ProductoResponseDTO;
import com.inventario.tienda.entity.Producto;
import com.inventario.tienda.repository.ProductosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductosService {

    private final ProductosRepository productoRepository;

    public ProductoResponseDTO createProducto(ProductoRequestDTO request){

        if(request.getNombre() == null || request.getNombre().isEmpty()){
            throw new RuntimeException("El nombre del producto es obligatorio");
        }

        if(request.getPrecio() == null || request.getPrecio() <= 0){
            throw new RuntimeException("El precio debe ser mayor a 0");
        }

        if(request.getStock() == null || request.getStock() < 0){
            throw new RuntimeException("El stock no puede ser negativo");
        }

        Producto productoExistente = productoRepository.findByNombre(request.getNombre());

        if(productoExistente != null){

            productoExistente.setStock(
                productoExistente.getStock() + request.getStock()
            );

            Producto actualizado = productoRepository.save(productoExistente);

            return mapToResponse(actualizado);
        }

        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());

        Producto guardado = productoRepository.save(producto);

        return mapToResponse(guardado);
    }

    public List<ProductoResponseDTO> getProductos(){

        List<Producto> productos = productoRepository.findAll();
        List<ProductoResponseDTO> lista = new ArrayList<>();

        for(Producto producto : productos){
            lista.add(mapToResponse(producto));
        }

        return lista;
    }

    public ProductoResponseDTO getProductoById(Long id){

        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        return mapToResponse(producto);
    }

    public ProductoResponseDTO deleteProducto(Long id){

        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se puede eliminar. Producto no encontrado"));

        productoRepository.delete(producto);

        return mapToResponse(producto);
    }

    public ProductoResponseDTO updateProducto(Long id, ProductoRequestDTO request){

        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());

        Producto actualizado = productoRepository.save(producto);

        return mapToResponse(actualizado);
    }

    public ProductoResponseDTO getProductoByNombre(String nombre){

        Producto producto = productoRepository.findByNombre(nombre);

        if(producto == null){
            throw new RuntimeException("No existe un producto con el nombre: " + nombre);
        }

        return mapToResponse(producto);
    }

    private ProductoResponseDTO mapToResponse(Producto producto){
        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setDescripcion(producto.getDescripcion());
        response.setPrecio(producto.getPrecio());
        response.setStock(producto.getStock());
        return response;
    }
}