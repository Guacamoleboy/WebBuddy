browser.tabs.onUpdated.addListener(async (tabId, changeInfo, tab) => {

  if (changeInfo.status !== "complete") return;

  // Prevents us targetting our own site
  if (!tab.url || tab.url.startsWith("http://localhost:7000")) return;

  try {
  
    const response = await fetch(`http://localhost:7000/api/check?url=${encodeURIComponent(tab.url)}`);
    const data = await response.json();

    if (!data.safe) {
      browser.tabs.sendMessage(tabId, { action: "showBlocker" });
    }

  } catch (e) {
    console.error("Scanner.js failed | ", e);
  }

});