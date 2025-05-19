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
        val db =  getDatabaseBuilder(applicationContext)
        val cardDao = db.cardDao()
        val userDao = db.userDao()
        val transactionDao = db.transactionDao()
        val daoHolder = DaoHolder(cardDao,userDao,transactionDao)
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