package com.chainsys.royalfinance.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.chainsys.royalfinance.model.Loan;
public class LoanMapper implements RowMapper<Loan>
{

	@Override
	public Loan mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		Loan loan=new Loan();
		loan.setLoanId(rs.getInt(1));
		loan.setBorrowerId(rs.getString(2));
		loan.setDate(rs.getString(3));
		loan.setInterest(rs.getInt(4));
		loan.setRemainingTenure(rs.getInt(5));
		loan.setInterestAmount(rs.getDouble(6));
		loan.setPrincipalAmount(rs.getDouble(7));
		loan.setBalanceLoanAmount(rs.getDouble(8));
		loan.setEmi(rs.getDouble(9));
		loan.setDueDate(rs.getString(10));
		loan.setPaymentStatus(rs.getString(11));
		loan.setTenure(rs.getInt(12));
		loan.setLoanAmount(rs.getDouble(13));
		return loan;
	}
}
