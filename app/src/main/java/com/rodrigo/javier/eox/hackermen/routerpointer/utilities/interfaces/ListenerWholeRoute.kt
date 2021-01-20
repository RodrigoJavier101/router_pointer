package com.rodrigo.javier.eox.hackermen.routerpointer.utilities.interfaces

import com.rodrigo.javier.eox.hackermen.routerpointer.model.entities.DeliveryPoint

interface ListenerWholeRoute {
    fun viewTouchedShort(
        deliveryPoint: DeliveryPoint,
        position: Int
    )

    fun viewTouchedLong(
        deliveryPoint: DeliveryPoint,
        id: Int
    )


}