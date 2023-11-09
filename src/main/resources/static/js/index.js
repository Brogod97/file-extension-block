// Variables

const fixedExtensionItemButtons = document.querySelectorAll(
  ".fixed-extension-item .button"
);

const customExtensionInput = document.querySelector(
  ".custom-extension-input-area > input"
);
const addButton = document.querySelector(".add-button");
let deleteButtons = document.querySelectorAll(".delete");

const fileInput = document.getElementById("file");
const fileText = document.querySelector(".file > span");
const submitButton = document.querySelector(".submit");

const resetButtons = document.querySelectorAll(".reset-button");

const Type = {
  Fixed: "F",
  Custom: "C",
};

// Events

/**
 * 고정 확장자 버튼 클릭 이벤트
 * */
fixedExtensionItemButtons.forEach((button) => {
  button.addEventListener("click", function () {
    // 스타일 적용
    this.classList.toggle("active");

    // Ajax 요청
    const extensionName = this.textContent;
    let isChecked = this.classList.contains("active") ? "N" : "Y";

    updateFixedExtension(extensionName, Type.Fixed, isChecked);
  });
});

/**
 * 커스텀 확장자 입력 시 버튼 스타일 활성화
 */
customExtensionInput.addEventListener("input", function (event) {
  const inputValue = customExtensionInput.value;

  if (inputValue === "") {
    addButton.classList.remove("active");
  } else {
    addButton.classList.add("active");
  }
});

/**
 * 커스텀 확장자 추가 버튼 클릭 시 추가
 */
addButton.addEventListener("click", function () {
  const newCustomName = customExtensionInput.value;
  const customCount = document.querySelector(".current").textContent;

  // 커스텀 수 200개 이상 등록 방지
  if (customCount == 200) {
    customExtensionInput.value = "";
    alert("커스텀 확장자 최대 저장 횟수를 초과하였습니다.");
  } else {
    addCustomExtension(newCustomName, Type.Custom);
  }
});

/**
 * 커스텀 확장자 Enter 입력 시 추가
 */
customExtensionInput.addEventListener("keyup", function (event) {
  if (event.keyCode === 13) {
    event.preventDefault();
    addButton.click();
  }
});

/**
 * 커스텀 확장자 삭제 버튼 클릭 시 삭제
 * */
deleteButtons.forEach((delButton) => {
  delButton.addEventListener("click", function () {
    const extensionName = this.previousElementSibling.textContent;

    deleteCustomExtension(extensionName);
  });
});

/**
 * [고정 / 커스텀] 초기화 버튼 클릭 시 수행
 */
resetButtons.forEach((button) => {
  button.addEventListener("click", function () {
    if (this.classList.contains("fixed")) {
      // DB 초기화
      const type = Type.Fixed;
      resetExtension(type);

      // 스타일 수정
      fixedExtensionItemButtons.forEach((item) => {
        item.classList.remove("active");
      });
    } else if (this.classList.contains("custom")) {
      // DB 초기화
      const type = Type.Custom;
      resetExtension(type);

      // 확장자 수 초기화
      const currentCount = document.querySelector(".current");
      const customExtensionList = document.querySelector(
        ".custom-extension-list"
      );
      customExtensionList.innerHTML = "";
      currentCount.textContent = 0;
    }
  });
});

/**
 * 파일 업로드 시 파일명 표기
 */
fileInput.addEventListener("change", function () {
  if (fileInput.files.length > 0) {
    const fileName = fileInput.files[0].name;

    // 표시 문구 변경 및 스타일 활성화
    fileText.textContent = fileName;
    submitButton.classList.add("active");
  } else {
    // 표시 문구 변경 및 스타일 활성화
    fileText.textContent = "파일 선택하기";
    submitButton.classList.remove("active");
  }
});

/**
 * 파일 업로드 제출 버튼 클릭 시 유효성 검사
 */
submitButton.addEventListener("click", function () {
  if (fileInput.files.length > 0) {
    const fileExtensionName = fileInput.files[0].name.split(".")[1];

    checkValidExtension(fileExtensionName);
  } else {
    alert("파일을 선택해주세요");
  }
});

/**
 * 파일 제출 시 확장자명 유효성 검사
 * @param fileExtensionName
 */
function checkValidExtension(fileExtensionName) {
  $.ajax({
    url: "checkValidExtension",
    data: {
      name: fileExtensionName,
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
}

// Functions

/**
 * 고정 확장자 checked 필드 업데이트 요청
 * @param extensionName
 * @param isChecked
 */
function updateFixedExtension(extensionName, type, isChecked) {
  $.ajax({
    url: "update",
    data: { name: extensionName, type: type, checked: isChecked },
    type: "POST",
    success: function () {
      console.log("Fixed Extension Update 성공");
    },
    error: function () {
      console.log("Fixed Extension Update 실패");
    },
  });
}

/**
 * 커스텀 확장자 추가 요청
 * @param newCustomName
 */
function addCustomExtension(newCustomName, type) {
  $.ajax({
    url: "add",
    data: { name: newCustomName, type: type },
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

/**
 * 커스텀 확장자 삭제 요청
 * @param extensionName
 */
function deleteCustomExtension(extensionName) {
  $.ajax({
    url: "delete",
    data: {
      name: extensionName,
    },
    type: "POST",
    success: function () {
      console.log("custom 삭제 성공");
      location.reload();
    },
    error: function () {
      console.log("custom 삭제 실패");
    },
  });
}

/**
 * 확장자 초기화 요청
 * @param type
 */
function resetExtension(type) {
  $.ajax({
    url: "reset",
    data: {
      type: type,
    },
    type: "POST",
    success: function () {
      console.log("Extension 초기화 성공");
    },
    error: function () {
      console.log("Extension 초기화 실패");
    },
  });
}
