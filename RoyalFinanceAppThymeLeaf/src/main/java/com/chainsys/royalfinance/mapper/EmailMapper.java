package com.chainsys.royalfinance.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.chainsys.royalfinance.model.User;
public class EmailMapper implements RowMapper<User>
{
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		User user=new User();
		String email=rs.getString(1);
		String password=rs.getString(2);
		user.setEmail(email);
		user.setPassword(password);
		return user;
	}

}
