package com.rodrigo.javier.eox.hackermen.routerpointer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rodrigo.javier.eox.hackermen.routerpointer.model.database.RouteDatabase
import com.rodrigo.javier.eox.hackermen.routerpointer.model.managers.RouteDatabaseManager
import com.rodrigo.javier.eox.hackermen.routerpointer.model.database.RoutesDao
import com.rodrigo.javier.eox.hackermen.routerpointer.model.entities.DeliveryPointEntity
import kotlinx.coroutines.launch

class WholeRouteViewModel(application: Application) : AndroidViewModel(application) {
    var list: LiveData<MutableList<DeliveryPointEntity>> =
        MutableLiveData(
            mutableListOf()
        )

    var dao: RoutesDao

    init {
        val database = RouteDatabase.getDatabase(getApplication())
        dao = database.getDao()
        if (list.value.isNullOrEmpty()) {
            getListUsingViewModel()
    
        }
    }
    
    fun getListUsingViewModel() {
        list = RouteDatabaseManager(dao).allPoints
        viewModelScope.launch {
            list
        }
    }

    suspend fun insertInDatabaseUsingViewModel(deliveryPointEntity: DeliveryPointEntity) {
        RouteDatabaseManager(dao).insertDetailInDDBB(deliveryPointEntity)
    }

    suspend fun deleteInDatabaseUsingViewModel(deliveryPointEntity: DeliveryPointEntity) {
        RouteDatabaseManager(dao).deleteItemWithDatabaseManager(deliveryPointEntity)
    }
}