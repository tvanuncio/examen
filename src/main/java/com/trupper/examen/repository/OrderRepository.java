package com.trupper.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trupper.examen.entity.Orden;

public interface OrderRepository extends JpaRepository<Orden, Long> {

}
