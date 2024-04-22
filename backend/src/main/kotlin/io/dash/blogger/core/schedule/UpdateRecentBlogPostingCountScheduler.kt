package io.dash.blogger.core.schedule

import io.dash.blogger.core.crawler.BlogCrawling
import io.dash.blogger.core.domain.BlogRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class UpdateRecentBlogPostingCountScheduler(
    private val blogRepository: BlogRepository,
    private val blogCrawling: BlogCrawling
) {
    // 매일 자정마다 실행
    @Scheduled(cron = "0 0 0 * * *")
    fun update() {
        blogRepository.findAll().map { blog ->
            blog.apply {
                val recentPostingCount = blogCrawling.countPostingBy(blog)
                updateRecentPostingCount(recentPostingCount)
            }
        }
    }
}
