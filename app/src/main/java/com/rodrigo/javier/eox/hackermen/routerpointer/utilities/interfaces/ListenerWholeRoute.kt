package com.rodrigo.javier.eox.hackermen.routerpointer.utilities.interfaces

import com.rodrigo.javier.eox.hackermen.routerpointer.model.entities.DeliveryPointEntity

interface ListenerWholeRoute {
	
	fun viewTouchedShort(deliveryPointEntity:DeliveryPointEntity, position:Int)
	
	fun viewTouchedLong(deliveryPointEntity:DeliveryPointEntity, id:Int)
	
}