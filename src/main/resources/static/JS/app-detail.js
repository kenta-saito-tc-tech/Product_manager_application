document.addEventListener("DOMContentLoaded", () => {
  //最初非表示(編集時表示)
  const firstNone1 = document.getElementById("js-firstNone1");
  const firstNone2 = document.getElementById("js-firstNone2");
  const firstNone3 = document.getElementById("js-firstNone3");
  const firstNone4 = document.getElementById("js-firstNone4");
  const firstNone5 = document.getElementById("js-firstNone5");
  //各ボタン
  const updateBtn = document.getElementById("js-updateBtn");
  const deleteBtn = document.getElementById("js-deleteBtn");
  const changeBtn = document.getElementById("js-changeBtn");

  //エラー
  const errarId = document.getElementById("js-errarId");
  const errarName = document.getElementById("js-errarName");
  const errarPrice = document.getElementById("js-errarPrice");
  //input
  const inputId = document.getElementById("js-inputId");
  const inputName = document.getElementById("js-inputName");
  const inputPrice = document.getElementById("js-inputPrice");
  const listSelect = document.getElementById("js-listSelect");
  const selectedCategory = document.getElementById("js-selectedCategory");
  const inputDescription = document.getElementById("js-inputDescription");
  const inputImage = document.getElementById("js-inputImage");

  //IDの値
  const id = document.getElementById("js-id").value;
  console.log(id);
  //権限の値
  const role = document.getElementById("js-role").value;
  console.log(role);

  //エラーメッセージ非表示
  errarId.style.display = "none";
  errarName.style.display = "none";
  errarPrice.style.display = "none";

  //最初非表示
  firstNone1.style.display = "none";
  firstNone2.style.display = "none";
  firstNone3.style.display = "none";
  firstNone4.style.display = "none";
  firstNone5.style.display = "none";
  updateBtn.style.display = "none";

  if (role == 2) {
    changeBtn.style.display = "none";
    deleteBtn.style.display = "none";
  }

  function productDetailShow() {
    //初期表示
    fetch(`/productDetail?productId=${id}`).then((res) => {
      //RestControllerから受け取った値->res(成功/200 失敗/400)
      if (res.status === 400) {
        console.log("no");
      } else {
        res.json().then((data) => {
          console.log(data);
          inputId.value = data.productId;
          inputId.readOnly = true;
          inputId.style.backgroundColor = "peachpuff";
          inputName.value = data.name;
          inputName.readOnly = true;
          inputName.style.backgroundColor = "peachpuff";
          inputPrice.value = data.price;
          inputPrice.readOnly = true;
          inputPrice.style.backgroundColor = "peachpuff";
          //カテゴリを入れる
          fetch("/categories").then((res) => {
            //RestControllerから受け取った値->res(成功/200 失敗/400)
            if (res.status === 400) {
              console.log("no");
            } else {
              res.json().then((categories) => {
                categories.forEach((category) => {
                  if (category.id === data.categoryId) {
                    selectedCategory.value = category.id;
                    selectedCategory.text = category.name;
                  }
                });
              });
            }
          });
          inputDescription.value = data.description;
          inputDescription.readOnly = true;
          inputDescription.style.backgroundColor = "peachpuff";
          inputImage.src = data.imagePath;
        });
      }
    });
  }

  //編集ボタンクリック時処理
  changeBtn.addEventListener("click", function () {
    //カテゴリのセレクト表示
    fetch("/categories").then((res) => {
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

    //input変更、ボタンの表示、非表示
    updateBtn.style.display = "block";
    deleteBtn.style.display = "none";
    changeBtn.style.display = "none";
    inputId.readOnly = false;
    inputId.style.backgroundColor = "white";
    inputName.readOnly = false;
    inputName.style.backgroundColor = "white";
    inputPrice.readOnly = false;
    inputPrice.style.backgroundColor = "white";
    inputDescription.readOnly = false;
    inputDescription.style.backgroundColor = "white";
  });

  //削除ボタンクリック時処理
  deleteBtn.addEventListener("click", function () {
    console.log("click");
    const deleteData = {
      id: id,
      productId: inputId.value,
      categoryId: listSelect.value,
      name: inputName.value,
      price: inputPrice.value,
      imagePath: null,
      description: inputDescription.value,
      createdAt: null,
      updatedAt: null,
    };
    console.log(deleteData);

    fetch("/productDelete", {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(deleteData),
    })
      .then((response) => {
        if (response.ok) {
          console.log("DELETE request processed");
          window.location.href = "/backToList"; //ControllerのGetに指示を出す
          // 画面推移後にポップアップを表示
          window.setTimeout(function () {
            alert("削除が完了しました");
          }, 1000);
        } else {
          console.error("DELETE request failed");
          // ポップアップを表示
          window.setTimeout(function () {
            alert("追加時にエラーが発生しました");
          }, 1000);
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        // ポップアップを表示
        window.setTimeout(function () {
          alert("追加時にエラーが発生しました");
        }, 1000);
      });
  });

  //更新ボタンクリック時処理
  updateBtn.addEventListener("click", function () {
    const idText = Number(inputId.value);
    const nameText = inputName.value;
    const priceText = Number(inputPrice.value);
    const categoryText = listSelect.value;
    const descText = inputDescription.value;

    //判別用FLAG
    let flg = 0;

    if (isNaN(idText) || idText === null || idText === "") {
      errarId.style.display = "block";
      flg = 1;
    } else {
      errarId.style.display = "none";
    }
    if (isNaN(priceText) || priceText === null || priceText === "") {
      errarPrice.style.display = "block";
      flg = 1;
    } else {
      errarPrice.style.display = "none";
    }
    if (nameText === null || nameText === "") {
      errarName.style.display = "block";
      flg = 1;
    } else {
      errarName.style.display = "none";
    }

    if (flg === 0) {
      //データ格納用
      const data = {
        id: id,
        productId: idText,
        categoryId: categoryText,
        name: nameText,
        price: priceText,
        imagePath: null,
        description: descText,
        createdAt: null,
        updatedAt: null,
      };

      console.log("click");
      console.log(data);

      fetch("/updateProduct", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      })
        .then((response) => {
          if (response.ok) {
            console.log("POST request processed");
            window.location.href = "/backToList";
            // 画面推移後にポップアップを表示
            window.setTimeout(function () {
              alert("更新が完了しました");
            }, 1000);
          } else {
            console.error("POST request failed");
            errarId.style.display = "block";
            // ポップアップを表示
            window.setTimeout(function () {
              alert("更新時にエラーが発生しました");
            }, 1000);
          }
        })
        .catch((error) => {
          console.error("Error:", error);
          // ポップアップを表示
          window.setTimeout(function () {
            alert("更新時にエラーが発生しました");
          }, 1000);
        });
    }
  });

  productDetailShow();
});
