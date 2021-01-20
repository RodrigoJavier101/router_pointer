package com.rodrigo.javier.eox.hackermen.routerpointer.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rodrigo.javier.eox.hackermen.routerpointer.model.database.RouteDatabase
import com.rodrigo.javier.eox.hackermen.routerpointer.model.database.RouteDatabaseManager
import com.rodrigo.javier.eox.hackermen.routerpointer.model.database.RoutesDao
import com.rodrigo.javier.eox.hackermen.routerpointer.model.entities.DeliveryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WholeRouteViewModel(application: Application) : AndroidViewModel(application) {
    var list: LiveData<MutableList<DeliveryPoint>> =
        MutableLiveData(
            mutableListOf()
        )

    var dao: RoutesDao

    init {
        val database = RouteDatabase.getDatabse(getApplication())
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

    suspend fun insertInDatabaseUsingViewModel(deliveryPoint: DeliveryPoint) {
        RouteDatabaseManager(dao).insertDetailInDDBB(deliveryPoint)
    }

    suspend fun deleteInDatabaseUsingViewModel(deliveryPoint: DeliveryPoint) {
        RouteDatabaseManager(dao).deleteItemWithDatabaseManager(deliveryPoint)
    }
}