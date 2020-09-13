<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login Admin Book Management </title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="/resources/auth/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/resources/auth/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/resources/auth/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/resources/auth/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/resources/auth/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="/resources/auth/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/resources/auth/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/resources/auth/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="/resources/auth/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/resources/auth/css/util.css">
	<link rel="stylesheet" type="text/css" href="/resources/auth/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100" style="background-image: url('/resources/auth/images/bg-01.jpg');">
			<div class="wrap-login100 p-t-30 p-b-50">
				<span class="login100-form-title p-b-41">
					Login Admin Book Management
				</span>
				<form class="login100-form validate-form p-b-33 p-t-5" action="javascript:void(0)">
					<p style="color: red; margin-left: 54px;margin-top: 12px;font-weight: bold;" id="loginFail"></p>
					<div class="wrap-input100 validate-input" data-validate = "Enter email">
						<input class="input100" type="text" name="username" placeholder="Email" id="email">
						<span class="focus-input100" data-placeholder="&#xe82a;"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Enter password">
						<input class="input100" type="password" name="pass" placeholder="Password" id="pwd">
						<span class="focus-input100" data-placeholder="&#xe80f;"></span>
					</div>

					<div class="container-login100-form-btn m-t-32">
						<button class="login100-form-btn" onclick="return handleLogin()">
							Login
						</button>
					</div>

				</form>
			</div>
		</div>
	</div>

	<div id="dropDownSelect1"></div>
	<div id="wait" style="display:none;width:106px;height:106px;position:absolute;top:50%;left:50%;padding:2px;z-index:2"><img src='/resources/templates/demo_wait.gif' width="100" height="100" /></div>
	
<!--===============================================================================================-->
	<script src="/resources/auth/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="/resources/auth/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="/resources/auth/vendor/bootstrap/js/popper.js"></script>
	<script src="/resources/auth/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="/resources/auth/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="/resources/auth/vendor/daterangepicker/moment.min.js"></script>
	<script src="/resources/auth/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="/resources/auth/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="/resources/auth/js/main.js"></script>
	<script src="/resources/templates/admin/js/login.js"></script>
</body>
</html>