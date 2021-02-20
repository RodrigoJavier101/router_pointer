package com.rodrigo.javier.eox.hackermen.routerpointer.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rodrigo.javier.eox.hackermen.routerpointer.model.entities.*

@Dao
interface RoutesDao {

    /*Routes*/
    @Query("select * from route_table")
    fun getRoutesList(): LiveData<MutableList<RoutesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRouteDetail(routeDetail: RoutesEntity)

    /*Delivery Point*/
    @Query("SELECT * FROM delivery_point_table")
    fun getDeliveryList(): LiveData<MutableList<DeliveryPointEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeliveryDetail(deliveryPointEntity: DeliveryPointEntity)

    @Delete
    suspend fun deleteDeliveryItem(deliveryPointEntity: DeliveryPointEntity)

    /*Comuna*/
    @Query("select * from comuna_table")
    fun getComunaList(): LiveData<MutableList<ComunaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComunaList(comunaList: MutableList<ComunaEntity>)

    /*Provincia*/
    @Query("select * from provincia_table")
    fun getProvinciaList(): LiveData<MutableList<ProvinciaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProvinciaList(comunaList: MutableList<ProvinciaEntity>)

    /*Region*/
    @Query("select * from region_table")
    fun getRegionList(): LiveData<MutableList<RegionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegionList(comunaList: MutableList<RegionEntity>)

}