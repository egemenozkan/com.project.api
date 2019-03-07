package com.project.api.data.mapper.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.project.api.data.enums.EventPeriodType;

public class EventPeriodTypeTypeHandler implements TypeHandler<EventPeriodType> {

	@Override
	public void setParameter(java.sql.PreparedStatement ps, int i, EventPeriodType parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getId());

	}

	@Override
	public EventPeriodType getResult(ResultSet rs, String columnName) throws SQLException {
		return EventPeriodType.getById(rs.getInt(columnName));
	}

	@Override
	public EventPeriodType getResult(ResultSet rs, int columnIndex) throws SQLException {
		return EventPeriodType.getById(rs.getInt(columnIndex));
	}

	@Override
	public EventPeriodType getResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
		return EventPeriodType.getById(cs.getInt(columnIndex));
	}

}