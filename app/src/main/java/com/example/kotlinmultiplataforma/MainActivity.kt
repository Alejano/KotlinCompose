package com.example.kotlinmultiplataforma

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinmultiplataforma.ui.theme.KotlinmultiplataformaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Body()
        }
    }
}

@Composable
fun Body() {
    var changeImage by rememberSaveable { mutableIntStateOf(value = R.drawable.baseline_person_24) }
    var changeText by rememberSaveable { mutableStateOf(value ="Persona" ) }
    var columnDynamic by rememberSaveable { mutableIntStateOf(value = 1) }


    KotlinmultiplataformaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .safeDrawingPadding()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Bienvenido!",
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.Gray)
                        .clickable {
                            Log.d("Click", "Hice click a BIENVENIDO")
                        },
                    fontSize = 50.sp,
                )

                CustomText(
                    Modifier.padding(10.dp),
                    "pequeño saltamontes",
                    20.sp
                )

                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .height(60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    CustomOptionText(
                        Modifier,
                        "1",
                        onClick = {texto->
                            changeText = texto
                            changeImage = R.drawable.baseline_accessibility_24
                            columnDynamic = 1
                        }
                    )
                    CustomOptionText(
                        Modifier,
                        "2",
                        onClick = {texto->
                            changeText = texto
                            changeImage = R.drawable.baseline_ac_unit_24
                            columnDynamic = 2
                        }
                    )
                    CustomOptionText(
                        Modifier,
                        "3",
                        onClick = {texto->
                            changeText = texto
                            changeImage = R.drawable.baseline_account_balance_wallet_24
                            columnDynamic = 3
                        }
                    )
                    CustomOptionText(
                        Modifier,
                        "4",
                        onClick = {texto->
                            changeText = texto
                            changeImage = R.drawable.baseline_person_24
                            columnDynamic = 4
                        }
                    )
                    CustomOptionText(
                        Modifier,
                        "...10",
                        onClick = {texto->
                            changeText = texto
                            changeImage = R.drawable.ic_launcher_foreground
                            columnDynamic = 10
                        }
                    )
                }

                Card(
                    Modifier.padding(10.dp),
                    elevation = CardDefaults.cardElevation(10.dp),
                    border = BorderStroke(1.dp, Color.Black)
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(id = changeImage),
                            contentDescription = changeText
                        )
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = changeText,
                            fontSize = 20.sp)
                    }
                }
                LazyColumn {
                    items(columnDynamic){
                        x->
                        var showMore by rememberSaveable { mutableStateOf(value =false ) }
                        val showMoreHeight by animateDpAsState(
                            targetValue = if(showMore) 200.dp else 60.dp,
                            animationSpec = tween(
                                durationMillis = 2000
                            )
                        )
                        Column(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(showMoreHeight)
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    shape = MaterialTheme.shapes.medium
                                )
                                .border(
                                    shape = MaterialTheme.shapes.medium,
                                    border = BorderStroke(1.dp, Color.Black)
                                ),
                        ){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text("Numero ${x + 1}",fontSize = 30.sp)
                                OutlinedButton(
                                    colors = ButtonDefaults.buttonColors(),
                                    onClick = {
                                        Log.d("Click", "Hice click a $x")
                                        showMore = !showMore
                                    }
                                ){
                                    Text(if(!showMore)"Show More" else "Show Less")
                                }
                            }
                            if(showMore){
                                Row(modifier = Modifier.padding(10.dp)){
                                    Text("Texto que se muestra con el indice $x", fontSize = 20.sp)
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}


@Composable
fun CustomText(modifier: Modifier,texto: String,fontSize: TextUnit) {
    Text(
        text = texto,
        modifier = modifier
            .border(
                brush = SolidColor(Color.Black),
                shape = MaterialTheme.shapes.small,
                width = 1.dp
            )
            .padding(all = 10.dp),
        fontSize = fontSize
    )
}

@Composable
fun CustomOptionText(modifier: Modifier, texto: String, onClick: (String) -> Unit) {
    Text(
        text = texto,
        modifier = modifier
            .border(
                brush = SolidColor(Color.Black),
                shape = MaterialTheme.shapes.small,
                width = 1.dp
            )
            .padding(all = 10.dp)
            .clickable {
                onClick(texto)
                Log.d("Click", "Hice click a $texto")
            },
        fontSize = 20.sp
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Body()
}