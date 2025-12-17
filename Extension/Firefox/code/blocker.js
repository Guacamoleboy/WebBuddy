browser.runtime.onMessage.addListener((msg) => {

  if (msg.action === "showBlocker") {

    if (!document.getElementById("safety-blocker-overlay")) {

      // Overlay DIV
      const overlay = document.createElement("div");

      // Overlay ID
      overlay.id = "safety-blocker-overlay";

      // Overlay content
      overlay.innerHTML = `
        <h1>Usikker hjemmeside</h1>
        <p>Denne side er vurderet som potentielt usikker.</p>
        <div class="safety-blocker-actions">
          <button id="continue-btn">Gå videre alligevel</button>
          <button id="back-btn">Gå tilbage</button>
        </div>
      `;

      // Add overlay
      document.body.appendChild(overlay);

      // Disables scroll
      document.body.style.overflow = 'hidden';
      document.documentElement.style.overflow = 'hidden';

      // Continue Button
      document.getElementById("continue-btn").onclick = () => {
        overlay.remove();
        document.body.style.overflow = '';
        document.documentElement.style.overflow = '';
      };

      // Back Button
      document.getElementById("back-btn").onclick = () => {
        window.location.href = "https://www.google.com";
      };

    }
  }
});
