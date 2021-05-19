package com.example.bhagvatgeetaapp.models

data class VersesItem(
    val chapter_number: Int,
    val id: Int,
    val meaning: String,
    val text: String,
    val transliteration: String,
    val verse_number: String,
    val verse_order: Int,
    val word_meanings: String
)