package com.project.data.mybatis.typehandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.project.api.data.enums.LandingPageType;

public class LandingPageTypeTypeHandler implements TypeHandler<LandingPageType> {

	@Override
	public void setParameter(java.sql.PreparedStatement ps, int i, LandingPageType parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getId());

	}

	@Override
	public LandingPageType getResult(ResultSet rs, String columnName) throws SQLException {
		return LandingPageType.getById(rs.getInt(columnName));
	}

	@Override
	public LandingPageType getResult(ResultSet rs, int columnIndex) throws SQLException {
		return LandingPageType.getById(rs.getInt(columnIndex));
	}

	@Override
	public LandingPageType getResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
		return LandingPageType.getById(cs.getInt(columnIndex));
	}

}