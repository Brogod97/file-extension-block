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

/**
 * 커스텀 확장자 추가 요청
 * TODO: DB 저장, current 숫자 up, 아래 버튼 추가, input 초기화
 */
addButton.addEventListener("click", function () {});

// Enter 입력 시 추가 수행

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

    console.log(submitButton);

    // 파일 선택 시 파일 제출 활성화
    submitButton.classList.add("active");
  } else {
    fileText.textContent = "파일 선택하기";

    // 파일 선택 시 파일 제출 활성화
    submitButton.classList.remove("active");
  }
});

/**
 * 초기화 버튼 클릭 시 DB 초기화 수행
 */
