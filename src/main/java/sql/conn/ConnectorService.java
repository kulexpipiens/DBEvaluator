package sql.conn;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ManagedBean(name="connector")
@ApplicationScoped
public class ConnectorService implements Serializable, Connector {

	private static final long serialVersionUID = -955086828999612869L;
	private DataSource dataSource;
	private Connection connection; 
	
	public ConnectorService() throws NamingException, SQLException {
		InitialContext ctx = new InitialContext();
		dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ConnectorDB");
		connection = dataSource.getConnection();
	}
	
	
	public Connection getConnection() throws SQLException {
		return connection;
	}
		
}
