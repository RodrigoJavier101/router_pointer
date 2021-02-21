package com.rodrigo.javier.eox.hackermen.routerpointer.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.rodrigo.javier.eox.hackermen.routerpointer.R
import com.rodrigo.javier.eox.hackermen.routerpointer.databinding.FragmentWholeRouteMapBinding
import com.rodrigo.javier.eox.hackermen.routerpointer.viewmodels.WholeRouteViewModel
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
class SinglePointMapFragment : WholeRouteMapFragment() {

    private var _binding: FragmentWholeRouteMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private lateinit var mMap: GoogleMap
    private var mapReady = false

    @InternalCoroutinesApi
    private lateinit var model: WholeRouteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWholeRouteMapBinding.inflate(layoutInflater)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            mMap = googleMap
            mapReady = true
            updateMap()
        }

        return binding.root
    }

    private fun updateMap() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController(view)

        activity.let {
            model = ViewModelProvider(this).get(WholeRouteViewModel::class.java)
        }
    }
}