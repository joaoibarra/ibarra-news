package com.ibarra.news.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ibarra.news.data.db.converter.DateConverter
import com.ibarra.news.data.remote.domain.SourceRepository
import java.util.*

@Entity(tableName = "sources")
@TypeConverters(DateConverter::class)
data class Source(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "category") val category: String?,
    @ColumnInfo(name = "language") val language: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "created") val created: Date
){
    companion object {
        infix fun to(repository: SourceRepository): Source {
            return Source(
                id = repository.idName,
                name = repository.name,
                description = repository.description,
                url = repository.url,
                category = repository.category,
                language = repository.language,
                country = repository.country,
                created = Date()
            )
        }

        fun toList(repositories: List<SourceRepository>?): List<Source>? {
            return repositories?.map { to(it) }
        }
    }
}
