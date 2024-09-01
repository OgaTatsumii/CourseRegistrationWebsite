

const deleteButtons = document.querySelectorAll('.btn-danger');
const modal1 = document.querySelector(".modal1");
const confirmDeleteModal = document.querySelector('.confirmDelete');
const cancelButton = document.querySelector('.denied');
const iconClose2 = document.querySelector(".close2");
const form = document.forms['delete-form'];


deleteButtons.forEach(button => {
	button.addEventListener('click', function() {
		modal1.classList.add("active");
		confirmDeleteModal.classList.add('active');
		const ID = this.getAttribute("data-Id");
		console.log(ID);
		form.elements['Id'].value = ID;
	
});


cancelButton.addEventListener('click', function() {
	confirmDeleteModal.classList.remove('active');
	modal1.classList.remove("active");
});

iconClose2.addEventListener("click", () => {
	confirmDeleteModal.classList.remove('active');
	modal1.classList.remove("active");
});


});










