package com.androidlearning.sunnyweather.ui.place

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidlearning.sunnyweather.MainActivity
import com.androidlearning.sunnyweather.R
import com.androidlearning.sunnyweather.ui.weather.WeatherActivity

class PlaceFragment : Fragment() {

    val viewModel by lazy {
        ViewModelProvider(this)[PlaceViewModel::class.java]
    }

    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onStart() {
        super.onStart()

        if (viewModel.isPlaceSaved()) {
            val place = viewModel.getSavedPlace()
            val intent = Intent(context, WeatherActivity::class.java).apply {
                putExtra("location_lngAndLat", place.location)
                putExtra("place_name", place.address)
            }
            startActivity(intent)
            activity?.finish()
            return
        }

        activity?.let {

            val mainActivity = it as MainActivity

            val searchPlaceEdit = mainActivity.findViewById<EditText>(R.id.searchPlaceEdit)
            val bgImageView = mainActivity.findViewById<ImageView>(R.id.bgImageView)

            val recyclerView = mainActivity.findViewById<RecyclerView>(R.id.recyclerView)

            val layoutManager = LinearLayoutManager(mainActivity)

            adapter = PlaceAdapter(this, viewModel.placeList)

            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager

            searchPlaceEdit.addTextChangedListener { editable ->
                val content = editable.toString()
                if (content.isNotEmpty()) {
                    viewModel.searchPlaces(content)
                } else {
                    recyclerView.visibility = View.GONE
                    bgImageView.visibility = View.VISIBLE
                    viewModel.placeList.clear()
                    adapter.notifyDataSetChanged()
                }
            }

            viewModel.placeLiveData.observe(mainActivity, Observer { result ->
                val places = result.getOrNull()
                if (places != null) {
                    recyclerView.visibility = View.VISIBLE
                    bgImageView.visibility = View.GONE
                    viewModel.placeList.clear()
                    viewModel.placeList.addAll(places)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(mainActivity, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
                    result.exceptionOrNull()?.printStackTrace()
                }
            })
        }
    }
}