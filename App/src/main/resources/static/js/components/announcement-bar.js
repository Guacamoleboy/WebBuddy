const announcementHTML = `
<!-- Announcement Bar -->
<div class="announcement-bar">
    <div class="announcement-track">
        <span>WebBuddy Version 1.0.0 Launched | Download the extension for free</span>
    </div>
</div>
<!-- Announcement Bar -->
`;

export function loadAnnouncement(containerId = "announcement-component") {

    const container = document.getElementById(containerId);

    if (!container) {
        console.error(`Announcement container #${containerId} not found`);
        return;
    }

    container.innerHTML = announcementHTML;
}

// Auto-load announcement
loadAnnouncement();
