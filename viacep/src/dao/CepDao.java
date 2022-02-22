package dao;

import java.util.List;

import com.gtbr.domain.Cep;

public interface CepDao {
	
	void insert(Cep obj);
	List<Cep> findAll();
}
