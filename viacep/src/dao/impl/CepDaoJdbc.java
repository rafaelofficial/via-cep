package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gtbr.domain.Cep;

import dao.CepDao;
import db.DB;
import db.DbException;

public class CepDaoJdbc implements CepDao {
	
	private Connection conn;
	
	public CepDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Cep obj) {
		PreparedStatement statement = null;
		
		try {
			statement = conn.prepareStatement(
					"INSERT INTO tb_cidade (Cep, Logradouro, Bairro, Localidade, Uf, Ibge, Gia, Ddd, siafi) \r\n"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			configuraPlaceholderInsert(obj, statement);
			totalDeLinhasAfetadas(statement);
			
		} catch (SQLException e) {
			throw new DbException("Erro não esperado! Nenhuma linha afetada!");
		} finally {
			DB.closeStatement(statement);
		}
	}

	// total de linha inseridas afetadas
	private void totalDeLinhasAfetadas(PreparedStatement statement) throws SQLException {
		int linhasAfetadas = statement.executeUpdate();
		if (linhasAfetadas > 0) {
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				statement.setInt(linhasAfetadas, id);
			}
			DB.closeResultSet(resultSet);
		}
	}

	// configurar os placeholder
	private void configuraPlaceholderInsert(Cep obj, PreparedStatement statement) throws SQLException {
		statement.setString(1, obj.getCep());
		statement.setString(2, obj.getLogradouro());
		statement.setString(3, obj.getBairro());
		statement.setString(4, obj.getLocalidade());
		statement.setString(5, obj.getUf());
		statement.setString(6, obj.getIbge());
		statement.setString(7, obj.getGia());
		statement.setString(8, obj.getDdd());
		statement.setString(9, obj.getSiafi());
	}

	@Override
	public List<Cep> findAll() {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		// preparar para a consulta sql e executa a query
		try {
			statement = conn.prepareStatement("SELECT * FROM tb_cidade");
			resultSet = statement.executeQuery();
			
			return verificarResultadoDaQuery(resultSet);
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
			DB.closeResultSet(resultSet);
		}
	}

	// testa se veio algum resultado
	private List<Cep> verificarResultadoDaQuery(ResultSet resultSet) throws SQLException {
		List<Cep> list = new ArrayList<>();
		Map<Integer, Cep> map = new HashMap<Integer, Cep>();

		while (resultSet.next()) {
			Cep cep = map.get(resultSet.getInt("Id"));
			if (cep == null) {
				cep = instaciarCep(resultSet);
				map.put(resultSet.getInt("Id"), cep);
			}
			list.add(cep);
		}	
		return list;
	}

	// instancia CEP
	private Cep instaciarCep(ResultSet resultSet) throws SQLException {
		Cep cep = new Cep();
		cep.setCep(resultSet.getString("Cep"));
		cep.setLogradouro(resultSet.getString("Logradouro"));
		cep.setComplemento(resultSet.getString("Complemento"));
		cep.setBairro(resultSet.getString("Bairro"));
		cep.setLocalidade(resultSet.getString("Localidade"));
		cep.setUf(resultSet.getString("Uf"));
		cep.setIbge(resultSet.getString("Ibge"));
		cep.setGia(resultSet.getString("Gia"));
		cep.setDdd(resultSet.getString("Ddd"));
		cep.setSiafi(resultSet.getString("siafi"));
		
		return cep;
	}
}
