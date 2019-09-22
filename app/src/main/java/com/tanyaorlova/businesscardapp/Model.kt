package com.tanyaorlova.businesscardapp

import java.util.Date

class NewsItem(
    val title: String,
    val imageUrl: String,
    val category: Category,
    val publishDate: Date,
    val previewText: String,
    val fullText: String
)

class Category(val id: Int, val name: String)
