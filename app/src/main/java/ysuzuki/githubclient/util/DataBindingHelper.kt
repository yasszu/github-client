package ysuzuki.githubclient.util

import androidx.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by Yasuhiro Suzuki on 2016/12/05.
 *
 */
object DataBindingHelper {

    @JvmStatic
    @BindingAdapter("imageUrl", "placeholder")
    fun loadImage(view: ImageView, url: String?, placeholder: Drawable) {
        if (url.isNullOrBlank()) {
            view.setImageDrawable(placeholder)
        } else {
            Picasso.get().load(url).error(placeholder).into(view)
        }
    }

}
