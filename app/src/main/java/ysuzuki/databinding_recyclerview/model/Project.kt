package ysuzuki.databinding_recyclerview.model

import ysuzuki.databinding_recyclerview.model.Owner

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
data class Project(
        val name: String = "",
        val full_name: String = "",
        val description: String = "",
        val url: String = "",
        val owner: Owner = Owner(),
        val language: String = ""
)