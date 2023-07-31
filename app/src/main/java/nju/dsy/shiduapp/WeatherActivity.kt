package nju.dsy.shiduapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nju.dsy.shiduapp.weather.WeatherAdapter
import nju.dsy.shiduapp.weather.WeatherItem

class WeatherActivity : AppCompatActivity() {
    private val TAG = "WeatherActivity"
    private lateinit var weatherItems: List<WeatherItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        Log.v(TAG,"Weather activity onCreate ; time = ${System.currentTimeMillis()}")

        // 设置返回按钮的点击事件，返回到上一个Activity
        val btnBack: ImageButton = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            onBackPressed()
        }

        // 创建一个示例的天气数据列表
        generateWeatherSampleData()

        // 创建WeatherAdapter并设置给RecyclerView
        val weatherAdapter = WeatherAdapter(weatherItems)
        val rvFuture: RecyclerView = findViewById(R.id.rvFuture)
        rvFuture.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvFuture.adapter = weatherAdapter
    }

    private fun generateWeatherSampleData() {
        weatherItems = listOf(
            WeatherItem("当前", R.drawable.wth_sun, "33℃"),
            WeatherItem("15:00", R.drawable.wth_sun, "34℃"),
            WeatherItem("16:00", R.drawable.wth_sun, "36℃"),
            WeatherItem("17:00", R.drawable.wth_sun, "32℃"),
            WeatherItem("18:00", R.drawable.wth_sun, "30℃"),
            WeatherItem("19:00", R.drawable.wth_lightning, "28℃"),
            WeatherItem("20:00", R.drawable.wth_lightning, "26℃"),
            WeatherItem("21:00", R.drawable.wth_lightning, "25℃"),
            WeatherItem("22:00", R.drawable.wth_lightning, "24℃"),
            WeatherItem("23:00", R.drawable.wth_clouds, "23℃"),
            WeatherItem("00:00", R.drawable.wth_clouds, "22℃"),
            WeatherItem("01:00", R.drawable.wth_clouds, "21℃"),
            WeatherItem("02:00", R.drawable.wth_clouds, "20℃"),
            WeatherItem("03:00", R.drawable.wth_lightning, "19℃"),
            WeatherItem("04:00", R.drawable.wth_clouds, "22℃"),
            WeatherItem("05:00", R.drawable.wth_clouds, "26℃"),
            WeatherItem("06:00", R.drawable.wth_clouds, "28℃"),
            WeatherItem("07:00", R.drawable.wth_clouds, "30℃"),
            WeatherItem("08:00", R.drawable.wth_clouds, "32℃"),
            WeatherItem("09:00", R.drawable.wth_clouds, "34℃"),
            WeatherItem("10:00", R.drawable.wth_sun, "36℃"),
            WeatherItem("11:00", R.drawable.wth_sun, "32℃"),
            WeatherItem("12:00", R.drawable.wth_sun, "35℃"),
            WeatherItem("13:00", R.drawable.wth_sun, "36℃"),
            WeatherItem("14:00", R.drawable.wth_sun, "31℃"),
            )
    }

    override fun onBackPressed() {
        // 创建一个Intent，指定跳转到MainActivity
        val intent = Intent(this, MainActivity::class.java)

        // 启动MainActivity，并关闭当前的WeatherActivity
        startActivity(intent)
        finish()
    }

}