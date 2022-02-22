package dao;

import dao.impl.CepDaoJdbc;
import db.DB;

public class DaoFactory {
	
	/**
	 * @return instancia um novo CepDao
	 */
	public static CepDao criaCepDao() {
		return new CepDaoJdbc(DB.getConnection());
	}

}
