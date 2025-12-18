browser.runtime.onMessage.addListener(async (msg) => {

  // Initial
  if (msg.action !== "showBlocker") return;
  if (document.getElementById("safety-blocker-overlay")) return;

  // Wrapper
  const wrapper = document.createElement("div");
  wrapper.id = "webbuddy-content";

  // Overlay
  const overlay = document.createElement("div");
  overlay.id = "safety-blocker-overlay";

  // Overlay -> Wrapper
  document.body.appendChild(wrapper);
  wrapper.appendChild(overlay);

  // Scroll behavior
  document.body.style.overflow = "hidden";
  document.documentElement.style.overflow = "hidden";

  // Domain
  const domain = window.location.hostname;

  // Data Collect
  let data;
  try {
    const res = await fetch(`http://localhost:7000/api/check?url=${encodeURIComponent(location.href)}`);
    data = await res.json();
  } catch (e) {
    console.error("WebBuddy API error", e);
    return;
  }

  // Data setup
  const categories = Array.isArray(data.categories) ? data.categories : [];
  const confidence = data.confidence ?? 0;
  let lastValidated = data.lastValidated ?? "Ukendt";
  if (lastValidated !== "Ukendt") {
      const dateObj = new Date(lastValidated);
      const mm = String(dateObj.getMonth() + 1).padStart(2, '0');
      const dd = String(dateObj.getDate()).padStart(2, '0');
      const yy = String(dateObj.getFullYear()).slice(-4);
      lastValidated = `${mm}/${dd}-${yy}`;
  } 

  // HTML
  const categoryHTML = categories.map(cat => `
  <div class="webbuddy-category-card">
    <span class="webbuddy-category-title">${cat}</span>
  </div>
  `).join("");

  overlay.innerHTML = `
    <h1>WebBuddy</h1>
    <h2>Usikker hjemmeside</h2>

    <div class="webbuddy-url-card">
      <span class="webbuddy-url-label">Du er her</span>
      <span class="webbuddy-url-value">${domain}</span>
    </div>

    <p>
      <i>Denne side står som <span class="webbuddy-text-orange">usikker</span> i vores system!<br>
      Benyt kun siden hvis du kender den.</i>
    </p>

    <div class="webbuddy-categories">
      ${categoryHTML}
    </div>

    <div id="webbuddy-progress-container">
      <div id="webbuddy-progress-bar"></div>
    </div>

    <div class="webbuddy-validated-card">
      <div class="webbuddy-validated-text">
        <span class="label">Sidst valideret</span>
        <span class="value">${lastValidated}</span>
      </div>
    </div>

    <div class="safety-blocker-actions">
      <button id="back-btn">Gå tilbage</button>
      <button id="continue-btn">Gå videre alligevel</button>
    </div>
  `;

  // Progress bar
  const progressBar = document.getElementById("webbuddy-progress-bar");
  let progress = 0;

  const interval = setInterval(() => {
    if (progress >= confidence) {
      clearInterval(interval);
      return;
    }
    progress++;
    progressBar.style.width = progress + "%";

    if (progress <= 33) {
      progressBar.style.backgroundColor = "#22c55e";
    } else if (progress <= 66) {
      progressBar.style.backgroundColor = "#f59e0b";
    } else {
      progressBar.style.backgroundColor = "#ed2424";
    }
  }, 15);

  // Continue Button
  document.getElementById("continue-btn").onclick = () => {
    wrapper.remove();
    document.body.style.overflow = "";
    document.documentElement.style.overflow = "";
  };
  // Back Button
  document.getElementById("back-btn").onclick = () => {
    window.location.href = "https://www.google.com";
  };
});