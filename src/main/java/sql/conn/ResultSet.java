package sql.conn;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSet {
	private java.sql.ResultSet resultSet;
	private List<Object> resultList = new ArrayList<>();
	private Integer resultCount;
	
	
	public ResultSet(java.sql.ResultSet resultSet) throws SQLException {
		this.resultSet = resultSet;
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		resultCount = resultSetMetaData.getColumnCount();
		while (resultSet.next()){
			for (int i = 1; i < resultCount + 1; i++) {
				resultList.add(resultSet.getObject(i));
			}
		}
	}
		
	public java.sql.ResultSet getResultSet() {
		return resultSet;
	}
	
	public List<Object> getResultList() {
		return resultList;
	}

	public Integer getResultCount() {
		return resultCount;
	}
	
}
