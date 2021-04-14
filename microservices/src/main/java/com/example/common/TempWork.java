package com.example.common;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.jdbc.Work;

public class TempWork implements Work {
	private CallableStatement call;
	private PreparedStatement preparedStatement;
	private final String sql;

	public TempWork(String sql) {
		this.sql = sql;
	}

	@Override
	public void execute(Connection connection) throws SQLException {
		call = connection.prepareCall(sql);
		preparedStatement = connection.prepareStatement(sql);
	}

	public CallableStatement getCall() {
		return call;
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}
}
