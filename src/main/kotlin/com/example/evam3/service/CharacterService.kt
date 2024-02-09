package com.example.evam3.service

import com.example.evam3.entity.Character
import com.example.evam3.repository.CharacterRepository
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class CharacterService {
    @Autowired
    lateinit var sceneRepository: SceneRepository
    @Autowired
    lateinit var characterRepository: CharacterRepository

    fun list ():List<Character>{
        return characterRepository.findAll()
    }
    fun save(character: Character): Character {
        try {
            character.actorName?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El nombre del actor no debe ser vacío")
            val scene = sceneRepository.findById(character.sceneId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Scene not found")

            if (character.cost!! > scene.budget!!) {
                throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Character cost exceeds scene budget"
                )
            }

            return characterRepository.save(character)
        } catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, ex.message, ex
            )
        }
    }
    fun update(character: Character): Character {
        try {
            characterRepository.findById(character.id)
                ?: throw Exception("ID no existe")

            return characterRepository.save(character)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = characterRepository.findById(id)
                ?: throw Exception("ID no existe")
            characterRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

}