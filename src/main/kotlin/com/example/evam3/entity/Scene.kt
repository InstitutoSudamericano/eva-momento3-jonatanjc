package com.example.evam3.entity

import jakarta.persistence.*

@Entity
@Table (name="scene")
class Scene {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var description: String? = null
    var budget: Long? = null
    @Column(name="makeup_cost")
    var makeupCost: Double? = null
    @Column(name="number_of_films")
    var numberOfFilms: Long? = null
    @Column(name="film_id")
    var filmId: Long? = null
}