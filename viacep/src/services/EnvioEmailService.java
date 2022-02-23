package services;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;

public class EnvioEmailService {
	
	private static final Cep cep = ViaCepClient.findCep("17013905");
		
	String email = ""; // configure seu email
	String senha = ""; // configure sua senha
		
	public void enviarEmail() {
		
		SimpleEmail novoEmail = configurarServidorSMTP();
		try {
			envioEmailComSucesso(novoEmail);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	// envio de email com sucesso
	private void envioEmailComSucesso(SimpleEmail novoEmail) throws EmailException {
		novoEmail.setFrom(email);
		novoEmail.setSubject("Teste de envio de email!");
		novoEmail.setMsg("Teste de envio de CEP: \n" + cep);
		novoEmail.addTo(""); // configure o email para envio
		
		novoEmail.send();
		System.out.println("Enviado com sucesso!");
	}

	// configuração de servidor smtp
	private SimpleEmail configurarServidorSMTP() {
		SimpleEmail novoEmail = new SimpleEmail();
		
		novoEmail.setHostName("smtp.gmail.com");
		novoEmail.setSmtpPort(465);
		novoEmail.setAuthenticator(new DefaultAuthenticator(email, senha));
		novoEmail.setSSLOnConnect(true);
		
		return novoEmail;
	}
}
