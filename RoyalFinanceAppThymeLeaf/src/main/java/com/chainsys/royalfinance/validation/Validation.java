package com.chainsys.royalfinance.validation;
import java.util.Random;
import org.springframework.stereotype.Repository;
import com.chainsys.royalfinance.exception.NumberException;
import com.chainsys.royalfinance.exception.EmailException;
import com.chainsys.royalfinance.exception.NameException;
import com.chainsys.royalfinance.exception.PasswordExcepion;
import com.chainsys.royalfinance.exception.PhoneNoException;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
//import net.sourceforge.tess4j.util.LoadLibs;
@Repository
public class Validation
{
	static Random random=new Random();
	public boolean checkName(String name) throws NameException
	{
		String regex="^[A-Za-z\\s]*$";
		if(name.matches(regex))
		{
			return false;
		}
		else
		{
			throw new NameException();
		}
	}
	public boolean checkEmail(String email) throws EmailException
	{
		String regex="^[a-z0-9_\\-\\/.]+@[a-z]+\\.[a-z]{2,}$";
		if(email.matches(regex))
		{
			return false;
		}
		else
		{
			throw new EmailException();
		}
	}
	public boolean checkPhoneNo(String phoneNo) throws PhoneNoException
	{
		String regex="^[6789]\\d{9}$";
		if(phoneNo.matches(regex))
		{
			return false;
		}
		else
		{
			throw new PhoneNoException();
		}
	}
	public boolean checkPassword(String password) throws PasswordExcepion
	{
		String regex="(?=.*[a-z])(?=.*[A-Z])(?=.*//d)(?=.*[@!#$%^&*]).{6}$";
		if(password.matches(regex))
		{
			return false;
		}
		else
		{
			throw new PasswordExcepion();
		}
	}
	public boolean checkAmount(String salary,String amount,String tenure) throws NumberException
	{
		String regex="\\d";
		if(salary.matches(regex) && amount.matches(regex) && tenure.matches(regex))
		{
			return false;
		}
		else
		{
			throw new NumberException();
		}
	}
	public boolean checkSalary(int salary,int loanAmount,int tenure)
	{
		int maxLoan=salary*10;
		int maxTenure=calculateMaxTenure(loanAmount);
		if(salary>=8000 && loanAmount<=maxLoan && tenure<=maxTenure)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public int calculateMaxTenure(int loanAmount)
	{
		 if (loanAmount <= 50000) 
		 {
		      return 12; 
		 } 
		 else if (loanAmount <= 100000)
		 {
		     return 24; 
		 } 
		 else 
		 {
		     return 36;
		 }
	}
	public long generateAccountNo()
	{
		long accountNo=random.nextLong(10000000000000l);
		return accountNo;
	}
	public String generatePAN()
	{
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) 
        {
            char c = (char) (random.nextInt(26) + 'A');
            sb.append(c);
        }
        String fourDigit=String.valueOf(random.nextInt(10000));
        sb.append(fourDigit);
        char lastChar = (char) (random.nextInt(26) + 'A');
        sb.append(lastChar);
		return sb.toString();
	}
	public String generateIFSC()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) 
		{
            char randomChar = (char)('A' + random.nextInt(26)); 
            sb.append(randomChar);
        }
		sb.append(0);
		String sixDigit=String.valueOf(random.nextInt(1000000));
		System.out.println(sixDigit);
		sb.append(sixDigit);
		System.out.println(sb);
		return sb.toString();
	}
//	 public Borrower extractAadhaarDetails(MultipartFile file) throws IOException, TesseractException 
//	 {
//	        File tempFile = File.createTempFile("temp-aadhaar", ".jpg");
//	        file.transferTo(tempFile);
//
//	        Tesseract tesseract = new Tesseract();
//	        tesseract.setDatapath(LoadLibs.extractTessResources("tessdata").getAbsolutePath()); // Set Tesseract data path
//
//	        String extractedText = tesseract.doOCR(tempFile);
//	        System.out.println("Extracted Text:\n" + extractedText);
//
//	        Borrower aadhaarDetails = parseBorrower(extractedText);
//
//	        tempFile.delete();
//
//	        return aadhaarDetails;
//	    }
//	    private Borrower parseBorrower(String extractedText)
//	    {
//	    	final String aadharRegex = "\\d{4}\\s\\d{4}\\s\\d{4}";
//	    	 Borrower details = new Borrower();
//
//	         Pattern pattern = Pattern.compile(aadharRegex);
//	         Matcher matcher = pattern.matcher(extractedText);
//	         if (matcher.find()) 
//	         {
//	             details.setAddress(matcher.group(0).replace(" ", ""));
//	         }
//	         System.out.println(details);
//	         return details;
//	    }
}
