package org.banking.simple.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cmppreference.AppContext
import getDatabaseBuilder
import org.banking.simple.app.core.DaoHolder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        AppContext.apply { set(this@MainActivity) }
        val cardDao = getDatabaseBuilder(applicationContext).cardDao()
        val userDao = getDatabaseBuilder(applicationContext).userDao()
        val daoHolder = DaoHolder(cardDao,userDao)
        setContent {
            App(daoHolder)
        }
    }
}
//
//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}