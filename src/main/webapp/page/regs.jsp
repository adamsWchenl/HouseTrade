<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 用户注册</TITLE>
  <link rel="stylesheet" type="text/css" href="../admin/easyUI/themes/default/easyui.css" />
  <link rel="stylesheet" type="text/css" href="../admin/easyUI/themes/icon.css" />
  <script type="text/javascript" src="../admin/js/jquery-1.8.3.js"></script>
  <script type="text/javascript" src="../admin/js/jquery.easyui.min.js"></script>

  <script type="text/javascript">
    $(function () {
      $("#resName").blur(function () {
        if ($("#resName").val()=="") {
          $("#mesOne").html("");
        }else {
        $.post("/page/name",{"name":$("#resName").val()},
                function (date) {
          if (date.result==0) {
            $("#mesOne").html("用户名已被使用").css("color","red");
          }else {
            $("#mesOne").html("用户名可用").css("color","green");
          }
        },"json")
      }
      })

      /*
      判断密码是否一致
      * */
      $("#repassword").blur(function () {
        var repass=$("#repassword").val();
        var pass=$("#password").val();
        if (repass!=pass) {
          $("#mesTwo").html("密码不一致").css("color","red");
        }
        if (repass=="") {
          $("#mesTwo").html("");
        }
      })

      $("#newUser").click(function () {
        $.post("/page/new",
                {"name":$("#resName").val(),"password":$("#password").val(),"telephone":$("#telephone").val()},
                function(data){
                  if (data.result>0) {
                    alert("添加成功")
                  }
                },"json")
      })

    });

  </script>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新用户注册</DT>
  <DD class=past>填写个人信息</DD></DL>
<DIV class=box>
  <form id="saveUserForm" method="post">
    <table>
  <TBODY>
  <TR>
    <TD class=field>用 户 名：</TD>
    <TD><INPUT class=text type=text name=name id="resName"> <span id="mesOne"></span></TD></TR>
  <TR>
    <TD class=field>密　　码：</TD>
    <TD><INPUT class=text type=password name=password id="password"></TD></TR>
  <TR>
    <TD class=field>确认密码：</TD>
    <TD><INPUT class=text type=password name=repassword id="repassword"><span id="mesTwo"></span> </TD></TR>
  <TR>
    <TD class=field>电　　话：</TD>
    <TD><INPUT class=text type=text name=telephone id="telephone"> </TD></TR>
<DIV class=buttons>
<INPUT value=立即注册 type=submit id="newUser">
</DIV>
    </table>
      </form>

</DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
