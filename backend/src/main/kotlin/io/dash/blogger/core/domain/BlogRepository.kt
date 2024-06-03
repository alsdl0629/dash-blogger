package io.dash.blogger.core.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BlogRepository : JpaRepository<Blog, String> {
    @Query("select b from Blog b order by b.postingCount desc limit :top")
    fun findAllBlogRankingByTop(top: Int): List<Blog>
}
