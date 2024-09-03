package com.trupper.examen.entity;

import com.trupper.examen.dto.SucursalDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Sucursal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	public SucursalDto convertToDto() {
		SucursalDto suc = new SucursalDto();
		suc.setId(this.getId());
		suc.setNombre(this.getNombre());
		return suc;
	}

}
