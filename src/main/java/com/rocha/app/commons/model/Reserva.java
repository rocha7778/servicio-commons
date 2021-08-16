package com.rocha.app.commons.model;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reserva")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1940117426283265687L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty(value="fechaIngreso", required = true)
    @NotNull(message="Por favor provee una fecha de ingreso para la reserva")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
	private Date fechaIngreso;
	
	@JsonProperty(value="fechaSalida", required = true)
    @NotNull(message="Por favor provee una fecha de salida para la reserva")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
	private Date fechaSalida;
	
	@JsonProperty(value="noPersonas", required = true)
    @NotNull(message="Por favor provee el numero de personas para la reserva")
	private int noPersonas;
	@JsonProperty(value="titularReserva", required = true)
    @NotNull(message="Por favor provee el nombre de la titular de la reserva")
	private String titularReserva;
	
	@JsonProperty(value="noHabitaciones", required = true)
    @NotNull(message="Por favor provee el numero de habitaciones para la reserva")
	private int noHabitaciones;
	
	private int noMenores;
	
	@JsonProperty(value="email", required = true)
    @NotNull(message="Por favor provee un correo para confirmar la reserva")
	private String email;
	@JsonInclude()
	@Transient
	private long totalDias;
	
	
	public long getTotalDias() {
		if(fechaSalida ==null || fechaIngreso == null) {
			return 0;
		}else {
			long diff = fechaSalida.getTime() - fechaIngreso.getTime();
			totalDias =  TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			return totalDias;
		}
		
	   
	}
}
