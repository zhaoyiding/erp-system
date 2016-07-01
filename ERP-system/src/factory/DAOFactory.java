package factory;

import dao.EmployeeDAO;
import dao.MessageDAO;
import dao.ReplyDAO;
import daoImpl.EmployeeDAOImpl;
import daoImpl.MessageDAOImpl;
import daoImpl.ReplyDAOImpl;

public class DAOFactory {
	public static EmployeeDAO getEmployeeDAO() {
		return new EmployeeDAOImpl();
	}

	public static MessageDAO getMessageDAO() {
		return new MessageDAOImpl();
	}

	public static ReplyDAO getReplyDAO() {
		return new ReplyDAOImpl();
	}
}
