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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var inputSum by remember { mutableStateOf("1") }
    var inputDish by remember { mutableStateOf("1") }
    var resultMessage by remember { mutableStateOf("") }
    var sliderValue by remember { mutableStateOf(0f) }
    val discounts = listOf(3, 5, 7, 10)
    var (selectedDiscount) = remember { mutableStateOf(discounts[0])}
    val resultDiscount = when {
        inputDish.toInt() > 10  -> discounts[3]
        inputDish.toInt() > 5 -> discounts[2]
        inputDish.toInt() > 2 -> discounts[1]
        else -> discounts[0]
    }
    val resultWithDiscount = inputSum.toFloat() - (inputSum.toFloat()*0.01*selectedDiscount)
    val resultWithTips = resultWithDiscount.toFloat()+ (resultWithDiscount.toFloat()*0.01*sliderValue.toFloat())
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
                        selectedDiscount = resultDiscount
                    }
                },
                //label = { Text("n") },
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = "Чаевые"
        )
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..25f,
            steps = 24,  // количество промежуточных шагов
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "0"
            )
            Text(
                text = "25"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            Modifier.fillMaxWidth(),
           // horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Скидка:"
            )

            Row(Modifier
                .selectableGroup()
                //.padding(16.dp)
                ,
                horizontalArrangement = Arrangement.SpaceBetween
               // .fillMaxWidth()
            ){
            discounts.forEach { option  ->
                Column(
                    )
                {
                    RadioButton(
                        selected = (option == selectedDiscount ),
                        onClick = {  }
                    )
                    Text(text = option.toString()+"%", fontSize = 24.sp)
                }
             }
            }
        }

        Text("Результат")
        ResultRow("Сумма заказа со скидкой ",String.format("%.2f",resultWithDiscount))
        ResultRow("Сумма заказа с чаевыми ",String.format("%.2f",  resultWithTips))


    }






    }

@Composable
private fun ResultRow(label: String, value: String, emphasized: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label)
        Text(value)
    }
}


@Preview(showBackground = true)
@Composable
fun MainPreview() {
    Lab5Theme {
        MainScreen()
    }
}