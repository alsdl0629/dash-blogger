package io.dash.blogger.core.domain

import io.dash.blogger.core.common.entity.BaseEntity
import io.dash.blogger.core.controller.dto.PostingCount
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id

@Entity
class Blog(
    @Id
    val targetUrl: String,

    @Column(nullable = false)
    private var postingCount: Int = PostingCount.TEMPORARY_VALUE,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(7)", nullable = false)
    val blogPlatform: BlogPlatform,

    @Column(columnDefinition = "VARCHAR(500)", nullable = false)
    val howToFindElement: String
) : BaseEntity() {
    val getPostingCount
        get() = this.postingCount

    fun updateRecentPostingCount(postingCount: Int) {
        this.postingCount = postingCount
    }
}
