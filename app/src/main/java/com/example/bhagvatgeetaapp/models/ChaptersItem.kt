package com.example.bhagvatgeetaapp.models

data class ChaptersItem(
    val chapter_number: Int,
    val chapter_summary: String,
    val id: Int,
    val image_name: String,
    val name: String,
    val name_meaning: String,
    val name_translation: String,
    val name_transliterated: String,
    val verses_count: Int
)