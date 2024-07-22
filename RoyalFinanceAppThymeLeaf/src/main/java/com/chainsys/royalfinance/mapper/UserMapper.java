package com.chainsys.royalfinance.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.chainsys.royalfinance.model.User;
public class UserMapper implements RowMapper<User>
{
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		User user=new User();
		String id=rs.getString(1);
		String name=rs.getString(2);
		String dateOfBirth=rs.getString(3);
		long phoneNo=rs.getLong(4);
		String email=rs.getString(5);
		String location=rs.getString(6);
		user.setId(id);
		user.setName(name);
		user.setPhoneNo(phoneNo);
		user.setDateOfBirth(dateOfBirth);
		user.setEmail(email);
		user.setLocation(location);
		user.setActive(rs.getInt(7));
		return user;
	}

}
