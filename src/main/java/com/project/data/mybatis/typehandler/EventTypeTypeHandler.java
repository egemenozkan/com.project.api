package com.project.data.mybatis.typehandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.project.api.data.model.event.EventType;

public class EventTypeTypeHandler implements TypeHandler<EventType> {

	@Override
	public void setParameter(java.sql.PreparedStatement ps, int i, EventType parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getId());

	}

	@Override
	public EventType getResult(ResultSet rs, String columnName) throws SQLException {
		return EventType.getById(rs.getInt(columnName));
	}

	@Override
	public EventType getResult(ResultSet rs, int columnIndex) throws SQLException {
		return EventType.getById(rs.getInt(columnIndex));
	}

	@Override
	public EventType getResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
		return EventType.getById(cs.getInt(columnIndex));
	}

}