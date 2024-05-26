package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class EmpresaRepository {

	public List<Empresa> findAll() throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement(
			    "WITH RankedEntries AS ( " +
			    "    SELECT EmpresaId, Entrada, DataHora, Valor, Historico, SaldoAnterior, " +
			    "           ROW_NUMBER() OVER (PARTITION BY CAST(DataHora AS DATE) ORDER BY DataHora DESC) AS RowNum " +
			    "    FROM Financeiro.FluxoBancario " +
			    ") " +
			    "SELECT EmpresaId, Entrada, DataHora, Valor, Historico, SaldoAnterior " +
			    "FROM RankedEntries " +
			    "WHERE RowNum = 1 " +
			    "ORDER BY DataHora DESC " +
			    "OFFSET 0 ROWS " +
			    "FETCH NEXT 1000 ROWS ONLY"
			);
		
		ResultSet resultSet = statement.executeQuery();
		
		List<Empresa> lista = new ArrayList<Empresa>();
		while (resultSet.next()) {
			
			Empresa empresa = new Empresa();
			
			empresa.setId(UUID.fromString(resultSet.getString("EmpresaId")));
			empresa.setEntrada(resultSet.getString("Entrada"));

			// Fazendo o parse da data
			String dataHoraString = resultSet.getString("DataHora");
			Date dataHora = parseDataHora(dataHoraString);
			empresa.setDataHora(dataHora);
			empresa.setValor(Double.parseDouble(resultSet.getString("Valor")));
			empresa.setHistorico(resultSet.getString("Historico"));
			empresa.setSaldoAnterior(Double.parseDouble(resultSet.getString("SaldoAnterior")));

			lista.add(empresa);
		}
		connection.close();
		return lista;
	}

	// Método para fazer o parse da data
	private Date parseDataHora(String dataHoraString) {
		
		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
		
		try {
			return formato.parse(dataHoraString);
		} catch (ParseException e) {
			e.printStackTrace(); // Ou faça outro tratamento de exceção adequado ao seu caso
			return null;
		}
	}
	
	/*
	public static void main(String[] args) {
		
		EmpresaRepository empresaRepository = new EmpresaRepository();
		try {
			List<Empresa> empresas =  empresaRepository.findAll();
			System.out.println(empresas);
		}
		catch(Exception e) {
			
		}
		
	}
	*/

}
