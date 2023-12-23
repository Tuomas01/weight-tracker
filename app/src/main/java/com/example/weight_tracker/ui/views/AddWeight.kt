package com.example.weight_tracker.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weight_tracker.WeightViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWeight(viewModel: WeightViewModel) {
    // Variables for getting the current date
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var weight by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("$day/$month/$year") }
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
            label = { Text("Weight") }
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
    }

}