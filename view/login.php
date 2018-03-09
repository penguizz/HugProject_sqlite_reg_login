<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Theme Made By www.w3schools.com - No Copyright -->
  <title>Bootstrap Theme Simply Me</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Kanit" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
  body {
      font-family: 'Kanit', sans-serif;
      line-height: 1.8;
      color: #f5f6f7;
  }
  a{font-size: 16px;}
  .navbar-brand{
    font-size: 24px;
  }
  .margin {margin-bottom: 45px;}
  .bg-1 { 
      background-color: #1abc9c; /* Green */
      color: #ffffff;
  }
  .bg-2 { 
      background-color: #474e5d; /* Dark Blue */
      color: #ffffff;
  }
  .bg-3 { 
      background-color: #ffffff; /* White */
      color: #555555;
  }
  .bg-4 { 
      background-color: #2f2f2f; /* Black Gray */
      color: #fff;
  }
  .container-fluid {
      padding-top: 70px;
      padding-bottom: 70px;
  }
  .navbar {
      border: 0;
      border-radius: 0;
      margin-bottom: 0;
      font-size: 12px;
      ;
  }
  .form-signin{
      width: 50%;
  }

  .header{
    margin-top: 80px;
  }
  .navbar-nav  li a:hover {
      color: #1abc9c !important;
  }
  </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <p class="navbar-brand" href="#">HugProject</p>
    </div>
  </div>
</nav>
<div class="header col-md-12">
        <div class="container" class="form-group">   
            <div class="login-header">
                <h1 class="text-center">LOGIN</h1>
                <p class="text-center"> Purchase tracking system</p>   
            </div>   
        </div>
        <br>
        <div class="col-md-offset-4">
            <form class="form-signin" >
                <label for="inputEmail" class="sr-only">E-mail address
                </label>
                <input type="email" id="inputEmail" class="form-control" placeholder="E-mail address"> <!-- required autofocus -->
                <br>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="Password"> <!-- required -->
                <div class="checkbox">
                <label>
                    <input class="Purchase-tracking" type="checkbox" value="remember-me">Remember me
                </label>
                </div>
                <div class="">
                    <a class="btn btn-lg btn-primary btn-block" type="submit" href="/">Login</a>

                </div>
            </form>
        </div>
    </div>
</body>
</html>
