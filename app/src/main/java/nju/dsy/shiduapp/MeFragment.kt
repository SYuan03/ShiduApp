package nju.dsy.shiduapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import nju.dsy.shiduapp.user.UserManager

class MeFragment : Fragment() {

    private lateinit var textViewUsername: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_me, container, false)

        textViewUsername = view.findViewById(R.id.textViewUsrname)

        // 从SharedPreferences中获取用户名和邮箱，并显示在界面上
        val username = UserManager.getUsername(requireContext())

        if (username != null) {
            textViewUsername.text = "Username: $username"
        } else {
            textViewUsername.text = "Username: N/A"
        }


        return view
    }
}