package db;

public class DbException extends RuntimeException {

	static final long serialVersionUID = 1L;
	
	public DbException(String mensagem) {
		super(mensagem);
	}
}
