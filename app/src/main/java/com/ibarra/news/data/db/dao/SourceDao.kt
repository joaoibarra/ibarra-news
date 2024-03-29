package com.ibarra.news.data.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.ibarra.news.data.db.entity.Source

@Dao
interface SourceDao {
    @Query("SELECT * FROM sources ORDER BY created ASC")
    fun findAll(): DataSource.Factory<Int, Source>

    @Query("SELECT * FROM sources WHERE id LIKE :sourceId ORDER BY created ASC")
    fun findById(sourceId: String): Source

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(source: Source)

    @Delete
    fun delete(source: Source)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sources: List<Source>?)
}