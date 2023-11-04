const fixedExtensionItems = document.querySelectorAll(
  ".fixed-extension-item .button"
);

/**
 * 고정 확장자 클릭 시 이벤트
 * */
fixedExtensionItems.forEach((item) => {
  item.addEventListener("click", function () {
    // 스타일 활성화 (토글)
    this.classList.toggle("active");

    // Ajax 요청
    const extensionName = this.textContent;
    let checked = this.classList.contains("active") ? "N" : "Y";

    $.ajax({
      url: "update",
      data: { name: extensionName, type: "F", checked: checked },
      type: "POST",
      success: function (result) {
        console.log("check update 성공");
      },
      error: function () {
        console.log("Fixed Extension Update 실패");
      },
    });
  });
});

/**
 * 확장자 입력 시 추가 버튼 활성화
 */
const customExtensionInput = document.querySelector(
  ".custom-extension-input-area > input"
);
const addButton = document.querySelector(".add-button");

customExtensionInput.addEventListener("input", function () {
  const inputValue = customExtensionInput.value;

  if (inputValue === "") {
    addButton.classList.remove("active");
  } else {
    addButton.classList.add("active");
  }
});

customExtensionInput.addEventListener("keyup", function (event) {
  if (event.keyCode === 13) {
    event.preventDefault();
    addButton.click();
  }
});

/**
 * 커스텀 확장자 추가 요청
 */
addButton.addEventListener("click", function () {
  const newCustomName = customExtensionInput.value;
  const currentCountValue = document.querySelector(".current").textContent;

  if (currentCountValue == 200) {
    customExtensionInput.value = "";
    alert("커스텀 확장자 최대 저장 횟수를 초과하였습니다.");
  } else {
    addCustomExtensionAjax(newCustomName);
  }
});

function addCustomExtensionAjax(newCustomName) {
  $.ajax({
    url: "add",
    data: { name: newCustomName, type: "C" },
    type: "POST",
    success: function (result) {
      console.log("add 성공");
      location.reload();
    },
    error: function () {
      customExtensionInput.value = "";
      alert("이미 추가된 확장자입니다.");
      console.log("add 실패");
    },
  });
}

// Enter 입력 시 추가 수행

// 커스텀 확장자 삭제
let deleteButtons = document.querySelectorAll(".delete");

deleteButtons.forEach((delButton) => {
  delButton.addEventListener("click", function () {
    const extensionName = this.previousElementSibling.textContent;

    $.ajax({
      url: "delete",
      data: {
        name: extensionName,
      },
      type: "POST",
      success: function (result) {
        console.log("custom 삭제 성공");
        location.reload();
      },
      error: function () {
        console.log("custom 삭제 실패");
      },
    });
  });
});

/**
 * 파일 업로드 시 파일명 표기
 */
const fileInput = document.getElementById("file");
const fileText = document.querySelector(".file > span");

const submitButton = document.querySelector(".submit");

fileInput.addEventListener("change", function () {
  if (fileInput.files.length > 0) {
    const fileName = fileInput.files[0].name;
    fileText.textContent = fileName;

    // 파일 선택 시 파일 제출 활성화
    submitButton.classList.add("active");
  } else {
    fileText.textContent = "파일 선택하기";

    // 파일 선택 시 파일 제출 활성화
    submitButton.classList.remove("active");
  }
});

submitButton.addEventListener("click", function () {
  if (fileInput.files.length > 0) {
    const fileName = fileInput.files[0].name;
    const fileNameArray = fileName.split(".");

    $.ajax({
      url: "checkValidExtension",
      data: {
        name: fileNameArray[1],
      },
      type: "POST",
      success: function (result) {
        if (result === true) {
          alert("제출 성공");
        } else {
          alert("제한된 확장자 입니다.");
        }
      },
      error: function () {
        console.log("checkValidExtension 실패");
      },
    });
  } else {
    alert("파일을 선택해주세요");
  }
});

/**
 * 초기화 버튼 클릭 시 DB 초기화 수행
 */
function resetExtensionList(type) {
  // DB 초기화 요청
  $.ajax({
    url: "reset",
    data: {
      type: type,
    },
    type: "POST",
    success: function (result) {
      console.log("fixedExtension 초기화 성공");
    },
    error: function () {
      console.log("fixedExtension 초기화 실패");
    },
  });

  // 화면 초기화
  if (type === "F") {
    fixedExtensionItems.forEach((item) => {
      item.classList.remove("active");
    });
  }

  if (type === "C") {
    const currentCount = document.querySelector(".current");
    const customExtensionList = document.querySelector(
      ".custom-extension-list"
    );
    customExtensionList.innerHTML = "";
    currentCount.textContent = 0;
  }
}
