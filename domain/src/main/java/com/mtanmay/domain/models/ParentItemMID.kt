package com.mtanmay.domain.models

/**
 * Class to store serialized JSON data as [ParentItemMID] for domain layer which is eventually used by the UI
 */
data class ParentItemMID(
    val mid: Int,
    val entries: List<ChildItemTID>,
    var mExpanded: Boolean = false
): BaseParentItem {
    override var isExpanded: Boolean
        get() = mExpanded
        set(value) {
            mExpanded = value
        }
}

data class ChildItemTID(
    val tid: Long,
    val mAmount: Double,
    val mNarration: Long
): BaseChildItem {
    override val amount: Double
        get() = mAmount
    override val narration: Long
        get() = mNarration
}