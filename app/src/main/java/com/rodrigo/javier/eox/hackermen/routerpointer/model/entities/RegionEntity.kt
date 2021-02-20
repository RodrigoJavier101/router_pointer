package com.rodrigo.javier.eox.hackermen.routerpointer.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodrigo.javier.eox.hackermen.routerpointer.utilities.StringStatic

@Entity(tableName = StringStatic.REGION_TABLE)
data class RegionEntity(
    @PrimaryKey(autoGenerate = true)
    var regionId: Int = 0,
    var regionNombre: String = "",
    var regionOrdinal: String = ""
)
