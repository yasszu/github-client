package ysuzuki.githubclient.model

import com.squareup.moshi.Json

/**
 * Created by Yasuhiro Suzuki on 2017/04/08.
 */
data class SearchResult(
        @Json(name = "html_url") val totalCount: Int,
        @Json(name = "incomplete_results") val results: Boolean,
        @Json(name = "items") val items: List<Repository>
)