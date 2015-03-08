package sql.conn;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	private List<Object[]> resultList = new ArrayList<>();
	private Integer resultCount;
	private List<String> culumnsNames = new ArrayList<>();
	private String select;
	private String message;

	@ManagedProperty(value = "#{connector}")
	Connector connector;

	public void evaluate() {
		Connection connection = null;
		try {
			connection = connector.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(select);
			this.resultSet = prepareStatement.executeQuery();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			resultCount = resultSetMetaData.getColumnCount();

			// fill columns names
			for (int i = 1; i <= resultCount; i++) {
				culumnsNames.add(resultSetMetaData.getColumnName(i));
			}

			// add rows
			while (resultSet.next()) {
				Object[] oneRow = new Object[resultCount];
				for (int i = 1; i <= resultCount; i++) {
					oneRow[i - 1] = resultSet.getObject(i);
				}
				resultList.add(oneRow);
			}
			message = "";
		} catch (MySQLSyntaxErrorException e) {
			message = e.getMessage();
		} catch (SQLException e) {
			message = e.getMessage();
			e.printStackTrace();
		}
	}

	public java.sql.ResultSet getResultSet() {
		return resultSet;
	}

	public List<Object[]> getResultList() throws SQLException {
		return resultList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getCulumnsNames() {
		return culumnsNames;
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
		this.select = select;
	}

	public String getMessage() {
		return message;
	}

}
