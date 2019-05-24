package ysuzuki.githubclient.model

import com.squareup.moshi.Json

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
data class Repo(
        val name: String = "",
        val full_name: String = "",
        val description: String = "",
        @Json(name = "html_url") val url: String = "",
        val owner: Owner = Owner(),
        val language: String = "",
        @Json(name = "stargazers_count") val starts: Int
)