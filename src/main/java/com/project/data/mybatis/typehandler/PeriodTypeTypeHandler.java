package com.project.data.mybatis.typehandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.project.api.data.enums.PeriodType;

public class PeriodTypeTypeHandler implements TypeHandler<PeriodType> {

	@Override
	public void setParameter(java.sql.PreparedStatement ps, int i, PeriodType parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getId());

	}

	@Override
	public PeriodType getResult(ResultSet rs, String columnName) throws SQLException {
		return PeriodType.getById(rs.getInt(columnName));
	}

	@Override
	public PeriodType getResult(ResultSet rs, int columnIndex) throws SQLException {
		return PeriodType.getById(rs.getInt(columnIndex));
	}

	@Override
	public PeriodType getResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
		return PeriodType.getById(cs.getInt(columnIndex));
	}

}