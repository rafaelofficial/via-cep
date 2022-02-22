package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cep> findAll() {
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			// preparar para a consulta sql
			statement = conn.prepareStatement("SELECT * FROM tb_cidade");
			// executa a query
			resultSet = statement.executeQuery();
			
			List<Cep> list = new ArrayList<>();
			Map<Integer, Cep> map = new HashMap();
			
			// testa se veio algum resultado
			while (resultSet.next()) {
				Cep cepMap = map.get(resultSet.getInt("Id"));
				if (cepMap == null) {
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
					
					list.add(cep);
				}
				return list;
			}	
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
			DB.closeResultSet(resultSet);
		}	
		return null;
	}
}
