package com.example.weight_tracker.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weight_tracker.WeightViewModel
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWeight(viewModel: WeightViewModel, navController: NavController) {
    // Variables for getting the current date
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var weight by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("$day.$month.$year") }
    var showDatePicker = viewModel.openPopUp.value

    // Check the viewModel's openPopUp value, if it's true show the DatePicker composable to the user
    if (showDatePicker) {
        // DatePicker takes 2 parameters, viewModel and
        // onDateSelected function, which gets the selected date as "it" and assigns it to the date variable
        DatePicker(
            viewModel,
            onDateSelected = { date = it }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Date") }
            )
            // Icon that functions like a button
            // onClick change the viewModel's openPopUp value to true to open the DatePicker composable
            IconButton(onClick = { viewModel.isOpen() }) {
                Icon(
                    Icons.Default.DateRange,
                    contentDescription = "Calendar",
                    modifier = Modifier.padding(horizontal = 8.dp),
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = {
                    navController.navigate("weightlist")
                    val formattedDate = stringToDate(date)
                    println(":DD $formattedDate")
                    if (formattedDate != null) {
                        println(":DDD not null")
                        viewModel.addWeight(weight.toInt(), formattedDate)
                    }

                }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text("Add weight")
                }
            }
        }
    }
}

fun stringToDate(date: String): Date? {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy")
    val dateFromString: Date? = try {
        dateFormat.parse(date)
    } catch (e: Exception) {
        println("error: $e")
        null
    }
    return dateFromString
}

