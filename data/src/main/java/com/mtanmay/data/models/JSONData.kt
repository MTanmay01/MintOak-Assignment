package com.mtanmay.data.models

import com.google.gson.annotations.SerializedName
import com.mtanmay.domain.models.ChildItemTID
import com.mtanmay.domain.models.ChildItemMID
import com.mtanmay.domain.models.ParentItemMID
import com.mtanmay.domain.models.ParentItemTID

/**
 * Class to store the contents of JSON file
 */
data class JSONData(
    val sort: List<InnerSortData>
)

data class InnerSortData(
    @SerializedName("Mid") val mID: Int,
    @SerializedName("Tid") val tID: Long,
    val amount: Double,
    val narration: Long
)

/**
 * Mapping JSON file data stored with [JSONData] to [ParentItemMID] list (domain model)
 * by selecting unique MIDs and for each MID selecting only the unique TIDs
 */
fun JSONData.toParentItemMID(): List<ParentItemMID> {
    return sort
        .groupBy { it.mID }
        .map { (mid, list) ->
            ParentItemMID(
                mid = mid,
                entries = list
                    .map {
                         ChildItemTID(
                             tid = it.tID,
                             mAmount = it.amount,
                             mNarration = it.narration
                         )
                    }
                    .distinct()
            )
        }
}

/**
 * sorting by unique TIDs
 */
fun JSONData.toParentItemTID(): List<ParentItemTID> {
    return sort
        .groupBy { it.tID }
        .map { (tid, list) ->
            ParentItemTID(
                tid = tid,
                entries = list
                    .map {
                         ChildItemMID(
                             mid = it.mID,
                             mAmount = it.amount,
                             mNarration = it.narration
                         )
                    }
                    .distinct()
            )
        }
}