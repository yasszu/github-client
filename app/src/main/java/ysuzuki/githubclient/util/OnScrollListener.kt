package ysuzuki.githubclient.util

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Set this listener to RecyclerView#addOnScrollListener
 *
 * @param mVisibleThreshold is number of remaining items before loading more.
 * @param layoutManager android.support.v7.widget.LinearLayoutManager
 */
class OnScrollListener(val mLayoutManager: LinearLayoutManager,
                       val onScrolled: () -> Unit,
                       val onLoad: () -> Unit) : RecyclerView.OnScrollListener() {

    constructor(mLayoutManager: LinearLayoutManager, onLoad: () -> Unit): this(mLayoutManager, {}, onLoad)

    val mVisibleThreshold = 6

    var previousItemCount = 0
        private set

    var mLoading = true
        private set

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        onScrolled()
        val visibleItemCount = mLayoutManager.childCount
        val totalItemCount = mLayoutManager.itemCount
        val firstVisibleItems = mLayoutManager.findFirstVisibleItemPosition()
        resetLoadingFrg(totalItemCount)
        loadNextItems(visibleItemCount, totalItemCount, firstVisibleItems)
    }

    fun resetLoadingFrg(total: Int) {
        if (mLoading && total > previousItemCount) {
            mLoading = false
            previousItemCount = total
        }
    }

    fun loadNextItems(visible: Int, total: Int, first: Int) {
        if (!mLoading && total <= first + visible + mVisibleThreshold) {
            mLoading = true
            onLoad()
        }
    }

    fun clear() {
        previousItemCount = 0
        mLoading = true
    }

}