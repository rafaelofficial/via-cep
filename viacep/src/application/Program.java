package application;

import java.util.Scanner;

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Cep cep = ViaCepClient.findCep("13382440");
		System.out.println(cep);
		sc.close();		
	}
}
