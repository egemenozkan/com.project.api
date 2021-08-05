package com.project.data.mybatis.typehandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.project.api.data.enums.FeeType;

public class FeeTypeTypeHandler implements TypeHandler<FeeType> {

	@Override
	public void setParameter(java.sql.PreparedStatement ps, int i, FeeType parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getId());

	}


	@Override
	public FeeType getResult(ResultSet rs, String columnName) throws SQLException {
		return FeeType.getById(rs.getInt(columnName));
	}

	@Override
	public FeeType getResult(ResultSet rs, int columnIndex) throws SQLException {
		return FeeType.getById(rs.getInt(columnIndex));
	}

	@Override
	public FeeType getResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
		return FeeType.getById(cs.getInt(columnIndex));
	}
}