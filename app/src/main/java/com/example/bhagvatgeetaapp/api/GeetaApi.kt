package com.example.bhagvatgeetaapp.api

import com.example.bhagvatgeetaapp.api.responses.Chapter
import com.example.bhagvatgeetaapp.api.responses.Verse
import retrofit2.http.GET
import retrofit2.http.Path

interface GeetaApi {
    @GET("gita/chapters")
    suspend fun getChaptersList(): List<Chapter>

    @GET("gita/{ch}")
    suspend fun getChapterInfo(
        @Path("ch") ch: Int
    ): Chapter

    @GET("gita/{ch}/{sl}")
    suspend fun getVerseInfo(
        @Path("ch") ch: Int,
        @Path("sl") sl: Int
    ) : Verse

    @GET("gita")
    suspend fun getRandomVerse(): Verse
}