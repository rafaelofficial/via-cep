package application;

import java.util.List;
import java.util.Scanner;

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;

import dao.CepDao;
import dao.DaoFactory;
import services.EnvioEmailService;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		CepDao cepDao = DaoFactory.criaCepDao();
		EnvioEmailService service = new EnvioEmailService();
		
		System.out.print("\nInforme um CEP válido: ");
		String cep = sc.nextLine();
		Cep novoCep = ViaCepClient.findCep(cep);
				
		System.out.println("\n--- Teste #1: Cep insert ---");
		cepDao.insert(novoCep);
		System.out.println("\nInserido! Novo Cep: " + novoCep.getCep());
	
		System.out.println("\n--- Teste #2: Cep findAll ---");
		List<Cep> list = cepDao.findAll();
		for (Cep listaCep : list) {
			System.out.println(listaCep);
		}	
		
		System.out.println("\n--- Teste #3: Envio de e-mail ---");
		service.enviarEmail();
		
		sc.close();
	}
}
