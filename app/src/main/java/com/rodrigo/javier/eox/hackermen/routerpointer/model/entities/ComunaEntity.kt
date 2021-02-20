package com.rodrigo.javier.eox.hackermen.routerpointer.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodrigo.javier.eox.hackermen.routerpointer.utilities.StringStatic

@Entity(tableName = StringStatic.COMUNA_TABLE)
data class ComunaEntity(
    @PrimaryKey(autoGenerate = true)
    var comunaId: Int = 0,
    var comunaNombre: String = "",
    var provinciaId: Int = 0
)
