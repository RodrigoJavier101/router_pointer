package com.rodrigo.javier.eox.hackermen.routerpointer.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rodrigo.javier.eox.hackermen.routerpointer.model.entities.DeliveryPoint

@Dao
interface RoutesDao {
    @Query("SELECT * FROM delivery_point_table")
    fun getPointsList(): LiveData<MutableList<DeliveryPoint>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(deliveryPoint: DeliveryPoint)

    @Delete
    suspend fun deleteItem(deliveryPoint: DeliveryPoint)
}