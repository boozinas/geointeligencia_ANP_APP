package com.cicipn.geointeligencia_anp.ui.home

import org.osmdroid.config.Configuration;
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cicipn.geointeligencia_anp.R
import org.osmdroid.config.Configuration.getInstance
import org.osmdroid.library.BuildConfig
import org.osmdroid.tileprovider.BitmapPool.getInstance
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapController
import org.osmdroid.views.MapView

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private  lateinit var osmRL: MapView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        // val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            // textView.text = it
        })

        osmRL = root.findViewById(R.id.mapView)
        osmRL.tileProvider.tileSource = TileSourceFactory.MAPNIK
        osmRL.setMultiTouchControls(true)

        val ctx = requireActivity().applicationContext
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        val controller: MapController = osmRL.controller as MapController

        val geoPoint = GeoPoint(19.5020228, -99.0264017)

        controller.setCenter(geoPoint)
        controller.setZoom(18)



        return root
    }
}