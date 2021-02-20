package com.rodrigo.javier.eox.hackermen.routerpointer.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rodrigo.javier.eox.hackermen.routerpointer.model.entities.*
import com.rodrigo.javier.eox.hackermen.routerpointer.utilities.StringStatic
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [
        RoutesEntity::class,
        DeliveryPointEntity::class,
        RegionEntity::class,
        ProvinciaEntity::class,
        ComunaEntity::class
    ],
    version = 4,
    exportSchema = true
)
abstract class RouteDatabase : RoomDatabase() {

    abstract fun getDao(): RoutesDao

    companion object {

        @Volatile
        private var databaseInstance: RouteDatabase? =
            null


        fun getDatabase(context: Context): RouteDatabase {
            if (databaseInstance == null) {
                synchronized(this) {
                    databaseInstance =
                        Room
                            .databaseBuilder(
                                context,
                                RouteDatabase::class.java,
                                StringStatic.ROUTE_DATABASE
                            )
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return databaseInstance!!
//            return databaseInstance ?: synchronized(this) {
//                databaseInstance ?: Room.databaseBuilder(
//                    context, RouteDatabase::class.java,
//                    StringStatic.ROUTE_DATABASE
//                ).fallbackToDestructiveMigration().build()
//            }.also { databaseInstance = it }
        }

        private val sLock = Any()


//        internal fun close() {
//            synchronized(sLock) {
//                databaseInstance?.close().also {
//                    databaseInstance = null
//                }
//            }
//        }
    }
}