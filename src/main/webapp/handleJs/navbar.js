const avatar = document.querySelector(".avatar");
	const subMenu = document.querySelector(".sub-menu");

	avatar.addEventListener("click", function(event) {
		if (subMenu.classList.contains("active")) {
			subMenu.classList.remove("active");
		} else {
			subMenu.classList.add("active");
		}
		event.stopPropagation();
	});

	document.addEventListener("click", function(event) {
		if (!avatar.contains(event.target)) {
			subMenu.classList.remove("active");
		}
	});