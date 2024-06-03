package io.dash.blogger.core.controller

import io.dash.blogger.core.controller.dto.GetAllPostingCountRankingResponse
import io.dash.blogger.core.controller.dto.PostingCount
import io.dash.blogger.core.domain.Blog
import io.dash.blogger.core.domain.BlogPlatform.TISTORY
import io.dash.blogger.core.domain.BlogPlatform.VELOG
import io.dash.blogger.core.service.BlogService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/blogs")
@RestController
class BlogController(
    private val blogService: BlogService,
) {
    @GetMapping("/postingCount")
    fun countPosting(
        @RequestParam targetUrl: String,
        @RequestParam(required = false) cssSelector: String = VELOG_CSS_SELECTOR,
    ): PostingCount {
        return if (targetUrl.contains(VELOG.text)) blogService.countPosting(
            Blog(
                targetUrl = targetUrl,
                blogPlatform = VELOG,
                howToFindElement = cssSelector
            )
        ) else blogService.countPosting(
            Blog(
                targetUrl = targetUrl,
                blogPlatform = TISTORY,
                howToFindElement = "#$cssSelector"  // #을 붙여서 요청 보내면 사라짐
            )
        )
    }

    @GetMapping("/postingCount/rankings")
    fun getPostingCountRanking(@RequestParam(required = false) top: Int = 10): GetAllPostingCountRankingResponse =
        blogService.getPostingCountRanking(top)

    companion object {
        const val VELOG_CSS_SELECTOR =
            "body > div > div.BasicLayout_block__6bmSl > div.responsive_mainResponsive___uG64 > main > div > section > div.VelogPosts_block__nfCQF > div.VelogTagHorizontalList_block__s42PD > a.VelogTagHorizontalList_tagItem__1tP3p.VelogTagHorizontalList_active__5ASRU > span"
    }
}
