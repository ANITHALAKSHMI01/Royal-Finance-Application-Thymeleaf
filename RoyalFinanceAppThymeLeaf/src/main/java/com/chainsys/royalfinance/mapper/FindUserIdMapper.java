package com.chainsys.royalfinance.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
public class FindUserIdMapper implements RowMapper<String>
{
	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		String id=rs.getString(1);
		return id;
	}
}
