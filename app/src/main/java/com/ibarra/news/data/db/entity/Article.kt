package com.ibarra.news.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ibarra.news.data.db.converter.DateConverter
import com.ibarra.news.data.remote.domain.ArticleRepository
import com.ibarra.news.data.remote.domain.ArticleSourceRepository
import java.util.*

@Entity(tableName = "articles")
@TypeConverters(DateConverter::class)
data class Article (
    @PrimaryKey(autoGenerate = false)  val url: String,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "urlToImage") val urlToImage: String?,
    @ColumnInfo(name = "publishedAt") val publishedAt: String?,
    @ColumnInfo(name = "content") val content: String?,
    @ColumnInfo(name = "created") val created: Date
){
    companion object {
        fun to(repository: ArticleRepository): Article {
            return Article(
                author = repository.author,
                title = repository.title,
                description = repository.description,
                url = repository.url,
                urlToImage = repository.urlToImage,
                publishedAt = repository.publishedAt,
                content = repository.content,
                created = Date()
            )
        }

        fun toList(repositories: List<ArticleRepository>?): List<Article>? {
            return repositories?.map { to(it) }
        }
    }
}