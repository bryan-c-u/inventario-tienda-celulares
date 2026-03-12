package com.inventario.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.tienda.entity.Producto;


@Repository
public interface ProductosRepository extends JpaRepository<Producto, Long>{
    Producto findByNombre(String nombre);
}