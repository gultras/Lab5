package com.example.lab5

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab5.ui.theme.Lab5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var inputSum by remember { mutableStateOf("") }
    var inputDish by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("") }
    var sliderValue by remember { mutableStateOf(0f) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        //horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center
    ) {
    Row( modifier = Modifier
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "Сумма заказа"
        )

        Spacer(modifier = Modifier.width(16.dp))
        TextField(
            value = inputSum,
            onValueChange = { newText ->
                if (newText.all { it.isDigit() }) {
                    inputSum = newText
                }
            },
            //label = { Text("n") },
            singleLine = true
        )
    }

        Row( modifier = Modifier
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Количество блюд"
            )

            Spacer(modifier = Modifier.width(16.dp))
            TextField(
                value = inputDish,
                onValueChange = { newText ->
                    if (newText.all { it.isDigit() }) {
                        inputDish = newText
                    }
                },
                //label = { Text("n") },
                singleLine = true
            )
        }
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..25f,
            steps = 25,  // количество промежуточных шагов
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "0"
        )



    }

}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    Lab5Theme {
        MainScreen()
    }
}