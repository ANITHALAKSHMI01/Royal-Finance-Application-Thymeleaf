package com.chainsys.royalfinance.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.chainsys.royalfinance.model.Payment;
public class PaymentMapper implements RowMapper<Payment>
{
	@Override
	public Payment mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Payment payment=new Payment();
		payment.setPaymentId(rs.getInt(1));
		payment.setUserId(rs.getString(2));
		payment.setDate(rs.getString(3));
		payment.setFromAccount(rs.getLong(4));
		payment.setToAccount(rs.getLong(5));
		payment.setAmount(rs.getInt(6));
		return payment;
	}
}
