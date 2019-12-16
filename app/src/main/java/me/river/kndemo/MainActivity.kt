package me.river.kndemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import me.river.kndemo.logic.getPlatform
import me.river.kndemo.logic.getUser
import me.river.kndemo.logic.setUser

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //头部textview，显示平台信息
        platformTV.text = getPlatform()

        //save按钮点击，读取edittext输入文字，并交由库打印
        save.setOnClickListener{
            val userName = inputName.text.toString()
            setUser(username = userName)
        }

        //从库中读取名字，并展示在地步的textview中
        load.setOnClickListener{
            val loadName = getUser()
            loadNameTV.text = loadName
        }
    }
}
