document.addEventListener("DOMContentLoaded", () => {
  const inputId = document.getElementById("js-inputId");
  const inputPass = document.getElementById("js-inputPass");
  const logButtom = document.getElementById("js-logButtom");
  const errarId = document.getElementById("js-idErrar");
  const errarPass = document.getElementById("js-passErrar");

  logButtom.addEventListener("click", () => {
    const id = inputId.value;
    const pass = inputPass.value;
    if (id.length <= 10 && id.length >= 4 && pass.length >= 4) {
      const data = { id: id, pass: pass };
      console.log(data);
      fetch("/log-check", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      })
        .then((response) => {
          if (response.ok) {
            console.log("LOGIN request processed");
            window.location.href = "/change-page"; //ControllerのGetに指示を出す
          } else {
            console.error("LOGIN request failed");
            console.log(data);
            errarId.textContent = "IDかPASSが間違っています";
            errarPass.textContent = "IDかPASSが間違っています";
          }
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    } else {
      errarId.textContent = "IDは4文字以上10文字以下です";
      errarPass.textContent = "PASSは4文字以上です";
    }
  });
});
