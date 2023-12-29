package com.suitmedia.suitmediaapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.suitmedia.suitmediaapp.data.response.DataItem
import com.suitmedia.suitmediaapp.data.retrofit.ApiService

class UserPagingSource(private val apiService: ApiService) :
    PagingSource<Int, DataItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseDataStories = apiService.getUsers(params.loadSize)

            LoadResult.Page(
                data = responseDataStories.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseDataStories.data.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}