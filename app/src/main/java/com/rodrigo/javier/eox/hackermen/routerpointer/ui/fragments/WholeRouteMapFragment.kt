package com.rodrigo.javier.eox.hackermen.routerpointer.ui.fragments

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rodrigo.javier.eox.hackermen.routerpointer.R
import com.rodrigo.javier.eox.hackermen.routerpointer.databinding.DialogLayoutBinding
import com.rodrigo.javier.eox.hackermen.routerpointer.databinding.FragmentWholeRouteMapBinding
import com.rodrigo.javier.eox.hackermen.routerpointer.model.entities.DeliveryPointEntity
import com.rodrigo.javier.eox.hackermen.routerpointer.ui.adapters.DeliveryListingAdapter
import com.rodrigo.javier.eox.hackermen.routerpointer.utilities.ListaComunas
import com.rodrigo.javier.eox.hackermen.routerpointer.utilities.ListaProvincias
import com.rodrigo.javier.eox.hackermen.routerpointer.utilities.ListaRegiones
import com.rodrigo.javier.eox.hackermen.routerpointer.utilities.interfaces.ListenerWholeRoute
import com.rodrigo.javier.eox.hackermen.routerpointer.viewmodels.WholeRouteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.stream.Collectors

open class WholeRouteMapFragment:Fragment(), ListenerWholeRoute {
	
	private var _binding:FragmentWholeRouteMapBinding? = null
	private val binding get() = _binding !!
	private var _bindingDialog:DialogLayoutBinding? = null
	private val bindingDialog get() = _bindingDialog
	private lateinit var adapter:DeliveryListingAdapter
	private lateinit var model:WholeRouteViewModel
	
	//    private var soundPool: SoundPool? = null
	//    private var sonido: Int = -1
	private lateinit var navController:NavController
	
	override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?):View? {
		_binding = FragmentWholeRouteMapBinding.inflate(layoutInflater)
		
		model = ViewModelProvider(this).get(WholeRouteViewModel::class.java)
		
		adapter = DeliveryListingAdapter(
			mutableListOf(),
			requireContext(),
			this, //this
		)
		
		initRecyclerView()
		
		model.list.observe(viewLifecycleOwner, Observer {
			// CoroutineScope(Dispatchers.IO).launch {
			adapter.setProductosEnLaVista(it)
			// }
		})
		//          setSoundEfect()
		ItemTouchHelper(object:
			ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
			override fun onMove(recyclerView:RecyclerView, viewHolder:RecyclerView.ViewHolder,
				target:RecyclerView.ViewHolder):Boolean {
				return false
			}
			
			override fun onSwiped(viewHolder:RecyclerView.ViewHolder, direction:Int) {
				CoroutineScope(Dispatchers.IO).launch {
					model.deleteInDatabaseUsingViewModel(adapter.getProductos(viewHolder.adapterPosition))
				}
				Toast.makeText(requireContext(), "deleted!!", Toast.LENGTH_SHORT).show()
			}
		}).attachToRecyclerView(binding.recyclerMainPage)
		
		
		return binding.root
	}
	
	private fun initRecyclerView() {
		binding.recyclerMainPage.hasFixedSize()
		binding.recyclerMainPage.layoutManager = LinearLayoutManager(requireContext())
		binding.recyclerMainPage.adapter = adapter
	}
	
	@SuppressLint("RestrictedApi")
	override fun onViewCreated(view:View, savedInstanceState:Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		navController = Navigation.findNavController(view)
		
		binding.btnGuardarRutaCompleta.setOnClickListener {
			navController.navigate(R.id.action_wholeRouteMapFragment_to_routesArchivesFragment2)
		}
		
		binding.btnNuevoPunto.setOnClickListener {
			_bindingDialog = DialogLayoutBinding.inflate(layoutInflater)
			val dialog = BottomSheetDialog(requireContext());
			val view = _bindingDialog
			val close = view !!.ivClose
			val btn = view.btnInsertarEnLista
			val regionesList = ListaRegiones.regionesList.stream().map { t ->
				t.regionNombre
			}.collect(Collectors.toList()) as ArrayList
			val provinciasList = ListaProvincias.provinciasList.stream().map { t ->
				t.provinciaNombre
			}.collect(Collectors.toList()) as ArrayList
			val comunasList = ListaComunas.comunasList.stream().map { t ->
				t.comunaNombre
			}.collect(Collectors.toList()) as ArrayList
			var arrayAdapterRegiones =
				ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, regionesList)
			var arrayAdapterProvincias =
				ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, provinciasList)
			var arrayAdapterComunas =
				ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, comunasList)
			// arrayAdapterRegiones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
			with(bindingDialog !!) {
				spinnerRegiones.adapter = arrayAdapterRegiones
				spinnerProvincias.adapter = arrayAdapterProvincias
				spinnerComunas.adapter = arrayAdapterComunas
				
				spinnerRegiones.setSelection(0, true)
				spinnerProvincias.setSelection(0, false)
				spinnerComunas.setSelection(0, false)
				// onItemSelectedListener = this@MainActivity
				spinnerRegiones.prompt = "Region"
				spinnerProvincias.prompt = "Provincia"
				spinnerComunas.prompt = "Comuna"
				
				spinnerRegiones.gravity = android.view.Gravity.CENTER
				spinnerProvincias.gravity = android.view.Gravity.CENTER
				spinnerComunas.gravity = android.view.Gravity.CENTER
			}
			
			close.setOnClickListener {
				dialog.dismiss()
			}
			var deliveryPoint = DeliveryPointEntity()
			
			btn.setOnClickListener {
				deliveryPoint.street = bindingDialog !!.spinnerRegiones.toString()
				deliveryPoint.phone = bindingDialog !!.spinnerComunas.toString()
				if (deliveryPoint.street.isNullOrBlank()) {
					Toast.makeText(requireContext(), "los campos no deben estar vacíos.", Toast.LENGTH_SHORT)
						.show()
				} else {
					CoroutineScope(Dispatchers.IO).launch {
						saveInDDBB(deliveryPoint)
						dialog.dismiss()
					}
				}
			}
			dialog.setCancelable(false)
			dialog.setContentView(view.root)
			dialog.show()
		}
		
	}
	
	private suspend fun saveInDDBB(deliveryPointEntity:DeliveryPointEntity) {
		model.insertInDatabaseUsingViewModel(deliveryPointEntity)
	}
	
	override fun viewTouchedShort(deliveryPointEntity:DeliveryPointEntity, position:Int) {
		Toast.makeText(requireContext(), "Short", Toast.LENGTH_SHORT).show()
	}
	
	override fun viewTouchedLong(deliveryPointEntity:DeliveryPointEntity, id:Int) {
		generatePhoneCall()
	}
	
	private fun generatePhoneCall() {
		//        val bind : Ph
		val dialogView = layoutInflater.inflate(R.layout.phonecall_dialog, null)
		val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
			.setNegativeButton("Cortar llamada") { dialog:DialogInterface, _:Int -> dialog.dismiss() }
		dialogBuilder.create().show()
	}
}