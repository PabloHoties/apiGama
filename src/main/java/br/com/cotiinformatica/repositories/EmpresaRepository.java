package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class EmpresaRepository {

	public List<Empresa> findAll() throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement
				("select e.EmpresaId, e.Entrada, e.DataHora, e.Valor, e.Historico, e.SaldoAnterior from Financeiro.FluxoBancario e");

		ResultSet resultSet = statement.executeQuery();

		List<Empresa> lista = new ArrayList<Empresa>();

		while (resultSet.next()) {
			Empresa empresa = new Empresa();

			empresa.setId(UUID.fromString(resultSet.getString("EmpresaId")));
			empresa.setEntrada(resultSet.getString("Entrada"));
			
			//empresa.setDataHora(resultSet.getString("DataHora"));
			
			empresa.setValor(Double.parseDouble(resultSet.getString("Valor")));
			empresa.setHistorico(resultSet.getString("Historico"));
			empresa.setSaldoAnterior(Double.parseDouble(resultSet.getString("SaldoAnterior")));

			lista.add(empresa);

		}

		connection.close();
		return lista;
	}
	
}
