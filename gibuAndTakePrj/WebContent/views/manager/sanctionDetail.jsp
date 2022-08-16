<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #header {
       	background-color: #8bdcb1;
        width: 1905px;
        text-align: left;
        font-weight: bold;
        font-size: 36px;
        padding: 30px 0px 30px 380px;
    }
    
    .a{
    	margin-left: 560px;
    }
    
    #up{
    	margin-top: 20px;
    }
    
    
    #list1{
			border: 2px solid #acdac2;
			border-radius: 10px;    		
            width: 800px;
            height: 500px;
            color: black;
            margin-top: 30px;
            vertical-align : top;
    }

    #list2{
    		border: 2px solid #acdac2;
			border-radius: 10px;
            width: 800px;
            height: 500px;
            color: black;                      
            vertical-align : top;
    }
    
    #list{
    	font-weight: bold;
    	margin-left: 560px;
    	margin-top: 20px;
    }

   #btn1{
    	background-color: #b3e0c9;
    	border: 2px solid #acdac2;
        margin: 0px 50px 0px 860px;
        margin-top: 30px;
        padding: 10px;
        font-weight: bold;
        border-radius: 15px;
        padding: 5px 20px 5px 20px;
    }
    
 	#btn2{
        background-color: #b3e0c9;
    	border: 2px solid #acdac2;
        margin: 0px 50px 0px 0px;
        margin-top: 30px;
        padding: 10px;
        font-weight: bold;
        border-radius: 15px;
		padding: 5px 20px 5px 20px;
    }

    #btn1:hover{
    	background-color: #93e1b7;
        cursor: pointer;
    }
    
    #btn2:hover{
    	background-color: #93e1b7;
        cursor: pointer;
    }
    
    .float{
    	float: left;
    }
    
    #last {    
        padding: 80px;
    }

</style>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>
    <div id="header">[공지] 사용자 제제 안내</div>

    <form action="/gibuAndTakePrj/manager/sanction" method="get">
	    <table id="up" class="a">
	    	<tr>
	    	<td id="list1">안내내용</td>
	    	</tr>
	    	<tr>
	    </table>
		    <div id="list">[제재된 사용자 목록]</div>
		<table id="down" class="a">
			<tr>
			    <td id="list2">제재 사용자 이름</td>
	    	</tr>
		</table>    
		    <div>
	            <input id="btn1" class="float" type="submit" value="목록">
	        </div>
	    
    </form>
    
    <form action="/gibuAndTakePrj/manager/modify" method="get">
            <input id="btn2" class="float" type="submit" value="수정">
    </form>
    	
    
    <div id="last"></div>

    <%@include file="/views/common/footer.jsp" %>
</body>
</html>