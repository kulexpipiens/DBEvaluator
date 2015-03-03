package sql.conn;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

@ManagedBean
@ViewScoped
public class ResultSet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6481731338017890578L;

	private java.sql.ResultSet resultSet;
	private List<Object> resultList = new ArrayList<>();
	private Integer resultCount;
	private String select;
	private String message= "nic";
	
	@ManagedProperty(value = "#{connector}")
	Connector connector;

	public java.sql.ResultSet getResultSet() {
		return resultSet;
	}

	public List<Object> getResultList() throws SQLException {
		return resultList;
	}

	public Integer getResultCount() throws SQLException {
		return resultCount;
	}

	public Connector getConnector() {
		return connector;
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		Connection connection = null;
		try {
			connection = connector.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement(select);
			this.resultSet = prepareStatement.executeQuery();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			resultCount = resultSetMetaData.getColumnCount();
			for (int i = 1; i < resultCount + 1; i++) {
				resultList.add(resultSetMetaData.getColumnName(i));
			}
			while (resultSet.next()) {
				for (int i = 1; i < resultCount + 1; i++) {
					resultList.add(resultSet.getObject(i));
				}
			}
			
		} catch (MySQLSyntaxErrorException e) {
			message = e.getMessage();
		} catch (SQLException e) {
			message = e.getMessage();
			e.printStackTrace();
		} 
	}

	public String getMessage() {
		return message;
	}

}
