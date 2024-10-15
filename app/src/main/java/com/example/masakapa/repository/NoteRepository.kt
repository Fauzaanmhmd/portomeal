package com.example.masakapa.repository

import com.example.masakapa.data.dao.NoteDao
import com.example.masakapa.data.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(val noteDao: NoteDao) {
    fun getAll() = noteDao.getAll()

    suspend fun insert(note: Note) = noteDao.insert(note)
    suspend fun delete(note: Note) = noteDao.delete(note)
    suspend fun update(note: Note) = noteDao.update(note)
     fun getNoteById(id: Int) = noteDao.getNoteById(id)
}