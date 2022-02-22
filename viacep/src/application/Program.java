package application;

import java.util.List;

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;

import dao.CepDao;
import dao.DaoFactory;

public class Program {

	public static void main(String[] args) {
		
		CepDao cepDao = DaoFactory.criaCepDao();
		
		//Cep cep = ViaCepClient.findCep("13382440");
		
		List<Cep> list = cepDao.findAll();
		for (Cep listaCep : list) {
			System.out.println(listaCep);
		}
	}
}
