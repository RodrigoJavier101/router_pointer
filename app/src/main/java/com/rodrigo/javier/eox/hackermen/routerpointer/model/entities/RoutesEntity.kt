package com.rodrigo.javier.eox.hackermen.routerpointer.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodrigo.javier.eox.hackermen.routerpointer.utilities.StringStatic

@Entity(tableName = StringStatic.ROUTE_TABLE)
data class RoutesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
//    val date: Float = 0f
)
