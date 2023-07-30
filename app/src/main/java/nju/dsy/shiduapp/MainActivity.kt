package nju.dsy.shiduapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import nju.dsy.shiduapp.news.NewsDatabaseHelper

class MainActivity : AppCompatActivity() {

    // tag
    private val TAG = "MainActivity"
    // 其他代码
    lateinit var newsDatabaseHelper: NewsDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.v(TAG,"Main activity onCreate ; time = ${System.currentTimeMillis()}")

        // 初始化数据库
        newsDatabaseHelper = NewsDatabaseHelper(this)

        // 设置导航栏点击事件监听
        // 先获取导航栏的各个控件
        val home = findViewById<RelativeLayout>(R.id.home)
        val explore = findViewById<RelativeLayout>(R.id.explore)
        val music = findViewById<RelativeLayout>(R.id.music)
        val me = findViewById<RelativeLayout>(R.id.me)
        val homeText = findViewById<TextView>(R.id.hometext)
        val homeIcon = findViewById<ImageView>(R.id.homeicon)
        val exploreText = findViewById<TextView>(R.id.exploretext)
        val exploreIcon = findViewById<ImageView>(R.id.exploreicon)
        val musicText = findViewById<TextView>(R.id.musictext)
        val musicIcon = findViewById<ImageView>(R.id.musicicon)
        val meText = findViewById<TextView>(R.id.metext)
        val meIcon = findViewById<ImageView>(R.id.meicon)

        // 设置默认显示的 HomeFragment，并且设置按钮的选中状态
        showFragment(HomeFragment())
        setSelectedNavItem(homeText, homeIcon)

        // 导航栏点击事件监听
        home.setOnClickListener {
            showFragment(HomeFragment())
            // 设置按钮的选中状态
            setSelectedNavItem(homeText, homeIcon)
            // 清除其他按钮的选中状态
            clearSelectedNavItem(exploreText, exploreIcon)
            clearSelectedNavItem(musicText, musicIcon)
            clearSelectedNavItem(meText, meIcon)
        }
        explore.setOnClickListener {
            showFragment(ExploreFragment())
            setSelectedNavItem(exploreText, exploreIcon)
            clearSelectedNavItem(homeText, homeIcon)
            clearSelectedNavItem(musicText, musicIcon)
            clearSelectedNavItem(meText, meIcon)
        }
        music.setOnClickListener {
            showFragment(MusicFragment())
            setSelectedNavItem(musicText, musicIcon)
            clearSelectedNavItem(homeText, homeIcon)
            clearSelectedNavItem(exploreText, exploreIcon)
            clearSelectedNavItem(meText, meIcon)
        }
        me.setOnClickListener {
            showFragment(MeFragment())
            setSelectedNavItem(meText, meIcon)
            clearSelectedNavItem(homeText, homeIcon)
            clearSelectedNavItem(exploreText, exploreIcon)
            clearSelectedNavItem(musicText, musicIcon)
        }
    }

    private fun showFragment(fragment: Fragment) {
        // 使用 FragmentManager 将 fragment 显示到 activity 中
        // 切换 fragment 时，使用 replace() 方法
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment)
            .commit()
    }

    private fun setSelectedNavItem(textView: TextView, imageView: ImageView) {
        textView.isSelected = true
        imageView.isSelected = true
    }

    private fun clearSelectedNavItem(textView: TextView, imageView: ImageView) {
        textView.isSelected = false
        imageView.isSelected = false
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG,"Main activity onResume ; time = ${System.currentTimeMillis()}")
    }

    override fun onPause() {
        super.onPause()
        Log.v(TAG,"Main activity onPause ; time = ${System.currentTimeMillis()}")
    }



}