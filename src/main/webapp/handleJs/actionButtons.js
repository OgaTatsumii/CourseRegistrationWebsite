document.addEventListener("DOMContentLoaded", function() {
	const selectAllCheckbox = document.getElementById("selectAll");
	const checkboxes = document.querySelectorAll(".studentCheckbox");
	const actionSelect = document.querySelector(".container_action select");
	const actionButton = document.getElementById("actionButton");
	const modal = document.querySelector(".modal3");
	const confirmDeleteManyModal = document.querySelector('.confirmDeleteMany');
	const cancelButton = document.querySelector('.confirmDeleteMany .denied');
	const iconClose2 = document.querySelector(".confirmDeleteMany .icon-close");

	selectAllCheckbox.addEventListener("change", function() {
		checkboxes.forEach(checkbox => {
			checkbox.checked = selectAllCheckbox.checked;
			updateForm(checkbox);
		});
		toggleActionButton();
	});
	
	
	actionSelect.addEventListener("change", toggleActionButton);

	checkboxes.forEach(checkbox => {
		checkbox.addEventListener("change", function() {
			updateForm(this);
			toggleActionButton();
		});
	});

	
	
	actionSelect.addEventListener("change", function() {
		var selectedValue = this.value;
		var deleteForm = document.querySelector('.confirmDeleteMany form');
		if (selectedValue !== "-- Hành động --"){
			deleteForm.action = selectedValue;
		}
		
	});

	actionButton.addEventListener("click", function() {
		modal.classList.add("active");
		confirmDeleteManyModal.classList.add('active');
	});

	cancelButton.addEventListener('click', function() {
		confirmDeleteManyModal.classList.remove('active');
		modal.classList.remove("active");
	});

	iconClose2.addEventListener("click", () => {
		confirmDeleteManyModal.classList.remove('active');
		modal.classList.remove("active");
	});


	function toggleActionButton() {
		const isAnyCheckboxChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
		const isActionSelected = actionSelect.value !== "-- Hành động --";
		if (isAnyCheckboxChecked && isActionSelected) {
			actionButton.disabled = false;
			actionButton.classList.add("button_active");
		} else {
			actionButton.disabled = true;
			actionButton.classList.remove("button_active");
		}
	}

	function updateForm(checkbox) {
		const maSinhVien = checkbox.value; // Lấy mã sinh viên từ ô cạnh checkbox
		const form = document.querySelector(".confirmDeleteMany form[name='delete-form']");
		const hiddenInput = form.querySelector(`input[value="${maSinhVien}"]`); // Tìm input tương ứng trong form

		if (checkbox.checked && !hiddenInput) {
			// Nếu checkbox được chọn và input không tồn tại trong form, thêm input ẩn vào form
			const newHiddenInput = document.createElement("input");
			newHiddenInput.type = "hidden";
			newHiddenInput.name = "submit_Ids";
			newHiddenInput.value = maSinhVien;
			form.appendChild(newHiddenInput);
		} else if (!checkbox.checked && hiddenInput) {
			// Nếu checkbox không được chọn và input tồn tại trong form, xóa input khỏi form
			hiddenInput.remove();
		}
	}


	
});
