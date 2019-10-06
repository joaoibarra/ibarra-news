package com.ibarra.news.data.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.ibarra.news.data.db.entity.Article


@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles ORDER BY created ASC")
    fun findAll(): DataSource.Factory<Int, Article>

    @Query("SELECT * FROM articles WHERE sourceId LIKE :sourceId ORDER BY created ASC")
    fun findBySourceId(sourceId: String): DataSource.Factory<Int, Article>

    @Query("SELECT * FROM articles WHERE url LIKE :url ORDER BY created ASC")
    fun findByUrl(url: String): Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article)

    @Delete
    fun delete(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sources: List<Article>?)
}