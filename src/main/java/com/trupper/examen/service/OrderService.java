package com.trupper.examen.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	public OrdenDto save (OrdenDto ordenDto, String sucursal ) {
		Orden orden = convertToEntity(ordenDto);
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
	
	private Orden convertToEntity (OrdenDto order) {
		Orden data = new Orden();
		
		data.setFecha(order.getFecha());
		data.setId(order.getId());
		data.setTotal(order.getTotal());
		
		if (order.getSucursal() != null) {
		   Sucursal suc = new Sucursal();	
		   suc.setId(order.getSucursal().getId());
		   suc.setNombre(order.getSucursal().getNombre());
		   data.setSucursal(suc);
		}
		
		if (order.getProductos() != null) {
			List<Producto> lista = order.getProductos().stream().map(dto -> {
			  Producto p = new Producto();
				p.setCodigo(dto.getCodigo());
				p.setDescripcion(dto.getDescripcion());
				p.setId(dto.getId());
				p.setPrecio(dto.getPrecio());
				return p;
		      }).collect(Collectors.toList());
		   data.setProductos(lista);
		  
		}
		return data;
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
