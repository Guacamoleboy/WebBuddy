document.addEventListener("click", e => {
    const toggle = e.target.closest(".dropdown-toggle");
    const openMenus = document.querySelectorAll(".dropdown.open");

    openMenus.forEach(menu => {
        if (!menu.contains(e.target)) {
            menu.classList.remove("open");
        }
    });

    if (toggle) {
        toggle.parentElement.classList.toggle("open");
    }
});

document.querySelectorAll('.footer-lang .dropdown-toggle').forEach(btn => {
    btn.addEventListener('click', () => {
        const parent = btn.parentElement;
        parent.classList.toggle('open');
    });
});

document.addEventListener('click', (e) => {
    document.querySelectorAll('.footer-lang').forEach(drop => {
        if (!drop.contains(e.target)) {
            drop.classList.remove('open');
        }
    });
});