document.addEventListener("DOMContentLoaded", () => {
  //テキスト情報
  const listSelect = document.getElementById("js-listSelect");
  const inputId = document.getElementById("js-inputId");
  const inputName = document.getElementById("js-inputName");
  const inputPrice = document.getElementById("js-inputPrice");
  const inputDescription = document.getElementById("js-inputDescription");
  const inputImage = document.getElementById("js-inputImage");
  //ボタン
  const insertBtn = document.getElementById("js-insertBtn");
  //エラー
  const errarId = document.getElementById("js-errarId");
  const errarName = document.getElementById("js-errarName");
  const errarPrice = document.getElementById("js-errarPrice");
  const errarCategory = document.getElementById("js-errarCategory");
  const errarDescription = document.getElementById("js-errarDescription");
  const errarImage = document.getElementById("js-errarImage");


  //エラーメッセージ非表示
  errarId.style.display = "none";
  errarName.style.display = "none";
  errarPrice.style.display = "none";
  errarCategory.style.display = "none";
  errarDescription.style.display = "none";
  errarImage.style.display = "none";

  fetch("/categories").then((res) => {
    //RestControllerから受け取った値->res(成功/200 失敗/400)
    if (res.status === 400) {
      console.log("no");
    } else {
      res.json().then((data) => {
        data.forEach((category) => {
          const option1 = document.createElement("option");
          option1.value = category.id;
          option1.text = category.name;
          listSelect.appendChild(option1);
        });
      });
    }
  });

  //登録ボタンクリック時処理
  insertBtn.addEventListener("click", () => {
    const idText = Number(inputId.value);
    const nameText = inputName.value;
    const priceText = Number(inputPrice.value);
    const categoryText = listSelect.value;
    const descText = inputDescription.value;
    const imageURL = inputImage.value;

    //判別用FLAG
    let flg = 0;

    if (isNaN(idText) || idText == null || idText == "" || idText == 0) {
      errarId.style.display = "block";
      flg = 1;
    } else {
      errarId.style.display = "none";
    }
    if (isNaN(priceText) || priceText == null || priceText == "" || priceText == 0) {
      errarPrice.style.display = "block";
      flg = 1;
    } else {
      errarPrice.style.display = "none";
    }
    if (nameText == null || nameText == "") {
      errarName.style.display = "block";
      flg = 1;
    } else {
      errarName.style.display = "none";
    }
    if (categoryText == null || categoryText == "") {
      errarCategory.style.display = "block";
      flg = 1;
    } else {
      errarCategory.style.display = "none";
    }

    if (flg === 0) {
      //データ格納用
      const data = {
        id: 0,
        productId: idText,
        categoryId: categoryText,
        name: nameText,
        price: priceText,
        imagePath: imageURL,
        description: descText,
        createdAt: null,
        updatedAt: null,
      };

      console.log("click");
      console.log(data);

      fetch("/insertProduct", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      })
        .then((response) => {
          if (response.ok) {
            console.log("POST request processed");
            window.location.href = "/backToList"; //ControllerのGetに指示を出す
          } else {
            console.error("POST request failed");
            errarId.style.display = "block";
          }
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    }
  });
});
