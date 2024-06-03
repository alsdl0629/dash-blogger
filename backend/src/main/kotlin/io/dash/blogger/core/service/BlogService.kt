package io.dash.blogger.core.service

import io.dash.blogger.core.controller.dto.GetAllPostingCountRankingResponse
import io.dash.blogger.core.controller.dto.PostingCount
import io.dash.blogger.core.crawler.BlogCrawling
import io.dash.blogger.core.domain.Blog
import io.dash.blogger.core.domain.BlogRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class BlogService(
    private val blogRepository: BlogRepository,
    private val blogCrawling: BlogCrawling
) {
    @Transactional
    fun countPosting(blog: Blog): PostingCount {
        val findBlog = blogRepository.findByIdOrNull(blog.targetUrl) ?: register(blog)
        return PostingCount(findBlog.getPostingCount)
    }

    private fun register(blog: Blog): Blog {
        blog.apply {
            val postingCount = blogCrawling.countPostingBy(this)
            updateRecentPostingCount(postingCount)
            return blogRepository.save(this)
        }
    }

    fun getPostingCountRanking(top: Int): GetAllPostingCountRankingResponse {
        val topBlogs = blogRepository.findAllBlogRankingByTop(top)

        return GetAllPostingCountRankingResponse(
            topBlogs.map { it.targetUrl }
        )
    }
}
