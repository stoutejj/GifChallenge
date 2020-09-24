package com.jamal.giffy.data.repository

import com.jamal.giffy.data.model.GiffyResponse
import io.reactivex.Single

interface GiffyRepository {
    fun fetchTrendingGifs(): Single<GiffyResponse>
    fun searchGif(query: String): Single<GiffyResponse>
}