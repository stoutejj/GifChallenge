package com.jamal.giffy.ui.imagedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jamal.giffy.R
import com.jamal.giffy.data.model.GiffyImage
import kotlinx.android.synthetic.main.fragment_image_detail.*

class ImageDetailFragment : Fragment() {
    companion object {
        private const val KEY_GIFFY_IMAGE = "giffy_image"
        fun newInstance(giffyImage: GiffyImage): ImageDetailFragment {
            return ImageDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GIFFY_IMAGE, giffyImage)
                }
            }
        }
    }

    private var giffyImage: GiffyImage? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        giffyImage = arguments?.getParcelable(KEY_GIFFY_IMAGE)
        giffyImage?.let { giffyImage ->
            Glide.with(this).load(giffyImage.images.downSized.url).into(image)
            tv_display_name.text = giffyImage.user?.displayName ?: "Not Found"
            tv_rating.text = giffyImage.rating ?: "No Rating Found"
            tv_source.text = giffyImage.source ?: "No Source Found"
        }
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.supportFragmentManager?.popBackStack()
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }
}