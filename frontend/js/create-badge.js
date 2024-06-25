const a = document.querySelector("svg text");

const url = localStorage.getItem("url");
if (url.includes("velog")) {
  fetch(`http://localhost:8080/api/v1/blogs/postingCount?targetUrl=${url}`)
    .then((response) => response.json())
    .then((data) => a.textContent = `total ${data.postingCount}`);
} else {
  const cssSelector = localStorage.getItem("cssSelector");
  fetch(`http://localhost:8080/api/v1/blogs/postingCount?targetUrl=${url}&cssSelector=${cssSelector}`)
    .then((response) => response.json())
    .then((data) => a.textContent = `total ${data.postingCount}`);
}
