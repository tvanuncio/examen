package com.trupper.examen.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductoDto {
	private Long id;	
	private OrdenDto orden;
	private String codigo;
	private String descripcion;
	private BigDecimal precio;
}
