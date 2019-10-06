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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(source: Article)

    @Delete
    fun delete(source: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sources: List<Article>?)
}