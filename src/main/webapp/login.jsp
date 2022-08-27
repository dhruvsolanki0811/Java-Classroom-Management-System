<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Somaiya</title>
    <link rel="stylesheet" href="login.css">
    <!---we had linked our css file----->
</head>
<body>
    <div class="full-page">
        <div class="navbar">
            <div>
                <a href='login.jsp'>Somaiya</a>
            </div>
            <nav>
                <ul id='MenuItems'>
                    <li><button class='loginbtn' onclick="document.getElementById('login-form').style.display='block'" style="width:auto;">Login</button></li>
                </ul>
            </nav>
        </div>
        <div id='login-form'class='login-page'>
            <div class="form-box">
                <div class='button-box'>
                    <div id='btn'></div>
                    <button type='button'onclick='student()'class='toggle-btn'>Student</button>
                    <button type='button'onclick='teacher()'class='toggle-btn'>Teacher</button>
                    <button type='button'onclick='admin()'class='toggle-btn'>Admin</button>
                </div>
                <form id='student' class='input-group-student'>
                    <input type='text'class='input-field'placeholder='Email Id' required >
		            <input type='password'class='input-field'placeholder='Enter Password' required>
		            <input type='checkbox'class='check-box'><span>Remember Password</span>
		            <button type='submit'class='submit-btn'>Log in</button>
		        </form>
		        <form id='teacher' class='input-group-teacher'>
		            <input type='text'class='input-field'placeholder='Email Id' required >
		            <input type='password'class='input-field'placeholder='Enter Password' required>
		            <input type='checkbox'class='check-box'><span>Remember Password</span>
		            <button type='submit'class='submit-btn'>Log in</button>
	            </form>
                <form id='admin' class='input-group-admin' action="adminlogin" method="post">
                    <input type='text'class='input-field'placeholder='Email Id' required name="amail">
		            <input type='password'class='input-field'placeholder='Enter Password' required name="apass" >
		            <input type='checkbox'class='check-box'><span>Remember Password</span>
		            <button type='submit'class='submit-btn'>Log in</button>
		        </form>
            </div>
        </div>
    </div>
    <script>
        var x=document.getElementById('student');
		var y=document.getElementById('teacher');
        var a=document.getElementById('admin');
		var z=document.getElementById('btn');
		function teacher()
		{
			x.style.left='-400px';
			y.style.left='50px';
            a.style.left='800px';
			z.style.left='110px';
		}
		function student()
		{
			x.style.left='50px';
			y.style.left='450px';
			a.style.left='800px';
            z.style.left='20px';
		}
        function admin()
        {
            x.style.left='-800px';
            y.style.left='-400px';
            a.style.left='50px';
            z.style.left='220px';
        }
	</script>
	<script>
        var modal = document.getElementById('login-form');
        window.onclick = function(event) 
        {
            if (event.target == modal) 
            {
                modal.style.display = "none";
            }
        }
    </script>
</body>
</html>