package com.example.piston

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.piston.myData.myList
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListView()

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        ListView()
    }

    @Composable
    fun ListView() {
        myData.getList()

        Column(modifier = Modifier.fillMaxHeight()) {
            Text(text = "Courses",
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            textAlign = TextAlign.Center,
                color = Color(66, 132, 237),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,


            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(10.dp))
            LazyColumn(modifier = Modifier.clickable() {
            }) {
                items(myList) { index ->
                    if (index.id % 2 == 1) {
                        if (index.id == myList.size) {
                            Ui(index, 10.dp)
                        } else {
                            Ui(index, 0.dp)
                        }
                    } else {
                        if (index.id == myList.size) {
                            Ui2(index, 10.dp)
                        } else {
                            Ui2(index, 0.dp)
                        }
                    }
                }

            }
        }
    }
}









