<%@page import="com.gnt.stmt.vo.ExeVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gnt.stmt.vo.StmtVo"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	StmtVo donaVo = (StmtVo)request.getAttribute("donaVo");
	ArrayList<ExeVo> exeVo = (ArrayList<ExeVo>)request.getAttribute("exeVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<title>기업 명세서 수정</title>
<style>

	#nav{
		display: flex;
	}

    #body {
        width: 1200px;
		height: 1000px;
        margin: 0 auto;
    }

    #category{
		width: 100%;
		height: 100px;
		background-color: #8bdcb1;
		text-align: left;
		font-size: 25px;
		font-weight: 700;
		line-height: 100px;
		margin: 0 0 0 0;
        margin-top: 20px;
	}
	
	#category a {
		margin: 0 0 0 20px;
	}

	form {
		width: 1200px;
	}

    table {
		width: 700px;
		color: #15462b;
		border-top: 3px solid #8bdcb1;
		border-bottom: 3px solid #8bdcb1;
		border-collapse: collapse;
		margin: auto;
		margin-bottom: 5px;
	}

	th {
		text-align: left;
		width: 20%;
	}

	th, td {
		border-bottom: 1px solid black;
		padding: 5px;
		padding-left: 20px;
	}

	#first th, #second th {
		border-right: 1px solid black;
		width: 20%;
	}

	#second tbody td:last-child {
		text-align: right;
		padding-right: 10px;
	}

    #outer {
		width: 700px;
		display: flex;
		margin: 0 auto;
		margin-top: 10px;
		flex-wrap: wrap;
		box-sizing: border-box;
		justify-content: space-between;
	}

	#title {
		font-size: 30px;
		display: inline;
		width: 550px;
		font-weight: bold;
		padding: 5px;
	}

	#do_t {
		margin-left: 10px;
		font-size: 20px;
	}

    #re-outer {
        width: 700px;
        margin: 0 auto;
		margin-top: 20px;
		margin-bottom: 20px;
        display: flex;
    }

	#re-outer input {
		background-color: #8bdcb1;
		border: #8bdcb1;
		border-radius: 30px;
		color: #2e6c4a;
		font-weight: bolder;
		font-size: 20px;
		width: 130px;
		height: 40px;
		box-sizing: border-box;
        margin: 0 auto;
	}

    #col-outer {
        display: flex;
        margin: auto;
		margin-top: 20px;
		padding-right: 20px;
        width: 700px;
        justify-content: right;
    }

    #col-p, #col-m {
        background-color: #8bdcb1;
        color: #2e6c4a;
        font-size: 20px;
        font-weight: bold;
        border-radius: 100%;
        width: 25px;
        height: 25px;
        margin: 5px;
        text-align: center;
        line-height: 25px;
		cursor: pointer;
    }

    #second tbody td {
        color: lightgray;
    }

	.total {
		color: #2e6c4a;
		font-weight: bold;
	}

	.input-col {
		display: flex;
		justify-content: space-between;
		border: none;
	}

	a {
        cursor: pointer;
    }

	input[type=submit]{
		cursor: pointer;
	}

</style>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>

	<div id="body">
		<div id="category">
			<a>기업 명세서 수정</a>
		</div>
		<div id="nav">
			<div id="naviIn"><%@ include file="/views/mypageNav/corpNavi.jsp" %></div>

			<form action="/gibuAndTakePrj/corp/stmtList" method="post">
		
				<table id="first">
					<div id="outer">
						<div id="title"><%=donaVo.getRegName() %></div>
						<div id="do_t">기부금 집행 내역 추가하기</div>
					</div>
					<tr>
						<th>집행기간</th>
						<td colspan="2">
							<fmt:parseDate var="parsedSdate" value="<%=donaVo.getDonaSdate() %>" pattern="yyyy-MM-dd HH:mm:ss"/>
                        	<fmt:formatDate value="${parsedSdate}" pattern="yyyy-MM-dd" />
                        	~
                        	<fmt:parseDate var="parsedEdate" value="<%=donaVo.getDonaEdate() %>" pattern="yyyy-MM-dd HH:mm:ss"/>
                        	<fmt:formatDate value="${parsedEdate}" pattern="yyyy-MM-dd" />
						</td>
					</tr>
					<tr>
						<th>총 모금 금액</th>
						<td colspan="2"><span class="total"><fmt:formatNumber value="<%=donaVo.getDonaPmoney() %>" pattern="#,###"/></span>원</span>(목표금액 3,000,000원)</td>
					</tr>
					<tr>
						<th>사업 대상</th>
						<td colspan="2"><%=donaVo.getDonaPerson() %></td>
					</tr>
				</table>
				<table id="second">
					<div id="col-outer">
						<div id="col-p" onclick='javascript:addItem();'>+</div>
						<div id="col-m" onclick="delRow">-</div>
					</div>
					<thead>
						<tr>
							<th>총 집행 금액</th>
							<td colspan="2"><span class="total">3,000,000원</span>(목표금액 3,000,000원)</td>
						</tr>
					</thead>
					<tbody>
						<% for (int i = 0; i < exeVo.size(); ++i) {%>
							<tr>
								<th>사업비</th>
								<td colspan="2" class="input-col" id="input-cnt">
									<input type="text" value="<%=exeVo.get(i).getExeCnt() %>" size="40" readonly="readonly">
									<input type="number" value="<%=exeVo.get(i).getExeMoney() %>" readonly="readonly">
								</td>
							</tr>
						<%} %>
				</table>
				<div id="re-outer">
					<input type="submit" value="완료">
				</div>
			</form>
			<div id="add-col" style="display: none">
				<ttr>
					<tth>사업비</tth>
					<ttd colspan="2" class="input-col" id="input-cnt">
						<input type="text" name="exeCnt" size="40" placeholder=" 사용 내역을 등록해주세요.">
						<input type="number" name="exeMoney" placeholder=" 금액을 등록해주세요.">
					</ttd>
				</ttr>
			</div>

		</div>
	</div>

	<%@include file="/views/common/footer.jsp" %>
	
	<!-- 로우 추가는 되는데 이상하게 됨. 수정. -->
	<script type="text/javascript">
	$(function replaceAll(str, ostr, rstr)
		{
			if(str == undefined) return str;
			return str.split(ostr).join(rstr);
	})
	
	$(function addCol() {
		let append_tr = $("add-col").html();
		append_tr = replaceAll(append_tr, 'tth', 'th');
		append_tr = replaceAll(append_tr, 'ttr', 'tr');
		append_tr = replaceAll(append_tr, 'ttd', 'td');
		$("#second tbody").append(apeend_tr);
	})
	
//		$(function () {
//			$('#col-p').click(function () {
//				console.log("클릭됨");
//				const copyTh = $('#input-title').clone(true);
//				const copyTd = $('#input-cnt').clone(true);
//				$('#copy').append(copyTh, copyTd);
			})
		})
	</script>

</body>
</html>