package com.project.data.mybatis.typehandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.project.api.data.enums.Star;

public class StarTypeHandler implements TypeHandler<Star> {

	@Override
	public void setParameter(java.sql.PreparedStatement ps, int i, Star parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getId());

	}


	@Override
	public Star getResult(ResultSet rs, String columnName) throws SQLException {
		return Star.getById(rs.getInt(columnName));
	}

	@Override
	public Star getResult(ResultSet rs, int columnIndex) throws SQLException {
		return Star.getById(rs.getInt(columnIndex));
	}

	@Override
	public Star getResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
		return Star.getById(cs.getInt(columnIndex));
	}
}