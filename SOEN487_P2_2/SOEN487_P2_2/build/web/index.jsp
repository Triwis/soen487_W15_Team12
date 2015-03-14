<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Group 12">

    <title>Today's Temperature | SOEN487 Group Twelve</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <div class="container">
        <div id="assign-header">
            <h2>Today's Temperature is</h2>
            <h1 id="todays-temperature"></h1>
            <p class="lead">doesn't seem right?<br><small><em>No worries, simply change it below!</em></small></p>
        </div>
        <div class="text-center temperature-form-wrapper">
            <form class="form-inline" id="temperature-form">
              <div class="form-group">
                <input type="text" class="form-control" id="temperature" name="temperature" placeholder="Enter new temperature">
              </div>
              <button type="submit" class="btn btn-default" id="btn-update-temperature">Update</button>
            </form>
            <div id="alerts"></div>
        </div>
    </div><!-- /.container -->
  <div class="footer">
      &copy; SOEN487 Group Twelve<br>
      Background image: http://loopele.com/live-weather-wallpaper-for-pc
  </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  </body>
</html>
