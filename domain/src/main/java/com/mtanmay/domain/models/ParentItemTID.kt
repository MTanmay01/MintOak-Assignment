package com.mtanmay.domain.models

data class ParentItemTID(
    val tid: Long,
    val entries: List<ChildItemMID>,
    var isExpanded: Boolean = false
)

data class ChildItemMID(
    val mid: Int,
    val amount: Double,
    val narration: Long
)