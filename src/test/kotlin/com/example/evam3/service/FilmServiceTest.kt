package com.example.evam3.service

import com.example.evam3.entity.Film
import com.example.evam3.repository.FilmRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File
import java.util.Optional

@SpringBootTest
internal class FilmServiceTest {
    @InjectMocks
    lateinit var filmService: FilmService

    @Mock
    lateinit var filmRepository: FilmRepository

    val jsonStringFilm = File("./src/test/resources/film.json").readText(Charsets.UTF_8)
    val filmMock = Gson().fromJson(jsonStringFilm, Film::class.java)


    @Test
    fun savefilmCorrect(){
        Mockito.`when`(filmRepository.save(Mockito.any(Film::class.java))).thenReturn(filmMock)
        val response = filmService.save(filmMock)
        Assertions.assertEquals(response.id, filmMock.id)
    }


    @Test
    fun savefilmWhenTitleIsBlank(){

        Assertions.assertThrows(Exception::class.java) {
            filmMock.apply { title=" "}
            Mockito.`when`(filmRepository.save(Mockito.any(Film::class.java))).thenReturn(filmMock)
            filmService.save(filmMock)

        }
    }
    //NO FUNCIONA ESTE TEST
    @Test
    fun updatefilmCorrect() {
        val updatedfilm = Film(/* Updated film details */)
        Mockito.`when`(filmRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(filmMock))
        Mockito.`when`(filmRepository.save(Mockito.any(Film::class.java))).thenReturn(updatedfilm)

        try {
            val response = filmService.update(updatedfilm)
            Assertions.assertEquals(response.id, updatedfilm.id)
        } catch (ex: Exception) {
            Assertions.fail("Exception thrown during update operation: ${ex.message}")
        }

        Mockito.verify(filmRepository, Mockito.times(1)).findById(updatedfilm.id)
        Mockito.verify(filmRepository, Mockito.times(1)).save(Mockito.any(Film::class.java))
    }
}