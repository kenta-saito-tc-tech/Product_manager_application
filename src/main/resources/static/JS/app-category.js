document.addEventListener("DOMContentLoaded", () => {
  //ボタン
  const deleteBtn = document.getElementById("js-deleteBtn");

  let checkedData = null;

  //categoriesテーブルの情報取得
  function listCategoryShow() {
    fetch("/categories").then((res) => {
      //RestControllerから受け取った値->res(成功/200 失敗/400)
      if (res.status === 400) {
        console.log("no");
      } else {
        //.jsonは非同期処理(.jsonの受け取り)
        res.json().then((data) => {
          //.jsonで受け取った値->data
          // テーブル要素の生成
          const tableContainer = document.getElementById("js-tableContainer");
          tableContainer.innerHTML = "";
          const table = document.createElement("table");

          // テーブルヘッダーの生成
          const thead = document.createElement("thead");
          const headerRow = document.createElement("tr");
          //チェックボックスの生成
          const checkbox = document.createElement("input");
          checkbox.type = "checkbox";
          headerRow.appendChild(checkbox);
          const th2 = document.createElement("th");
          th2.textContent = "ID";
          headerRow.appendChild(th2);
          const th3 = document.createElement("th");
          th3.textContent = "カテゴリ名";
          headerRow.appendChild(th3);
          const th4 = document.createElement("th");
          th4.textContent = "編集";
          headerRow.appendChild(th4);
          thead.appendChild(headerRow);

          const tbody = document.createElement("tbody");
          data.forEach((data1) => {
            const row = document.createElement("tr");
            const checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.addEventListener("change", function (event) {
              if (event.target.checked) {
                console.log("チェックされました");
                checkedData = data1; //チェックされたデータを格納
                console.log(checkedData);
              } else {
                console.log("チェックが解除されました");
                checkedData = null;
              }
            });
            row.appendChild(checkbox);
            for (const key in data1) {
              const input = document.createElement("input");
              if (key === "name") {
                input.type = "text";
                input.value = data1[key];
                input.readOnly = true;
                input.id = data1.id;
                row.appendChild(input);
              }
              if (key === "id") {
                const cell = document.createElement("td");
                cell.textContent = data1[key];
                row.appendChild(cell);
              }
            }
            const cell = document.createElement("td");
            cell.textContent = "編集";
            cell.addEventListener("click", () => {
              cell.textContent = "更新";
              showProductDetails(data1, cell, data1.id);
            });
            cell.classList.add("changeBtn");
            row.appendChild(cell);
            tbody.appendChild(row);
          });
          // テーブルにヘッダーとボディを追加
          table.appendChild(thead);
          table.appendChild(tbody);

          // テーブルをHTMLに追加
          tableContainer.appendChild(table);
        });
      }
    });
  }

  // 編集
  function showProductDetails(data, cell, id) {
    console.log("click");
    const changeName = document.getElementById(id);
    changeName.readOnly = false;
    cell.addEventListener("click", () => updateCategory(data, id));
  }

  //削除ボタンクリック時処理
  deleteBtn.addEventListener("click", function () {
    console.log("click");
    const deleteData = {
      id: checkedData.id,
      name: checkedData.name,
      createdAt: checkedData.createdAt,
      updatedAt: checkedData.updatedAt,
    };
    console.log(deleteData);

    fetch("/categoryDelete", {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(deleteData),
    })
      .then((response) => {
        if (response.ok) {
          console.log("DELETE request processed");
          listCategoryShow();
          // ポップアップを表示
          window.setTimeout(function () {
            alert("削除が完了しました");
          }, 1000);
        } else {
          console.error("DELETE request failed");
        }
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  });

  //更新
  function updateCategory(data, id) {
    console.log(id);
    const newName = document.getElementById(id).value;

    const newData = {
      id: data.id,
      name: newName,
      createdAt: data.createdAt,
      updatedAt: data.updatedAt,
    };

    console.log(newData);
    fetch("/categoryUpdate", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newData),
    })
      .then((response) => {
        if (response.ok) {
          console.log("PUT request processed");
          listCategoryShow();
          // ポップアップを表示
          window.setTimeout(function () {
            alert("更新が完了しました");
          }, 1000);
        } else {
          console.error("PUT request failed");
        }
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }

  listCategoryShow();
});
