package com.rodrigo.javier.eox.hackermen.routerpointer.model.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rodrigo.javier.eox.hackermen.routerpointer.model.entities.DeliveryPoint

class RouteDatabaseManager(private val dao: RoutesDao) {
    var allPoints: LiveData<MutableList<DeliveryPoint>> =
        MutableLiveData<MutableList<DeliveryPoint>>(mutableListOf())

    init {
        allPoints = dao.getPointsList()
    }

    /*suspend fun getListUsingDtabaseManager():LiveData<MutableList<DeliveryPoint>>{
         return dao.getPointsList()
    }*/

    suspend fun insertDetailInDDBB(deliveryPoint: DeliveryPoint) {
        dao.insertDetail(
            deliveryPoint
        )
    }

     suspend fun deleteItemWithDatabaseManager(deliveryPoint: DeliveryPoint) {
         dao.deleteItem(deliveryPoint)
     }
}