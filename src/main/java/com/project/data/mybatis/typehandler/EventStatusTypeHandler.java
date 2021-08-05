package com.project.data.mybatis.typehandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.project.api.data.model.event.EventStatus;


public class EventStatusTypeHandler implements TypeHandler<EventStatus> {

	@Override
	public void setParameter(java.sql.PreparedStatement ps, int i, EventStatus parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getId());

	}

	@Override
	public EventStatus getResult(ResultSet rs, String columnName) throws SQLException {
		return EventStatus.getById(rs.getInt(columnName));
	}

	@Override
	public EventStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
		return EventStatus.getById(rs.getInt(columnIndex));
	}

	@Override
	public EventStatus getResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
		return EventStatus.getById(cs.getInt(columnIndex));
	}

}