<%@page import="model.NoiDungThongBao"%>
<%@page import="model.LoaiThongBao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style009.css" />
</head>

<body>
	<%
	ArrayList<NoiDungThongBao> dsNDTB = (ArrayList<NoiDungThongBao>) session.getAttribute("result");
	%>
	<div class="studentManageContent"
		style="width: -webkit-fill-available; height: 100%; padding: 0; margin: 0">
		<div class="studentList">
			<table class="table">
				<thead
					style="background-color: rgb(238, 70, 70); color: white; font-size: 20px; border-color: black;">
					<tr>
						<th style="width: 9%">MãTB</th>
						<th style="width: 27%">Tiêu đề</th>
						<th style="width: 50%">Nội dung</th>
						<th style="width: 15%">Ngày TB</th>
						<th style="width: 10%">Loại</th>
						<th style="width: 12%"></th>
						<th style="width: 12%"></th>
					</tr>
				</thead>
				<tbody>
					<%
					if (dsNDTB != null || dsNDTB.isEmpty()) {
						for (NoiDungThongBao ndtb : dsNDTB) {
					%>
					<tr>
						<td><%=ndtb.getMaNoiDungThongBao()%></td>
						<td><%=ndtb.getTieuDe()%></td>
						<td><%=ndtb.getNoiDung()%></td>
						<td><%=ndtb.getThoiGianThongBao()%></td>
						<td><%=ndtb.getLtb().getMaLoaiThongBao()%></td>
						<td><a id="redirectToUpdate" class="btn btn-update"
							data-manotice="<%=ndtb.getMaNoiDungThongBao()%>">Sửa</a></td>
						<td><a class="btn btn-danger" data-manotice="<%=ndtb.getMaNoiDungThongBao()%>">Xóa</a></td>
					</tr>
					<%
					}
					} else {
					%>
					<p>Không tìm thấy dữ liệu</p>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			var a;
	        const updateButtons = document.querySelectorAll('.btn-update');
	        const delButtons = document.querySelectorAll('.btn-danger');
	        updateButtons.forEach(updateButton => {
	            updateButton.addEventListener('click', function() {
	                	a = this.getAttribute('data-manotice');
	                    window.top.location.href = 'announceManage?action=layThongTinSua&maThongBao='+a;
	            });
	        });
	        delButtons.forEach(delButton => {
	            delButton.addEventListener('click', function() {
	                	a = this.getAttribute('data-manotice');
	                    window.top.location.href = 'announceManage?action=xoaThongBao&maThongBao='+a;
	            });
	        });
	    });
    </script>
</body>
</html>