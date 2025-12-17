browser.webRequest.onBeforeRequest.addListener(
  (details) => {

    // URL Only
    if (details.type !== "main_frame") {
      return;
    }

    // Making sure we can't target our own server
    if (details.url.startsWith("http://localhost:7000")) {
      return;
    }

    // URL + Params and other relevant safety stuff
    const fullUrl = details.url;

    // Sending to our server
    fetch("http://localhost:7000", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        url: fullUrl
      })
    }).catch(err => {
      console.error("Fejl ved afsendelse af URL | ", err);
    });
  },
  { urls: ["<all_urls>"] }
);