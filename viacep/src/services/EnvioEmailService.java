package services;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;

public class EnvioEmailService {
	
	private static final Cep cep = ViaCepClient.findCep("17013905");
		
	String email = "";
	String senha = "";
		
	public void enviarEmail() {
		SimpleEmail novoEmail = new SimpleEmail();
		
		novoEmail.setHostName("smtp.gmail.com");
		novoEmail.setSmtpPort(465);
		novoEmail.setAuthenticator(new DefaultAuthenticator(email, senha));
		novoEmail.setSSLOnConnect(true);
		
		try {
			novoEmail.setFrom(email);
			novoEmail.setSubject("Teste de envio de email!");
			novoEmail.setMsg("Teste de envio de CEP: \n" + cep);
			novoEmail.addTo(email);
			
			novoEmail.send();
			System.out.println("Enviado com sucesso!");
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
