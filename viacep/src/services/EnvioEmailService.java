package services;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;

public class EnvioEmailService {
	
	private static Cep dadosEndereco = ViaCepClient.findCep("13382440");
		
	String email = "developmentjavafortests@gmail.com"; // configure seu email
	String senha = "#Rafa123"; // configure sua senha
		
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
		novoEmail.setSubject("Aqui está as informações da sua busca de endereço");
		novoEmail.setMsg("Endereço: " 
				+ "RUA: " + dadosEndereco.getLogradouro()
				+ ", BAIRRO: " + dadosEndereco.getBairro()
				+ ", COMPLEMENTO: " + dadosEndereco.getComplemento()
				+ ", CEP: " + dadosEndereco.getCep()
				+ ", LOCALIDADE: " + dadosEndereco.getLocalidade()
				+ ", DDD: " + dadosEndereco.getDdd()
				+ ", DADOS DO IBGE: " + dadosEndereco.getIbge());
		novoEmail.addTo("developmentjavafortests@gmail.com"); // configure o email para envio
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
	
	/**
	 * @param cep
	 * @return Envia por email os dados do cep buscado pelo usuário
	 */
	public Cep enviarCepParaEmail(String cep) {
		return dadosEndereco = ViaCepClient.findCep(cep);
	}
}
