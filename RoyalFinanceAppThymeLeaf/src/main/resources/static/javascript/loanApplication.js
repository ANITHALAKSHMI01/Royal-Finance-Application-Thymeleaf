function calculateMaxLoan() 
{
    var salary = document.getElementById('sal').value;
    var maxLoan = salary * 10;
    return maxLoan;
}
function calculateMaxTenure(loanAmount) 
{
	if(loanAmount<=30000 && loanAmount>=10000)
	{
		return 6; 
	}
    else if (loanAmount <= 50000) 
    {
        return 12; 
    } else if (loanAmount <= 100000)
    {
        return 24; 
    } else 
    {
        return 36;
    }
}
function validateSalary() 
{
    var salary = document.getElementById('sal').value;
    var salaryError = document.getElementById('salaryError');
    if (salary < 0) 
    {
        salaryError.textContent = "Salary cannot be negative.";
        salaryError.classList.add('active');
    } 
    else if (salary < 8000)
    {
        salaryError.textContent = "Salary must be Rs.8000 or more.";
        salaryError.classList.add('active');
    } else 
    {
        salaryError.textContent = "";
        salaryError.classList.remove('active');
    }
}
function validateLoan() 
{
    var loan = document.getElementById('loan').value;
    var loanError = document.getElementById('loanError');
    var maxLoan = calculateMaxLoan();
    if (loan < 0) 
    {
        loanError.textContent = "Loan amount cannot be negative.";
        loanError.classList.add('active');
    } 
    else if (loan > maxLoan) 
    {
        loanError.textContent = "Loan amount exceeds maximum allowed Rs." + maxLoan + ".";
        loanError.classList.add('active');
    }
    else 
    {
        loanError.textContent = "";
        loanError.classList.remove('active');
    }
}

function validateTenure()
{
	var loan = document.getElementById('loan').value;
    var tenure = document.getElementById('repay').value;
    var tenureError = document.getElementById('tenureError');
    var maxTenure=calculateMaxTenure(loan);
    if (tenure < 0) 
    {
        tenureError.textContent = "Tenure cannot be negative.";
        tenureError.classList.add('active');
    }
    else if(tenure>maxTenure)
    {
    	 tenureError.textContent = "Maximum tenure allowed for Rs."+loan+" is " + maxTenure + " months.";
         tenureError.classList.add('active');
    }
    else 
    {
        tenureError.textContent = "";
        tenureError.classList.remove('active');
    }
}
function validateForm() 
{
    var salary = document.getElementById('sal').value;
    var loan = document.getElementById('loan').value;
    var tenure = document.getElementById('repay').value;
    var salaryError = document.getElementById('salaryError');
    var loanError = document.getElementById('loanError');
    var tenureError = document.getElementById('tenureError');
    var maxLoan = calculateMaxLoan();
    var maxTenure=calculateMaxTenure(loan);
    var isValid = true;

    if (salary < 8000)
    {
        salaryError.textContent = "Salary must be Rs.8000 or more.";
        salaryError.classList.add('active');
        isValid = false;
    } 
    else 
    {
        salaryError.textContent = "";
        salaryError.classList.remove('active');
    }
    if (loan < 0) 
    {
        loanError.textContent = "Loan amount cannot be negative.";
        loanError.classList.add('active');
        isValid = false;
    } 
    else if (loan > maxLoan) 
    {
        loanError.textContent = "Loan amount exceeds maximum allowed Rs." + maxLoan + ".";
        loanError.classList.add('active');
        isValid = false;
    }
    else 
    {
        loanError.textContent = "";
        loanError.classList.remove('active');
    }
    if (tenure < 0)
    {
        tenureError.textContent = "Tenure cannot be negative.";
        tenureError.classList.add('active');
        isValid = false;
    } 
    else if(tenure>maxTenure)
    {
    	tenureError.textContent = "Maximum tenure allowed for Rs."+loan+"  is " + maxTenure + " months.";
        tenureError.classList.add('active');
        isValid = false;
    }
    else
    {
        tenureError.textContent = "";
        tenureError.classList.remove('active');
    }
    return isValid;
}
