package com.rodrigo.javier.eox.hackermen.routerpointer.model.managers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rodrigo.javier.eox.hackermen.routerpointer.model.database.RoutesDao
import com.rodrigo.javier.eox.hackermen.routerpointer.model.entities.DeliveryPointEntity

class RouteDatabaseManager(private val dao: RoutesDao) {
    var allPoints: LiveData<MutableList<DeliveryPointEntity>> =
        MutableLiveData<MutableList<DeliveryPointEntity>>(mutableListOf())

    init {
        allPoints = dao.getDeliveryList()
    }

    /*suspend fun getListUsingDtabaseManager():LiveData<MutableList<DeliveryPointEntity>>{
         return dao.getPointsList()
    }*/

    suspend fun insertDetailInDDBB(deliveryPointEntity: DeliveryPointEntity) {
        dao.insertDeliveryDetail(
            deliveryPointEntity
        )
    }

     suspend fun deleteItemWithDatabaseManager(deliveryPointEntity: DeliveryPointEntity) {
         dao.deleteDeliveryItem(deliveryPointEntity)
     }
}