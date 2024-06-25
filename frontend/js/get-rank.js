const rank = document.querySelector("#show-rank #rank-box");

fetch("http://localhost:8080/api/v1/blogs/postingCount/rankings")
  .then((response) => response.json())
  .then((json) => {
    const data = json.ranks;
    for (let index = 0; index < data.length; index++) {
      const url = data[index].blogUrl;
      const postingCount = data[index].postingCount;
      const blogLink = document.createElement("a");
      blogLink.href = url;
      blogLink.textContent = `${index + 1}. ${String(url).replace('https://', '')} (${postingCount}개)`;
      rank.appendChild(blogLink);
      rank.appendChild(document.createElement("br"));
    }
  });