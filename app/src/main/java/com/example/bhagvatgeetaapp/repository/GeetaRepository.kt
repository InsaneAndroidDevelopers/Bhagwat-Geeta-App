package com.example.bhagvatgeetaapp.repository

import com.example.bhagvatgeetaapp.api.api


class GeetaRepository() {

    suspend fun getChapterList() = api.getChaptersList()

    suspend fun getChapterInfo(number: Int) = api.getChapterInfo(number)

    suspend fun getVerseInfo(ch: Int, ver: Int) = api.getVerseInfo(ch, ver)

    suspend fun getRandomVerse() = api.getRandomVerse()

}