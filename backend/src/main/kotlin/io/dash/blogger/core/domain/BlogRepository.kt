package io.dash.blogger.core.domain

import org.springframework.data.jpa.repository.JpaRepository

interface BlogRepository : JpaRepository<Blog, String>
