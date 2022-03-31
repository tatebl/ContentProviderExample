package com.example.contentproviderexample.widgets

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.contentproviderexample.ui.RevatureCP
import com.example.contentproviderexample.ui.theme.ContentProviderExampleTheme
import com.example.contentproviderexample.viewModel.CustomerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customerViewModel= ViewModelProvider(this).get(CustomerViewModel::class.java)
        setContent {
            ContentProviderExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@SuppressLint("Range")
@Composable
fun MainScreen() {

    var name by remember() { mutableStateOf("") }
    var result by remember { mutableStateOf("")}
    var context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally) {

        OutlinedTextField(value = name,
            onValueChange = {name=it},
            label = { Text("Name")})

        Spacer(Modifier.height(10.dp))

        Button(onClick = {
            val values = ContentValues()
            values.put(RevatureCP.name,name)   //key=column name, value=new record content
            context.contentResolver.insert(RevatureCP.CONTENT_URI,values)

            Toast.makeText(context, "New record inserted",Toast.LENGTH_LONG).show()
        }) {
            Text(text = "Save new record")
        }

        Spacer(Modifier.height(10.dp))
        Button(onClick = {
            val cursor = context.contentResolver.query(RevatureCP.CONTENT_URI,null,null,null,null)

            if(cursor!!.moveToFirst()) {     //move to first row
                val strBuild = StringBuilder()
                while (!cursor.isAfterLast) {       //as long as new row is found
                    result="${cursor.getString(cursor.getColumnIndex("id"))}-${cursor.getString(cursor.getColumnIndex("name"))}"
                    cursor.moveToNext()
                }
                Log.d("Data","$result")
            } else {
                Log.d("Data","No Records Found")
            }


        }) {
            Text(text = "Display record")
        }
    }
}