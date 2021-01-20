package com.rodrigo.javier.eox.hackermen.routerpointer.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rodrigo.javier.eox.hackermen.routerpointer.model.entities.DeliveryPoint


@Database(entities = [DeliveryPoint::class], version = 2, exportSchema = false)
abstract class RouteDatabase : RoomDatabase() {
    abstract fun getDao(): RoutesDao

    companion object {
        private var database: RouteDatabase? = null

        fun getDatabse(context: Context): RouteDatabase {
            if (database == null) {
                synchronized(this) {
                    database =
                        Room.databaseBuilder(context, RouteDatabase::class.java, "route_database")
//                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return database!!
        }
    }
}