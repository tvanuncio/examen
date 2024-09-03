package com.trupper.examen.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;


@Data
public class OrdenDto {

	private Long id;

	SucursalDto sucursal;
	
	List<ProductoDto> productos;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	Date fecha;
	
	BigDecimal total;
}
