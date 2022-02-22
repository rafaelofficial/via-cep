package application;

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;

public class Program {

	public static void main(String[] args) {
		Cep cep = ViaCepClient.findCep("13382440");
		System.out.println(cep);	
	}
}
