package com.example.evam3.entity

import jakarta.persistence.*
import java.util.Date

@Entity
class Film {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", updatable = false)
    var id: Long? = null

    @Column(name = "title")
    var title: String? = null

    @Column(name = "duration")
    var duration: Long? = null

    @Column(name = "country") // Agregada la columna country
    var country: String? = null

    @Column(name = "puntuation") // Corregido el nombre de la columna
    var punctuation: Double? = null

    @Column(name = "release_date")
    var releaseDate: Date? = null

    @Column(name = "gender") // Corregido el nombre de la columna
    var gender: String? = null

    @Column(name = "presale") // Agregada la columna presale
    var presale: Int? = null

    @Column(name = "investment") // Agregada la columna investment
    var investment: Int? = null
}
