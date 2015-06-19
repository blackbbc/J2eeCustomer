<%--
  Created by IntelliJ IDEA.
  User: sweet
  Date: 15-6-19
  Time: 下午1:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>找回密码</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">

  <style>
    input,button{
      display:block;
      margin:6px auto;
      width:180px;
    }
    input{
      border:1px solid black;
      padding:12px;
    }
    button{
      background:none;
      border:1px solid black;
      padding:12px;
    }
  </style>
</head>
<body>

<div id="react">
  <div class="table">
    <h1>找回密码</h1>
    <input type="password" id="newPassword" placeholder="新密码" value=""/>
    <input type="password" id="oldPassword" placeholder="确认密码" value=""/>
    <button id="submit">确定</button>
    <p id="info"></p>
  </div>
</div>

<script>
  var info=document.getElementById('info');
  var button=document.getElementById('submit');
  button.onclick = function(){
    if(button.innerHTML!=='提交中...'){
      var oldp = document.getElementById('oldPassword').value;
      var newp = document.getElementById('newPassword').value;
      if(oldp!==newp){
        info.innerHTML='两次密码不相同，请检查';
        return;
      }
      button.innerHTML='提交中...';
      var xmlHttp = new XMLHttpRequest();
      xmlHttp.open("POST",'/Home/ForgetPW.json',true);
      xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
      xmlHttp.send('newWord='+newp);
      xmlHttp.onreadystatechange=function(){
        if(xmlHttp.readyState==4&&xmlHttp.status==200){
          var data=JSON.parse(xmlHttp.responseText);
          if(data.Code===0){
            info.innerHTML='修改密码成功，正在跳转至首页';
            window.location.href='http://localhost:3000';
          }
          else{
            info.innerHTML=data.Msg;
          }
        }
        else{
          info.innerHTML='网络错误';
        }
        button.innerHTML='确定';
      }
    }
  }
</script>

</body>
</html>
