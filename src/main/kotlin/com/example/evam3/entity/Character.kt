package com.example.evam3.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="character")
class Character {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var name: String? = null
    var age: Long? = null
    var description: String? = null
    @Column(name="actor_name")
    var actorName: String? = null
    var cost: Double? = null
    @Column(name="scene_id")
    var sceneId: Long? = null
}
