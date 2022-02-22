package application;

import java.util.List;

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;

import dao.CepDao;
import dao.DaoFactory;

public class Program {

	public static void main(String[] args) {
		
		CepDao cepDao = DaoFactory.criaCepDao();
		
		System.out.println("--- Teste #1: Cep insert ---");
		Cep novoCep = ViaCepClient.findCep("08375000");
		cepDao.insert(novoCep);
		System.out.println("\nInserido! Novo Cep: " + novoCep.getCep());
	
		System.out.println("\n--- Teste #2: Cep findAll ---");
		List<Cep> list = cepDao.findAll();
		for (Cep listaCep : list) {
			System.out.println(listaCep);
		}		
	}
}
