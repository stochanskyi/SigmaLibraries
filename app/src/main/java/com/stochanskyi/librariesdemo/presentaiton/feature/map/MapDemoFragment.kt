package com.stochanskyi.librariesdemo.presentaiton.feature.map

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.MapStyleOptions
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.app.appComponent
import com.stochanskyi.librariesdemo.databinding.FragmentMapDemoBinding
import com.stochanskyi.librariesdemo.presentaiton.base.BaseMapFragment
import com.stochanskyi.librariesdemo.presentaiton.feature.map.models.CountryBoundViewData
import com.stochanskyi.librariesdemo.presentaiton.feature.map.transformer.BoundGeometryToPolygonOptionsTransformer
import com.stochanskyi.librariesdemo.presentaiton.utils.ViewModelFactory
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import javax.inject.Inject

class MapDemoFragment : BaseMapFragment(R.layout.fragment_map_demo) {

    private val viewBinding: FragmentMapDemoBinding by viewBinding(FragmentMapDemoBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MapDemoViewModel by viewModels { viewModelFactory }

    private val boundTransformer = BoundGeometryToPolygonOptionsTransformer()

    override fun resolveMapView(): MapView = viewBinding.map

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()

    }

    private fun initViews() {
        mapAction {
            setupMapStyle(it)
        }
    }

    private fun initObservers() {
        viewModel.countryBoundsLiveData.observe(viewLifecycleOwner) { bounds ->
            mapAction { drawCountryBounds(it, bounds) }
        }
    }

    private fun drawCountryBounds(googleMap: GoogleMap, countryBounds: List<CountryBoundViewData>) {
        countryBounds.forEach {
            drawCountryBound(googleMap, it)
        }
    }

    private fun drawCountryBound(map: GoogleMap, countryBound: CountryBoundViewData) {
        val polygonOptions = countryBound.geometry.transform(boundTransformer)

        polygonOptions.forEach { polygon ->
            polygon
                .fillColor(requireContext().getColor(countryBound.color))
                .strokeWidth(1f)
                .let { map.addPolygon(it) }
        }
    }

    private fun setupMapStyle(map: GoogleMap) {
        val style = MapStyleOptions.loadRawResourceStyle(
            context ?: return,
            R.raw.map_style
        )

        map.setMapStyle(style)
    }

}