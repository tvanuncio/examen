package com.trupper.examen.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trupper.examen.dto.OrdenDto;
import com.trupper.examen.dto.ProductoDto;
import com.trupper.examen.entity.Orden;
import com.trupper.examen.entity.Producto;
import com.trupper.examen.entity.Sucursal;
import com.trupper.examen.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	
	@Autowired
	private OrderService orderService;
	


	//@CrossOrigin(origins = "*")
	@PostMapping("/{sucursal}")
	
	public OrdenDto save (@RequestBody OrdenDto order, @PathVariable("sucursal") String sucursal) {
		
		return this.orderService.save(order, sucursal);
	}
	

	
	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	private OrdenDto findById (@PathVariable("id") Long id) {
		return this.orderService.findById(id);
	}
	
	//@CrossOrigin(origins = "*")
	@PutMapping("/{idOrder}/product/{idProduct}")
	private OrdenDto updatePrecio (@RequestBody ProductoDto producto,@PathVariable("idOrder") Long order, @PathVariable("idProduct")  Long id ) {
		
		return this.orderService.updateProducto (producto, order, id);
	}

	
}
