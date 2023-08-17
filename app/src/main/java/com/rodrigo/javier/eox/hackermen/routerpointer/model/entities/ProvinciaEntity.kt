package com.rodrigo.javier.eox.hackermen.routerpointer.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodrigo.javier.eox.hackermen.routerpointer.utilities.StringStatic

@Entity(tableName = StringStatic.PROVINCIA_TABLE)
data class ProvinciaEntity(
    @PrimaryKey(autoGenerate = true)
    var provinviaId: Int = 0,
    var provinciaNombre: String = "",
    var regionId: Int = 0
)
