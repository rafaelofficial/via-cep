package dao.impl;

import java.sql.Connection;
import java.util.List;

import com.gtbr.domain.Cep;

import dao.CepDao;

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
		// TODO Auto-generated method stub
		return null;
	}

}
