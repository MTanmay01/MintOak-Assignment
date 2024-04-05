package com.mtanmay.domain.models

/**
 * Class to store serialized JSON data as [ParentItem] for domain layer which is eventually used by the UI
 */
data class ParentItem(
    val mid: Int,
    val entries: List<ChildItem>,
    var isExpanded: Boolean = false
)

data class ChildItem(
    val tid: Long,
    val amount: Double,
    val narration: Long
)