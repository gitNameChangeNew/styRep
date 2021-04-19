<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>index</title>

    <script type="text/javascript" src="static/js/jquery-2.2.3.min.js" ></script>
    <script type="text/javascript" src="https://cdn.bootcdn.net/ajax/libs/jquery.form/4.3.0/jquery.form.min.js"></script>

    <script>

        $(function(){
            $('#btn').click(function(){
                $.ajax({
                    url:"/ajaxEntiy",
                    type:"POST",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"username":"毛蛋","id":"2","birthday":"2021-01-30"}',
                    //预期服务器返回的数据类型
                    dataType:"json",
                    success:function(data){
                       alert(JSON.stringify(data));
                    }
		        });
            });
        });


        //上传图片
        function uploadPic(){
            //定义参数
            var options = {
                url:"/fileUpload4",
                type:"post",
                dataType:"json",
                success:function(res){
                    $('#success_img').attr('src', res.fullPath); //真实完整路径
                    $('#img_path').val(res.filename);    //相对路径
                }
            };
            //jquery.form使用方式
            $("#jvForm").ajaxSubmit(options);
        }

    </script>
</head>
<h2>Hello World!</h2>

<form action="/toConver">
    <p>姓名： <input type="text" name="username"/></p>
    <p>生日： <input type="text" name="birthday"/></p>
    <input type="submit">
</form>


<input type="button" id="btn" value="发送ajax请求"/>


<h1>传统方式文件上传</h1>

<form action="/fileUpload" method="post" enctype="multipart/form-data">
    <p>文件 <input type="file" name="选择上传文件"/></p>
    <input type="submit" value="上传">
</form>


<h1>springmvc方式文件上传</h1>

<form action="/fileUpload2" method="post" enctype="multipart/form-data">
    <p>文件 <input type="file" name="upload"/></p>
    <input type="submit" value="上传">
</form>




<h1>跨服务器方式文件上传</h1>

<form action="/fileUpload3" method="post"  enctype="multipart/form-data" >
    <p>文件 <input type="file" name="upload" multiple="multiple" /></p>
    <input type="submit" value="上传">
</form>



<h1>跨服务器方式文件上传2</h1>

<img height="100" id="success_img"/>
<form id="jvForm" action="" method="post" enctype="multipart/form-data">
    <!-- 保存图片的相对路径，方便提交给后台，存到数据库 -->
    <input type="hidden" name="" id="img_path"/>
    <input type="file" id="select_file" onchange="uploadPic()" name="upload"/>
</form>


<h1>exception</h1>
<a href="/testException/testException">测试异常处理</a>


<h1>interceptor</h1>
<a href="/testException/testInterceptor">拦截器测试</a>

</body>
</html>
