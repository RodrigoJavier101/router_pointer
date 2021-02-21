package com.rodrigo.javier.eox.hackermen.routerpointer.utilities

import com.rodrigo.javier.eox.hackermen.routerpointer.model.entities.RegionEntity

class ListaRegiones {
	companion object {
		
		val regionesList =
				arrayListOf<RegionEntity>(
						RegionEntity(1 ,
						             "Arica Parinacota" ,
						             "XV") ,
						RegionEntity(
								2 ,
								"Tarapacá" ,
								"I"
						            ) ,
						RegionEntity(
								3 ,
								"Antofagasta" ,
								" II "
						            ) ,
						RegionEntity(4 ,
						             "Atacama" ,
						             " III ") ,
						RegionEntity(5 ,
						             "Coquimbo" ,
						             "IV") ,
						RegionEntity(6 ,
						             "Valparaiso" ,
						             "V") ,
						RegionEntity(7 ,
						             "Metropolitana de Santiago" ,
						             "RM") ,
						RegionEntity(
								8 ,
								"Libertador General Bernardo O\'Higgins" ,
								"VI"
						            ) ,
						RegionEntity(
								9 ,
								"Maule" ,
								"VII"
						            ) ,
						RegionEntity(10 ,
						             "Biobío" ,
						             "VIII") ,
						RegionEntity(11 ,
						             "La Araucanía" ,
						             "IX") ,
						RegionEntity(12 ,
						             "Los Ríos" ,
						             "XIV") ,
						RegionEntity(
								13 ,
								"Los Lagos" ,
								"X"
						            ) ,
						RegionEntity(
								14 ,
								"Aisén del General Carlos Ibáñez del Campo" ,
								"XI"
						            ) ,
						RegionEntity(15 ,
						             "Magallanes y de la Antártica Chilena" ,
						             "XII")
				                         )
	}
}