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
        try {

            ProductoResponseDTO response = productoService.createProducto(productoRequestDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getProductos() {
        try {

            List<ProductoResponseDTO> response = productoService.getProductos();

            return ResponseEntity.status(HttpStatus.FOUND).body(response);

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> getProductoById(@PathVariable Long id) {
        try {

            ProductoResponseDTO response = productoService.getProductoById(id);

            return ResponseEntity.status(HttpStatus.FOUND).body(response);

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> deleteProducto(@PathVariable Long id) {
        try {

            ProductoResponseDTO response = productoService.deleteProducto(id);

            return ResponseEntity.status(HttpStatus.FOUND).body(response);

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> updateProducto(
            @PathVariable Long id,
            @RequestBody ProductoRequestDTO productoRequestDTO) {

        try {

            ProductoResponseDTO updatedProducto = productoService.updateProducto(id, productoRequestDTO);

            return ResponseEntity.ok(updatedProducto);

        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }
    }

}