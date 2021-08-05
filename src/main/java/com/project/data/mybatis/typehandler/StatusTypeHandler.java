package com.project.data.mybatis.typehandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.project.api.data.enums.Status;

public class StatusTypeHandler implements TypeHandler<Status> {

	@Override
	public void setParameter(java.sql.PreparedStatement ps, int i, Status parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getId());

	}

	@Override
	public Status getResult(ResultSet rs, String columnName) throws SQLException {
		return Status.getById(rs.getInt(columnName));
	}

	@Override
	public Status getResult(ResultSet rs, int columnIndex) throws SQLException {
		return Status.getById(rs.getInt(columnIndex));
	}

	@Override
	public Status getResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
		return Status.getById(cs.getInt(columnIndex));
	}

}