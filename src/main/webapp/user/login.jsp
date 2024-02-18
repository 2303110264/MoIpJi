<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MoIpJi-Login</title>
</head>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<link rel="stylesheet" href="../css/loginCss.css">
<script src="../js/loginJs.js"></script>
<!-- 
 -->
<body>

<div class="container" id="container">
        <div class="form-container sign-up-container">
            <form action="loginProcess.jsp" method="post">
                <h1>Create Account</h1><br>
                <!-- 
                 <div class="social-container">
                    <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                    <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                    <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                </div>
                <span>or use your email for registration</span>
                 -->
                <input type="text" name="id" placeholder="ID" />
                <input type="email" name="mail" placeholder="Email" />
                <input type="password" name="password" placeholder="Password" />
                <input type="password" name="checkPassword" placeholder="Check password" />
				<br><small>
				option</small>
                <input type="text" name="nickname" placeholder="Nickname" />
                <label for="gender">
	                <select name="gender" id="gender">
						<option value="">Gender</option>
						<option value="Female">Female</option>
						<option value="Male">Male</option>
						<option value="Undefined">Undefined</option>
	                </select>
                </label>
                <input type="date" placeholder="Birthday" id="birthday"
                value="2024-02-16" min="1900-01-01" max="2024-02-16"/>
                <br>
                <button type="submit">Sign Up</button>
            </form>
        </div>
        <div class="form-container sign-in-container">
            <form action="signUpProcess.jsp" method="post">
                <h1>Sign in</h1>
                <!-- 
                <div class="social-container">
                    <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                    <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                    <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                </div>
                <span>or use your account</span>
                 -->
                <input type="text" placeholder="ID" />
                <input type="password" placeholder="Password" />
                <div id="rememberMe">
               		<input type="checkbox" name="rememberMe" id="rememberMeBox">
                	&nbsp;&nbsp;Remember me
                </div>
                <a href="#">Forgot your password?</a>
                <button type="submit">Login</button>
            </form>
        </div>
        <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel overlay-left">
                    <h1>Welcome!</h1>
                    <p>If you have an account?</p>
                    <button class="ghost" id="signIn">Login</button>
                </div>
                <div class="overlay-panel overlay-right">
                    <h1>Hello, Friend!</h1>
                    <p>It's a good day for you to join us!</p>
                    <button class="ghost" id="signUp">Sign Up</button>
                </div>
            </div>
        </div>
    </div>

	<!-- 
<div class="footer">
<b>	Follow me on </b>
	<div class="icons">
		<a href="https://github.com/2303110264" target="_blank" class="social"><i class="fab fa-github"></i></a>
		<a href="https://www.instagram.com/vaibhavkhulbe143/" target="_blank" class="social"><i class="fab fa-instagram"></i></a>
		<a href="https://medium.com/@vaibhavkhulbe" target="_blank" class="social"><i class="fab fa-medium"></i></a>
		<a href="https://twitter.com/vaibhav_khulbe" target="_blank" class="social"><i class="fab fa-twitter-square"></i></a>
		<a href="https://linkedin.com/in/vaibhav-khulbe/" target="_blank" class="social"><i class="fab fa-linkedin"></i></a>
		</div>
	</div>
	 -->
</body>
</html>