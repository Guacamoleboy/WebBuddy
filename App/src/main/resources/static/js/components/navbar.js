const navbarHTML = `
<!-- Navbar -->
<nav class="navbar">
    <div class="guac-container guac-d-flex guac-align-center guac-justify-between">

        <!-- Brand -->
        <a href="/" class="guac-d-flex guac-align-center">
            <img src="/images/logo/png/logo-nb.png" alt="WebBuddy Logo" class="navbar-logo">
            <span class="guac-ml-2">WebBuddy.com</span>
        </a>
        <!-- Brand -->

        <!-- Links -->
        <ul class="guac-d-flex guac-align-center guac-gap-3 nav-links">

            <li class="dropdown">
                <button class="dropdown-toggle">
                    Browser Extension <i class="fa fa-chevron-down"></i>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#">Firefox</a></li>
                    <li><a href="#">Opera</a></li>
                    <li><a href="#">Brave</a></li>
                    <li><a href="#">Safari</a></li>
                    <li><a href="#">Chrome</a></li>
                </ul>
            </li>

            <li class="dropdown">
                <button class="dropdown-toggle">
                    Recent <i class="fa fa-chevron-down"></i>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#">Banned Domains</a></li>
                    <li><a href="#">Searched Domains</a></li>
                </ul>
            </li>

            <li class="dropdown">
                <button class="dropdown-toggle">
                    API <i class="fa fa-chevron-down"></i>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#">Getting Started</a></li>
                    <li><a href="#">Pricing</a></li>
                </ul>
            </li>

            <li><a href="#" class="nav-link">Our Mission</a></li>
            <li><a href="#" class="nav-link">Contact</a></li>

        </ul>

        <!-- Actions -->
        <div class="guac-d-flex guac-gap-2">
            <button class="guac-btn guac-btn-secondary">Log in</button>
            <button class="guac-btn btn-primary">API Access</button>
        </div>

    </div>
</nav>
<!-- Navbar -->
`;

export function loadNavbar(containerId = "navbar-component") {

    const container = document.getElementById(containerId);

    if (!container) {
        console.error(`Navbar container #${containerId} not found`);
        return;
    }

    container.innerHTML = navbarHTML;
}

// Auto-load navbar
loadNavbar();