package com.example.taskstodo.di

import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.taskstodo.data.TaskDao
import com.example.taskstodo.data.TaskRoomDatabase
import com.example.taskstodo.domain.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) =
        TaskRoomDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideTaskDao(database: TaskRoomDatabase) = database.taskDao()

    @Singleton
    @Provides
    fun provideRepository(taskDao: TaskDao) = TaskRepository(taskDao)

}