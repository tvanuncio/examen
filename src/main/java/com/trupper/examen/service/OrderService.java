package com.trupper.examen.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.trupper.examen.dto.OrdenDto;
import com.trupper.examen.dto.ProductoDto;
import com.trupper.examen.entity.Orden;
import com.trupper.examen.entity.Producto;
import com.trupper.examen.entity.Sucursal;
import com.trupper.examen.repository.OrderRepository;
import com.trupper.examen.repository.ProductoRepository;
import com.trupper.examen.repository.SucursalRepository;

@Component
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	SucursalRepository sucursalRepository;
	
	@Autowired
	ProductoRepository productoRepository;
	
	public OrdenDto save (Orden orden, String sucursal ) {
		Sucursal suc = new Sucursal();
		suc.setNombre(sucursal);
		Example<Sucursal> example = Example.of(suc);
		Sucursal sucursalOrder = null;
		Optional<Sucursal> optSucursal =  sucursalRepository.findOne(example);
		if (!optSucursal.isPresent()) {
			sucursalOrder = sucursalRepository.save(suc);
		}else {
			sucursalOrder = optSucursal.get();
		}
		
		orden.setSucursal(sucursalOrder);
		
		Orden orderSaved = orderRepository.save(orden);

		return orderSaved.convertToDto();
	}
	
	public OrdenDto findById (Long id) {
		
		Orden orden = orderRepository.findById(id).orElseThrow(() -> new RuntimeException());
		
		return orden.convertToDto();
	}
	
	public OrdenDto updateProducto (ProductoDto producto, Long order, Long id ) {
		
		Orden ordenTmp = orderRepository.findById(order).orElseThrow(() -> new RuntimeException());
		
	    
	    ordenTmp.getProductos().forEach( pr -> {
	       if (pr.getId() == id) {
	    	   pr.setPrecio(producto.getPrecio());
	       }
	    });
	    
	    return orderRepository.save(ordenTmp).convertToDto();
	    
		
	}

}
