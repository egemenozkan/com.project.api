package com.project.data.mybatis.typehandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.project.common.enums.Language;

public class LanguageTypeHandler implements TypeHandler<Language> {

	@Override
	public void setParameter(java.sql.PreparedStatement ps, int i, Language parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getId());

	}

	@Override
	public Language getResult(ResultSet rs, String columnName) throws SQLException {
		return Language.getByCode(rs.getString(columnName));
	}

	@Override
	public Language getResult(ResultSet rs, int columnIndex) throws SQLException {
		return Language.getByCode(rs.getString(columnIndex));

	}

	@Override
	public Language getResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
		return Language.getByCode(cs.getString(columnIndex));
	}

}