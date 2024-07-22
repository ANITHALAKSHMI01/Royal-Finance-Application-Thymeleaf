package com.chainsys.royalfinance.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.chainsys.royalfinance.model.Account;
public class AccountMapper implements RowMapper<Account>
{
	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Account account=new Account();
		account.setAccountNo(rs.getLong(1));
		account.setUserId(rs.getString(2));
		account.setIfsc(rs.getString(3));
		account.setPan(rs.getString(4));
		account.setTotalBalance(rs.getInt(5));
		return account;
	}
	//account_no,user_id,ifsc,pan,total_balance
}
