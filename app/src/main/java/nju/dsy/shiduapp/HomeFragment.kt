package nju.dsy.shiduapp

import android.content.ContentValues
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import nju.dsy.shiduapp.news.News
import nju.dsy.shiduapp.news.NewsAdapter
import nju.dsy.shiduapp.news.NewsDatabaseHelper


class HomeFragment : Fragment() {
    val TAG = "HomeFragment"

    private lateinit var newsDatabaseHelper: NewsDatabaseHelper

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

        // 设置渐变色的ShiDu
        val logoText: TextView = view.findViewById(R.id.textView2)
        setLogoTextStyle(logoText)

        // 加载新闻列表的数据，先获取MainActivity的实例，再访问MainActivity的newsDatabaseHelper变量
        // 获取 MainActivity 的实例
        val mainActivity = requireActivity() as MainActivity
        // 访问 MainActivity 的 newsDatabaseHelper 变量
        newsDatabaseHelper = mainActivity.newsDatabaseHelper

        // 插入样例数据
//        insertSampleData()

        // 获取数据并打印，看看是否成功
//        printNews()

        // 初始化 RecyclerView 和 NewsAdapter
        val recyclerView: RecyclerView = view.findViewById(R.id.newsList)
        val newsList = getAllNews()
        val newsAdapter = NewsAdapter(newsList)

        // 设置 RecyclerView 的布局管理器和适配器
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = newsAdapter

        return view

    }

    private fun printNews() {
        val newsList = getAllNews()
        for (news in newsList) {
            Log.i(TAG, news.toString())
        }
    }
    private fun insertSampleData() {
        // 加载新闻列表的数据
        // 创建一个News对象
        // 不需要id
        val news1 = News(
            0,
            "贵州村超总决赛1",
            "https://s2.loli.net/2023/07/30/xrbu6MHvVj5inST.png",
            "循着时间的轨迹，我们能深切地感受到，发源于贵州苗乡侗寨的“民间足球赛”，在保持体育运动激烈对抗特征的同时，也兼具“乡土味”“趣味性”“民族风”的独特魅力。",
            "https://www.toutiao.com/article/7261396962065367589/?log_from=1f7f9bdb167ea_1690722565213",
        )
        val news2 = News(
            0,
            "贵州村超总决赛2",
            "https://s2.loli.net/2023/07/30/FMYkXwPKlDQuyqz.png",
            "29日晚“村超”决赛现场，榕江县车江一村与忠诚村展开了冠军争夺。比赛开场不到3分钟，双方便各攻入一球，决赛的气氛瞬间被点燃。",
            "https://www.toutiao.com/article/7261396962065367589/?log_from=1f7f9bdb167ea_1690722565213",
        )
        insertNews(news1)
        insertNews(news2)
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

    // 插入新闻数据
    private fun insertNews(news: News) {

        val db = newsDatabaseHelper.writableDatabase

        val values = ContentValues().apply {
            put(NewsDatabaseHelper.COLUMN_TITLE, news.title)
            put(NewsDatabaseHelper.COLUMN_IMAGE_URL, news.imageUrl)
            put(NewsDatabaseHelper.COLUMN_SUMMARY, news.summary)
            put(NewsDatabaseHelper.COLUMN_URL, news.url)
        }

        val newRowId = db.insert(NewsDatabaseHelper.TABLE_NAME, null, values)
        db.close()
    }

    // 获取新闻列表
    private fun getAllNews(): List<News> {
        Log.i(TAG, "getAllNewsStart")
        val newsList = mutableListOf<News>()

        val db = newsDatabaseHelper.readableDatabase

        val projection = arrayOf(
            NewsDatabaseHelper.COLUMN_ID,
            NewsDatabaseHelper.COLUMN_TITLE,
            NewsDatabaseHelper.COLUMN_IMAGE_URL,
            NewsDatabaseHelper.COLUMN_SUMMARY,
            NewsDatabaseHelper.COLUMN_URL
        )

        val cursor = db.query(
            NewsDatabaseHelper.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(NewsDatabaseHelper.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(NewsDatabaseHelper.COLUMN_TITLE))
                val imageUrl = getString(getColumnIndexOrThrow(NewsDatabaseHelper.COLUMN_IMAGE_URL))
                val summary = getString(getColumnIndexOrThrow(NewsDatabaseHelper.COLUMN_SUMMARY))
                val url = getString(getColumnIndexOrThrow(NewsDatabaseHelper.COLUMN_URL))

                val news = News(id, title, imageUrl, summary, url)
                newsList.add(news)
            }
        }

        cursor.close()
        db.close()

        return newsList
    }
}
