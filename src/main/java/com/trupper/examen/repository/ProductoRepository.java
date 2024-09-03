package com.trupper.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trupper.examen.entity.Orden;
import com.trupper.examen.entity.Producto;
import com.trupper.examen.entity.Sucursal;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
