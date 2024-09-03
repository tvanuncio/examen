package com.trupper.examen.entity;

import java.math.BigDecimal;

import com.trupper.examen.dto.ProductoDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Orden orden;
	private String codigo;
	private String descripcion;
	private BigDecimal precio;
	
	public ProductoDto convertDto () {
		ProductoDto pr = new ProductoDto();
		pr.setCodigo(this.getCodigo());
		pr.setDescripcion(this.getDescripcion());
		pr.setId(this.getId());
		if (this.getOrden() != null) {
		   pr.setOrden(this.getOrden().convertToDto());
		}
		
		pr.setPrecio(this.getPrecio());
		return pr;
	}
}
