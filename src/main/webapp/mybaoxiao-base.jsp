<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>附件管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		function queryList() {
			var pageN = $("#pagenum").val();
			var reg = /^[1-9]\d*$/;
			if (!reg.test(pageN)){
                alert("请输入正确页数");
                return;
			}else{
			    window.location.href="${pageContext.request.contextPath}/bx/list?pageNum="+pageN+"${queryStr}";
			}
        }
	</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>
<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:个人报销管理>>报销列表
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/mybaoxiao-add.jsp';" value='添加报销' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>
<form name='form3' action='${pageContext.request.contextPath}/bx/list' method='get'>
	<input type='hidden' name='dopost' value='' />
	<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
		<tr bgcolor='#EEF4EA'>
			<td background='skin/images/wbg.gif' align='center'>
				<table border='0' cellpadding='0' cellspacing='0'>
					<tr>
						<td width='90' align='center'>搜索条件：</td>
						<td width='160'>
							<select name='search_status' style='width:150'>
								<option value="">选择类型...</option>
								<option value='0'>未审批</option>
								<option value='2'>已审批</option>
								<option value='1'>驳回</option>
								<option value='3'>驳回</option>
							</select>
						</td>
						<td width='70'>
							关键字：
						</td>
						<td width='160'>
							<input type='text' name='search_like_keyword' value='' style='width:120px' />
						</td>
						<td width='110'>
							<select name='orderby' style='width:120px'>
								<option value='0'>...</option>
								<option value='1'>立项时间</option>
								<option value='2'>计划完成时间</option>

							</select>
						</td>
						<td>
							&nbsp;&nbsp;&nbsp;<input name="imageField" type="image" src="${pageContext.request.contextPath}/skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>


<!--  内容列表   -->
<form name="form2">

<table width="98%"  cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;附件列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="20%">编号</td>
	<td width="6%">总金额</td>
	<td width="10%">使用时间</td>
	<td width="40%">备注信息</td>
	<td width="10%">审批状态</td>
	<td width="10%">操作</td>
</tr>

<c:forEach items="${page.list}" var="bs" varStatus="index">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${bs.bxid}" class="np"></td>
	<td>${index.count}</td>
	<td>${bs.totalmoney}</td>
	<td align="center">
		<a href=''><u>
			<fmt:formatDate value="${bs.bxtime}" pattern="yyyy-MM-dd"></fmt:formatDate>
		</u></a></td>
	<td>${bs.bxremark}</td>

	<c:if test="${bs.bxstatus == 0}">
		<td>未审批</td>
		<td><a href="mybaoxiao-edit.jsp?bxid=${bs.bxid}">编辑</a> </td>
	</c:if>
	<c:if test="${bs.bxstatus == 1}">
		<td>驳回</td>
		<td><a href="javascript:void(0)" style="pointer-events:none;color: grey" >编辑</a> </td>
	</c:if>
	<c:if test="${bs.bxstatus == 2}">
		<td>已审批</td>
		<td><a href="mybaoxiao-edit.jsp?bxid=${bs.bxid}">编辑</a> </td>
	</c:if>
	<c:if test="${bs.bxstatus == 3}">
		<td>已付款</td>
		<td><a href="javascript:void(0)" style="pointer-events:none;color: grey" >编辑</a> </td>
	</c:if>

</tr>
</c:forEach>


<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 -->
		<div>
			<a href="${pageContext.request.contextPath}/bx/list?pageNum=1${queryStr}">首页</a>
			<a href="${pageContext.request.contextPath}/bx/list?pageNum=${page.pageNum-1}${queryStr}">上一页</a>
			<c:forEach items="${page.navigatepageNums}" var="pageNum">
				<c:if test="${pageNum == page.pageNum}">
					<span style="color: #1c1c1c;font-weight: bold">${pageNum}</span>
				</c:if>
				<c:if test="${pageNum != page.pageNum}">
					<a href="${pageContext.request.contextPath}/bx/list?pageNum=${pageNum}${queryStr}">${pageNum}</a>
				</c:if>

			</c:forEach>
			<a href="${pageContext.request.contextPath}/bx/list?pageNum=${page.pageNum+1}${queryStr}">下一页</a>
			<a href="${pageContext.request.contextPath}/bx/list?pageNum=${page.pages}${queryStr}">末页</a>
			&nbsp;&nbsp; 跳转到<input size="1px" id="pagenum" name="pagenum" onblur="queryList()">页
		</div>
	</td>
</tr>
</table>

</form>
  

</body>
</html>