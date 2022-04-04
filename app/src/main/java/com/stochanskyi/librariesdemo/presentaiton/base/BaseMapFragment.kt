package com.stochanskyi.librariesdemo.presentaiton.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

abstract class BaseMapFragment : Fragment {

    constructor() : super()
    constructor(contentLayoutId: Int) : super(contentLayoutId)

    private var mapViewWeak: WeakReference<MapView>? = null
    private val mapView: MapView?
        get() = mapViewWeak?.get()

    abstract fun resolveMapView(): MapView?

    inline fun mapAction(crossinline action: (GoogleMap) -> Unit) {
        val map = resolveMapView() ?: return

        lifecycleScope.launch {
            action(map.awaitMap())
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMapView()

        mapView?.onCreate(savedInstanceState)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        mapView?.onDestroy()
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    @CallSuper
    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    private fun initMapView() {
        resolveMapView()?.let {
            mapViewWeak = WeakReference(it)
        }
    }
}