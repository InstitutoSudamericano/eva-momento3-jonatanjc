package com.example.evam3.service

import com.example.evam3.entity.Film
import com.example.evam3.repository.FilmRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class FilmService {
    @Autowired
    lateinit var filmRepository: FilmRepository

    fun list(): List<Film> {
        return filmRepository.findAll()
    }

    fun save(film: Film): Film {
        // Verificar si el título no está vacío o compuesto solo por espacios en blanco
        film.title?.takeIf { it.trim().isNotEmpty() }
            ?: throw IllegalArgumentException("El título de la película no debe estar vacío")

        return try {
            filmRepository.save(film)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }

    fun update(film: Film): Film {
        film.id?.let { filmRepository.findById(it) }
            ?: throw NoSuchElementException("ID de película no encontrado")

        return try {
            filmRepository.save(film)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar la película", ex)
        }
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = id?.let { filmRepository.findById(it) }
                ?: throw Exception("ID no existe")
            filmRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}
