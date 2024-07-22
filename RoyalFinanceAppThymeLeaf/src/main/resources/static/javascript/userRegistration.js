function validateName() 
{
	var name = document.getElementById('name').value;
	var nameError = document.getElementById('nameError');
	var namePattern = /^[A-Za-z\s]*$/;
	if (!namePattern.test(name))
	{
		nameError.textContent = "Name should be alphabet.";
		nameError.classList.add('active');
	}
	else
	{
		nameError.textContent = "";
		nameError.classList.remove('active');
	}
}
function validateEmail()
{
	var email = document.getElementById('email').value;
	var emailError = document.getElementById('emailError');
	var emailPattern = /^[a-z0-9_\-\/.]+@[a-z]+\.[a-z]{2,}$/;
	if (!emailPattern.test(email)) 
	{
		emailError.textContent = "Invalid email format (Ex. xyz01@gmail.com).";
		emailError.classList.add('active');
	}
	else 
	{
		emailError.textContent = "";
		emailError.classList.remove('active');
	}
}
function validatePhoneNo() 
{
	var phone = document.getElementById('phone').value;
	var phoneNoError = document.getElementById('phoneNoError');
	var phonePattern = /^[6789][0-9]{9}$/;
	if (!phonePattern.test(phone)) 
	{
		phoneNoError.textContent = "Phone should start with 6-9 and 10 digits must.";
		phoneNoError.classList.add('active');
	} 
	else 
	{
		phoneNoError.textContent = "";
		phoneNoError.classList.remove('active');
	}
}
function validatePassword() 
{
	var password = document.getElementById('pass').value;
	var passwordError = document.getElementById('passwordError');
	var passwordPattern = /(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@!#$%^&*]).{6}$/;
	if (!passwordPattern.test(password)) 
	{
		passwordError.textContent = "Password should contain atleast one lowercase,uppercase,number,special character and 6 digits must.";
		passwordError.classList.add('active');
	} else 
	{
		passwordError.textContent = "";
		passwordError.classList.remove('active');
	}
}

function validateForm() 
{
	var name = document.getElementById('name').value;
	var email = document.getElementById('email').value;
	var phone = document.getElementById('phone').value;
	var password = document.getElementById('pass').value;
	var isValid = true;
	var namePattern = /^[A-Za-z\s]*$/;
	if (!namePattern.test(name)) 
	{
		document.getElementById('nameError').textContent = "Name should be alphabet.";
		document.getElementById('nameError').classList.add('active');
		isValid = false;
	}
	else 
	{
		document.getElementById('nameError').textContent = "";
		document.getElementById('nameError').classList.remove('active');
	}
	var emailPattern = /^[a-z0-9_\-\/.]+@[a-z]+\.[a-z]{2,}$/;
	if (!emailPattern.test(email)) {
		document.getElementById('emailError').textContent = "Invalid email format (Ex. xyz01@gmail.com).";
		document.getElementById('emailError').classList.add('active');
		isValid = false;
	}
	else 
	{
		document.getElementById('emailError').textContent = "";
		document.getElementById('emailError').classList.remove('active');
	}
	var phonePattern = /^[6789][0-9]{9}$/;
	if (!phonePattern.test(phone)) 
	{
		document.getElementById('phoneNoError').textContent = "Phone should start with 6-9 and 10 digits must.";
		document.getElementById('phoneNoError').classList.add('active');
		isValid = false;
	}
	else 
	{
		document.getElementById('phoneNoError').textContent = "";
		document.getElementById('phoneNoError').classList.remove('active');
	}
	var passwordPattern = /(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@!#$%^&*]).{6}$/;
	if (!passwordPattern.test(password)) 
	{
		document.getElementById('passwordError').textContent = "Password should contain atleast one lowercase,uppercase,number,special character and 6 digits must.";
		document.getElementById('passwordError').classList.add('active');
		isValid = false;
	}
	else 
	{
		document.getElementById('passwordError').textContent = "";
		document.getElementById('passwordError').classList.remove('active');
	}
	return isValid;
}