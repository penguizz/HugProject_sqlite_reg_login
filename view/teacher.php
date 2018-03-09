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
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <p class="navbar-brand" href="#">HugProject</p>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="teacher.php">รายชื่อคุณครู</a></li>
        <li><a href="student.php">รายชื่อนักเรียน</a></li>
        <li><a href="room.php">จัดห้องเรียน</a></li>
         <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">MORE
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">ข้อมูลส่วนตัว</a></li>
            <li><a href="login.php">Logout</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>


<div class="panel panel-default">
  <div class="panel-heading">ค้นหา</div>
    <div class="panel-body">
        <form>
          <div class="form-group">
            <label for="usr">Type</label>
            <select class="form-control">
                <option value="0">กรุณาเลือก</option>
                <option value="customer">Customer</option>
                <option value="supplier">Supplier</option>
            </select>
          </div>
          <div class="form-group">
            <label for="usr">Invoice No</label>
                <div class="" style="margin-top: 5px">
                  <select class="js-example-basic-multiple" style="width: 100%;height: 40%;" >
                      <option value="">กรุณาเลือก</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                  </select>
                </div>
          </div>
          <div class="form-group">
            <label for="usr">Purchase Order No</label>
                <div class="" style="margin-top: 5px">
                  <select class="js-example-basic-multiple" style="width: 100%;height: 40%;" >
                      <option value="">กรุณาเลือก</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                  </select>
                </div>
          </div>
          <div class="form-group">
            <label for="usr">Supplier Name</label>
                <div class="" style="margin-top: 5px">
                  <select class="js-example-basic-multiple" style="width: 100%;height: 40%;" >
                      <option value="">กรุณาเลือก</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                      <option value="bL">Alabama</option>
                  </select>
                </div>
          </div>
            <label for="pwd">Date</label>
            <div class="datepic">
              <div class='input-group date' id='datetimepicker1'>
                <input type='text' name="daterange" class="form-control" />
                <!-- <input type='text' class="form-control"/> -->
                 <span class="input-group-addon">
                     <span class="glyphicon glyphicon-calendar"></span>
                 </span>
               </div>
            </div>
            <br>
            <div class="pull-right">
               <button type="button" class="btn">Search</button>
               <button type="button" class="btn btn-danger">Clear</button>
            </div>
        </form>
    </div>        
</div>
<div class="" style="margin-bottom: 15px">
    <a href="/invoice/create"><button type="button" class="btn btn-primary" style="width: 310px"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> เพิ่มรายการ</button></a>
</div>

</body>
</html>
