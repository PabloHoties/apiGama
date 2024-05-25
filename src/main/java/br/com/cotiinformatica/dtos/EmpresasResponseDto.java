package br.com.cotiinformatica.dtos;

import java.sql.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class EmpresasResponseDto {

	private UUID id;
	private String entrada;
	private Date dataHora;
	private Double valor;
	private String historico;
	private Double saldoAnterior;
	}
