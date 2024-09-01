<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal1">
	<div class="confirmDelete">
		<form name="delete-form" action="creditCourseManage" method="POST">
			<input type="hidden" name="action" value="deleteCreditCourse" />
			<input type="hidden" name="Id" value="" />
			<div class="desc">Thông báo</div>
			<div class="close2">
				<span class="icon-close"> <i class='bx bx-x'></i>
				</span>
			</div>
			<p>Xác nhận xóa đi lớp tín chỉ ?</p>
			<div class="action">
				<button type="submit" id="btn-delete-user" class="btn">
					Đúng</button>
				<a class="btn denied"> Hủy </a>
			</div>
		</form>
	</div>
</div>