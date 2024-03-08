package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember {
        mutableStateOf(value = "")
    }
    var outputValue by remember {
        mutableStateOf(value = "")
    }
    var inputUnit by remember {
        mutableStateOf(value = "Meters")
    }
    var outputUnit by remember {
        mutableStateOf(value = "Meters")
    }
    var iExpanded by remember {
        mutableStateOf(value = false)
    }
    var oExpanded by remember {
        mutableStateOf(value = false)
    }
    val conversionFactor = remember {
        mutableStateOf(1.0)
    }
    val oconversionFactor = remember {
        mutableStateOf(1.0)
    }
    fun convertUnits() {
        // ?: elvis operator smart if statement
        var inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        var result = (inputValueDouble * conversionFactor.value * 100.0 / oconversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Default, // Replace with your desired font family
        fontSize = 16.5.sp, // Replace with your desired font size
        color = Color.Red // Replace with your desired text color
    )
    Column( modifier = Modifier.fillMaxSize() ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            )  {

        //Here all the UI elements will be stacked below each other
        Text(text = "Unit Converter",
            style = customTextStyle )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = {
            inputValue = it
            // Here goes what sohuld happed, when theValue of our OutloinedTextfielld changes
            }, label = { Text(text = "Enter Value")}
            )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { iExpanded = true}) {
                    Text(text = "$inputUnit")
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "ArrowDown")
                }
                // INPUTTTT
                // expanded expands the drop down menu
                // ondismmiss request will shrinks the menu if you click anywhere else
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    //This is where the items in dropdown menu
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters")},
                        onClick = {
                            // Text
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Milimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Milimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = "$outputUnit")
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "ArrowDown")
                }
                //Output box

                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    //This is where the items in dropdown menu
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oconversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oconversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oconversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Milimeters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Milimeters"
                            oconversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("result: $outputValue $outputUnit" ,

            )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}