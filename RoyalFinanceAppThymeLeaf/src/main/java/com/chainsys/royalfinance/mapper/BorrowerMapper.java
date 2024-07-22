package com.chainsys.royalfinance.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.chainsys.royalfinance.model.Borrower;
public class BorrowerMapper implements RowMapper<Borrower> 
{
	@Override
	public Borrower mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Borrower borrower=new Borrower();
		borrower.setApplicationId(rs.getInt(1));
		borrower.setBorrowerId(rs.getString(2));
		borrower.setSalary(rs.getInt(3));
		borrower.setLoanAmount(rs.getInt(4));
		borrower.setTenure(rs.getInt(5));
		borrower.setPaySlip(rs.getBytes(6));
		borrower.setPanProof(rs.getBytes(7));
		borrower.setGovernmentId(rs.getBytes(8));
		borrower.setStatus(rs.getString(9));
		return borrower;
	}
}
