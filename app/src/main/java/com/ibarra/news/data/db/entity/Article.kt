package com.ibarra.news.data.db.entity

import androidx.room.*
import com.ibarra.news.data.db.converter.DateConverter
import com.ibarra.news.data.remote.domain.ArticleRepository
import java.text.SimpleDateFormat
import java.util.*

@Entity(
    tableName = "articles",
    foreignKeys = arrayOf(ForeignKey(
        entity = Source::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("sourceId"))
    ),
    indices = arrayOf(Index(value = ["sourceId", "url"], unique = true))
)

@TypeConverters(DateConverter::class)
data class Article(
    @PrimaryKey(autoGenerate = false) val url: String,
    @ColumnInfo(name = "sourceId") val sourceId: String,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "urlToImage") val urlToImage: String?,
    @ColumnInfo(name = "publishedAt") val publishedAt: Date?,
    @ColumnInfo(name = "content") val content: String?,
    @ColumnInfo(name = "created") val created: Date
){
    companion object {
        fun to(repository: ArticleRepository): Article {
            return Article(
                sourceId = repository.source.id,
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

    fun formatDate(): String? {
        return publishedAt?.let{
            val format = SimpleDateFormat("dd/MM/yyy hh:mm", Locale.getDefault())
            format.format(it).toString()
        }?:""
    }
}