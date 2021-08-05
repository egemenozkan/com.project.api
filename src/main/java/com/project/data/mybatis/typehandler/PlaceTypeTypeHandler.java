package com.project.data.mybatis.typehandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.project.api.data.enums.PlaceType;

public class PlaceTypeTypeHandler implements TypeHandler<PlaceType> {

	@Override
	public void setParameter(java.sql.PreparedStatement ps, int i, PlaceType parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getId());

	}

	@Override
	public PlaceType getResult(ResultSet rs, String columnName) throws SQLException {
		return PlaceType.getById(rs.getInt(columnName));
	}

	@Override
	public PlaceType getResult(ResultSet rs, int columnIndex) throws SQLException {
		return PlaceType.getById(rs.getInt(columnIndex));
	}

	@Override
	public PlaceType getResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
		return PlaceType.getById(cs.getInt(columnIndex));
	}

}