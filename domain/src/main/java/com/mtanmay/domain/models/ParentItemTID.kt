package com.mtanmay.domain.models

data class ParentItemTID(
    val tid: Long,
    val entries: List<ChildItemMID>,
    private var expanded: Boolean = false
): BaseParentItem {
    override var isExpanded: Boolean
        get() = expanded
        set(value) {
            expanded = value
        }
}

data class ChildItemMID(
    val mid: Int,
    val mAmount: Double,
    val mNarration: Long
): BaseChildItem {
    override val amount: Double
        get() = mAmount

    override val narration: Long
        get() = mNarration
}