package com.trupper.examen.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;

import com.trupper.examen.dto.OrdenDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "sucursal_id")
	Sucursal sucursal;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	List<Producto> productos;
	
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	Date fecha;
	
	BigDecimal total;
	
	public OrdenDto convertToDto() {
		OrdenDto order = new OrdenDto();
		order.setFecha(this.getFecha());
		order.setId(this.getId());
		order.setProductos(this.getProductos().stream().map(pr -> pr.convertDto()).collect(Collectors.toList()));
		order.setSucursal(this.getSucursal().convertToDto());
		order.setTotal(this.getTotal());
		return order;
	}
}
