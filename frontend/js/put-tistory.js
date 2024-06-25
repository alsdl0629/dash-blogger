const select = document.getElementById("blog-platform");
const hiddenLabel = document.getElementById("css-selector-label");
const hiddenInput = document.getElementById("cssSelector");

const HIDDEN_CLASS = "hidden"

function onSelectTistory() {
  const blogFlatform = select.value;
  if (blogFlatform === "TISTORY") {
    hiddenLabel.classList.remove(HIDDEN_CLASS);
    hiddenInput.classList.remove(HIDDEN_CLASS);
  } else if (!hiddenLabel.classList.contains(HIDDEN_CLASS) 
    && !hiddenInput.classList.contains(HIDDEN_CLASS)) {
    hiddenLabel.classList.add(HIDDEN_CLASS);
    hiddenInput.classList.add(HIDDEN_CLASS);
  }
}

select.addEventListener("change", onSelectTistory);