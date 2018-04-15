<#assign contextPath = request.getContextPath()>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Shoppy Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <title>登录</title>
    <link rel="stylesheet" href="${contextPath}/statics/css/bootstrap.css"/>
    <link rel="stylesheet" href="${contextPath}/statics/css/style.css"/>
    <script type="text/javascript" src="${contextPath}/statics/js/jquery.js"></script>
    <script type="text/javascript" src="${contextPath}/statics/js/bootstrap.js"></script>
</head>
<body>
<div class="login-page">
    <div class="login-main">
        <div class="login-head">
            <h1>登录测试平台</h1>
        </div>
        <div class="login-block">
            <form id="form" class="demoform">
                <input type="text" name="email" placeholder="Account" id="userName" datatype="s5-16" errormsg="请输入账号">
                <input type="password" name="password" class="lock" id="password" placeholder="Password" datatype="*6-15" errormsg="请输入密码">
                <#--<div class="forgot-top-grids">
                    <div class="forgot-grid">
                        <ul>
                            <li>
                                <input type="checkbox" id="brand1" value="">
                                <label for="brand1"><span></span>Remember me</label>
                            </li>
                        </ul>
                    </div>
                    <div class="forgot">
                        <a href="#">Forgot password?</a>
                    </div>
                    <div class="clearfix"></div>
                </div>-->
                <input type="button" id="btnSubmit" value="Login">
                <#--<h3>Not a member?<a href="signup.html"> Sign up now</a></h3>
                <h2>or login with</h2>-->
                <div class="login-icons">
                    <ul>
                        <li><a href="#" class="facebook"><i class="fa fa-facebook"></i></a></li>
                        <li><a href="#" class="twitter"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="#" class="google"><i class="fa fa-google-plus"></i></a></li>
                    </ul>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="copyrights">
    <p>Copyright &copy; 2018.所有权利归陈贺所有
</div>
</body>
<script type="text/javascript">
    $('#btnSubmit').click(function (event) {
        var account = $('#userName');
        var password = $('#password');

        if (!account.val() || !password.val()){
            return;
        }

        $.ajax({
            url:'${contextPath}/doLogin',
            type:'post',
            data:{
                account:account.val(),
                password:password.val()
            },
            success:function (data) {
                if (data.success){
                    window.location.href = "${contextPath}"+data.data;
                }
            }

        })
    });
</script>
</html>
