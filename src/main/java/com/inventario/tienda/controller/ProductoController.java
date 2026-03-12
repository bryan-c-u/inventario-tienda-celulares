package com.inventario.tienda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inventario.tienda.dto.ProductoRequestDTO;
import com.inventario.tienda.dto.ProductoResponseDTO;
import com.inventario.tienda.services.ProductosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productos")
public class ProductoController {

    private final ProductosService productoService;

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> createProducto(@RequestBody ProductoRequestDTO productoRequestDTO) {

        ProductoResponseDTO response = productoService.createProducto(productoRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getProductos() {

        List<ProductoResponseDTO> response = productoService.getProductos();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> getProductoById(@PathVariable Long id) {

        ProductoResponseDTO response = productoService.getProductoById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ProductoResponseDTO> getProductoByNombre(@PathVariable String nombre) {

        ProductoResponseDTO response = productoService.getProductoByNombre(nombre);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> updateProducto(
            @PathVariable Long id,
            @RequestBody ProductoRequestDTO productoRequestDTO) {

        ProductoResponseDTO updatedProducto = productoService.updateProducto(id, productoRequestDTO);

        return ResponseEntity.ok(updatedProducto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> deleteProducto(@PathVariable Long id) {

        ProductoResponseDTO response = productoService.deleteProducto(id);

        return ResponseEntity.ok(response);
    }
}