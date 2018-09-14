package com.example.samanta.tvtrackermvp.listeners;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private int visibleTreshold = 5;
    private int currentPage = 1;
    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private int startingPageIndex = 1;

    private RecyclerView.LayoutManager mLayoutManager;

    public void onScrolled(@Nullable RecyclerView view, int dx, int dy) {

        int lastVisibleItemPosition = 0;
        int totalItemCount = mLayoutManager.getItemCount();
        RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
        RecyclerView.LayoutManager newLayoutManager;
        if (mLayoutManager instanceof GridLayoutManager) {
            newLayoutManager = this.mLayoutManager;
            if (this.mLayoutManager == null) {
                throw new TypeCastException("Null cannot be cast to non-nul type");
            }
            lastVisibleItemPosition = ((GridLayoutManager) newLayoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof LinearLayoutManager) {
            newLayoutManager = this.mLayoutManager;
            if (this.mLayoutManager == null) {
                throw new TypeCastException("Null cannot be cast to non-null type");
            }
            lastVisibleItemPosition = ((LinearLayoutManager) newLayoutManager).findLastVisibleItemPosition();
        }

        if (totalItemCount < this.previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        if (this.loading && totalItemCount > this.previousTotalItemCount) {
            this.loading = false;
            this.previousTotalItemCount = totalItemCount;
        }

        if (!this.loading && lastVisibleItemPosition + this.visibleTreshold > totalItemCount) {
            int newCurrentPage = this.currentPage++;
            this.onLoadMore(this.currentPage, totalItemCount, view);
            this.loading = true;
        }
    }

    public abstract void onLoadMore(int curentPage, int newTotalItemCount, @Nullable RecyclerView newView);

    public EndlessScrollListener(@NotNull LinearLayoutManager layoutManager) {
        super();
        Intrinsics.checkParameterIsNotNull(layoutManager, "layoutManager");
        this.visibleTreshold = 5;
        this.currentPage = 1;
        this.loading = true;
        this.startingPageIndex = 1;
        this.mLayoutManager = (RecyclerView.LayoutManager) layoutManager;
    }

}
