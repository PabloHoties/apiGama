package br.com.cotiinformatica.entities;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class Empresa {

	private UUID id;
	private String entrada;
	private Date dataHora;
	private Double valor;
	private String historico;
	private Double saldoAnterior;
}
