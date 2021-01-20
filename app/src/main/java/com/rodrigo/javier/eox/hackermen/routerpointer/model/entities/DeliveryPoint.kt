package com.rodrigo.javier.eox.hackermen.routerpointer.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "delivery_point_table")
data class DeliveryPoint(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var idRoute: Int = 0,
    var name: String = "NAME DEFAULT",
    var lastName1: String = "LAST NAME DEFAUL 1",
    var lastName2: String = "LAST NAME DEFAUL 2",
    var county: String = "COUNTY DEFAULT",
    var street: String = "CALLE DEFAULT",
    var houseNumber: Int = 0,
    var phone: String = "PHONE DEFAULT",
    var time: Float = 0f,
    var state: Int = 0
)
