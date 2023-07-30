package nju.dsy.shiduapp

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.CharacterStyle
import android.text.style.UpdateAppearance
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide


class HomeFragment : Fragment() {
    val TAG = "HomeFragment"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")

//        if (arguments != null) {
//            val value = arguments?.getString("key")
//            Log.i(TAG, "activity传过来的值:$value")
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // load 天气的gif
        // 加载并播放 GIF 图片
        // 加载并播放 GIF 图片
        val weatherImage: ImageView = view.findViewById(R.id.weatherImage)
        Glide.with(this).asGif().load(R.drawable.weather_cloudy).into(weatherImage)

        val logoText: TextView = view.findViewById(R.id.textView2)
        setLogoTextStyle(logoText)
        return view
    }

    private fun setLogoTextStyle(textView: TextView) {
        val text = "S h i D u"

        // 创建一个SpannableString对象
        val spannable = SpannableString(text)

        // 创建一个LinearGradient对象，用于定义渐变色
        val gradient = LinearGradient(
            0f, 0f, textView.paint.measureText(text), 0f,
            intArrayOf(resources.getColor(R.color.start_color), resources.getColor(R.color.end_color)),
            null, Shader.TileMode.CLAMP
        )

        // 创建一个自定义的ShaderSpan，并将渐变色应用于其中
        val shaderSpan: CharacterStyle = object : CharacterStyle(), UpdateAppearance {
            override fun updateDrawState(tp: TextPaint) {
                tp.shader = gradient
            }
        }

        // 将ShaderSpan应用于文本
        spannable.setSpan(
            shaderSpan,
            0,
            text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // 将SpannableString对象设置给TextView
        textView.text = spannable
    }
}