const button = document.querySelector("#input-button");
const targetUrl = document.querySelector("#targetUrl");
const cssSelector = document.querySelector("#cssSelector");
const blogFlatform = document.getElementById("blog-platform");
const markdown = document.querySelector("#markdown-link");
const htmlLink = document.querySelector("#html-link");
const embedUrl = document.querySelector("#embed-url-link");

function onButtonClick() {
  const url = targetUrl.value;
  localStorage.setItem("url", url);
  if (blogFlatform.value === "VELOG") {
    if (url !== null && url !== "") {
      markdown.textContent = `![${url}](http://localhost:5501/html/velog-img.html)`;
      htmlLink.textContent = `<a href="http://localhost:5501/html/index.html">
  <img src="http://localhost:5501/html/velog-img.html"/></a>`;
      embedUrl.textContent = `http://localhost:5501/html/velog-img.html`;
    }
  } else {
    if (url !== null && url !== "") {
      const cssSelector = cssSelector.value;
      localStorage.setItem("cssSelector", cssSelector);
      markdown.textContent = `![${url}](http://localhost:5501/html/tistory-img.html)`;
      htmlLink.textContent = `<a href="http://localhost:5501/html/index.html">
      <img src="http://localhost:5501/html/tistory-img.html"/></a>`;
      embedUrl.textContent = `http://localhost:5501/html/tistory-img.html`;
    }
  }
}

button.addEventListener("click", onButtonClick);