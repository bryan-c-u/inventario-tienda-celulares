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

    
    public ProductoResponseDTO createProducto(ProductoRequestDTO productoRequestDTO) {

        
        if(productoRequestDTO.getNombre() == null || productoRequestDTO.getNombre().isEmpty()){
            throw new RuntimeException("El nombre del producto es obligatorio");
        }

        if(productoRequestDTO.getPrecio() == null || productoRequestDTO.getPrecio() <= 0){
            throw new RuntimeException("El precio debe ser mayor a 0");
        }

        if(productoRequestDTO.getStock() == null || productoRequestDTO.getStock() < 0){
            throw new RuntimeException("El stock no puede ser negativo");
        }

        
        Producto productoExistente = productoRepository.findByNombre(productoRequestDTO.getNombre());

        
        if(productoExistente != null){

            productoExistente.setStock(
                productoExistente.getStock() + productoRequestDTO.getStock()
            );

            Producto productoActualizado = productoRepository.save(productoExistente);

            ProductoResponseDTO response = new ProductoResponseDTO();
            response.setId(productoActualizado.getId());
            response.setNombre(productoActualizado.getNombre());
            response.setDescripcion(productoActualizado.getDescripcion());
            response.setPrecio(productoActualizado.getPrecio());
            response.setStock(productoActualizado.getStock());

            return response;
        }

        Producto producto = new Producto();
        producto.setNombre(productoRequestDTO.getNombre());
        producto.setDescripcion(productoRequestDTO.getDescripcion());
        producto.setPrecio(productoRequestDTO.getPrecio());
        producto.setStock(productoRequestDTO.getStock());

        productoRepository.save(producto);

        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setDescripcion(producto.getDescripcion());
        response.setPrecio(producto.getPrecio());
        response.setStock(producto.getStock());

        return response;
    }

    public List<ProductoResponseDTO> getProductos(){

        List<Producto> productos = productoRepository.findAll();
        List<ProductoResponseDTO> lista = new ArrayList<>();

        for(Producto producto : productos){

            ProductoResponseDTO response = new ProductoResponseDTO();
            response.setId(producto.getId());
            response.setNombre(producto.getNombre());
            response.setDescripcion(producto.getDescripcion());
            response.setPrecio(producto.getPrecio());
            response.setStock(producto.getStock());

            lista.add(response);
        }

        return lista;
    }

    public ProductoResponseDTO getProductoById(Long id){

        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setDescripcion(producto.getDescripcion());
        response.setPrecio(producto.getPrecio());
        response.setStock(producto.getStock());

        return response;
    }

    public ProductoResponseDTO deleteProducto(Long id){

        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setDescripcion(producto.getDescripcion());
        response.setPrecio(producto.getPrecio());
        response.setStock(producto.getStock());

        productoRepository.delete(producto);

        return response;
    }

    public ProductoResponseDTO updateProducto(Long id, ProductoRequestDTO productoRequestDTO){

        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        producto.setNombre(productoRequestDTO.getNombre());
        producto.setDescripcion(productoRequestDTO.getDescripcion());
        producto.setPrecio(productoRequestDTO.getPrecio());
        producto.setStock(productoRequestDTO.getStock());

        Producto productoActualizado = productoRepository.save(producto);

        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setId(productoActualizado.getId());
        response.setNombre(productoActualizado.getNombre());
        response.setDescripcion(productoActualizado.getDescripcion());
        response.setPrecio(productoActualizado.getPrecio());
        response.setStock(productoActualizado.getStock());

        return response;
    }
}