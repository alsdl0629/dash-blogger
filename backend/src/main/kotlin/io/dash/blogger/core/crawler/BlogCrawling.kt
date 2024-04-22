package io.dash.blogger.core.crawler

import io.dash.blogger.core.domain.Blog
import org.openqa.selenium.By.ByCssSelector
import org.openqa.selenium.chrome.ChromeDriver
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class BlogCrawling {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun countPostingBy(blog: Blog): Int {
        runCatching {
            var postingCount: Int

            ChromeDriver().apply {
                get(blog.targetUrl)
                Thread.sleep(500)
                postingCount = findElement(ByCssSelector(blog.howToFindElement)).text.toNumber()
            }

            return postingCount
        }.onFailure {
            logger.error("Fail Crawling: {}, Message: {}", blog.targetUrl, it.message)
        }.getOrThrow()
    }
}

private fun String.toNumber(): Int {
    val number = this.removeSurrounding("(", ")")
    return number.toIntOrNull() ?: throw NumberFormatException("$number cannot be converted to Number")
}
