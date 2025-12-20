const footerHTML = `
<!-- Footer -->
<footer class="site-footer">
    <div class="guac-container">
        <div class="footer-inner">
            <span class="footer-brand">WebBuddy</span>
            <span class="footer-divider">•</span>
            <span>© 2025</span>
            <span class="footer-divider">•</span>
            <span>All rights reserved</span>
            <span class="footer-divider">•</span>
            <a href="#" class="footer-link">Privacy</a>
            <span class="footer-divider">•</span>
            <a href="#" class="footer-link">Terms</a>
            <span class="footer-divider">•</span>
            <div class="dropdown footer-lang">
                <button class="dropdown-toggle">EN</button>
                <ul class="dropdown-menu">
                    <li><a href="#">DK</a></li>
                    <li><a href="#">DE</a></li>
                    <li><a href="#">FR</a></li>
                    <li><a href="#">ES</a></li>
                </ul>
            </div>
        </div>
    </div>
</footer>
<!-- Footer -->
`;

export function loadFooter(containerId = "footer-component") {

    const container = document.getElementById(containerId);

    if (!container) {
        console.error(`Footer container #${containerId} not found`);
        return;
    }

    container.innerHTML = footerHTML;
}

loadFooter();