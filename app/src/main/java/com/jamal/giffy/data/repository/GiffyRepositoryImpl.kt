package com.jamal.giffy.data.repository

import com.jamal.giffy.data.model.GiffyResponse
import com.jamal.giffy.data.network.WebServices
import io.reactivex.Single

class GiffyRepositoryImpl(private val webServices: WebServices) : GiffyRepository {
    override fun fetchTrendingGifs(): Single<GiffyResponse> {
        return webServices.fetchTrendingGif()
    }

    override fun searchGif(query: String): Single<GiffyResponse> {
        return webServices.searchGif(query)
    }
}