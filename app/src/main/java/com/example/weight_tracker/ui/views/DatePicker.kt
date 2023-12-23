package com.example.weight_tracker.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import com.example.weight_tracker.WeightViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    viewModel: WeightViewModel,
    onDateSelected: (String) -> Unit
) {
    val datePickerState =
        rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    val openPopUp = viewModel.openPopUp.value
    // Change the DatePicker's selected value from Long to a Date type that uses the device's locale
    val selectedDate = datePickerState.selectedDateMillis?.let {
        DateFormat.getDateInstance().format(it)
    } ?: ""
    DatePickerDialog(
        modifier = Modifier.fillMaxSize(),
        onDismissRequest = { /*TODO*/ },
        dismissButton = {
            Button(
                onClick = { viewModel.isOpen() },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("Close")
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.isOpen()
                    onDateSelected(selectedDate)
                    println(":DDD ${datePickerState.selectedDateMillis}, isOpen value: $openPopUp, selectedDate: $selectedDate")
                },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .padding(end = 24.dp)
            ) {
                Text("Add weight")
            }
        },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            DatePicker(
                state = datePickerState,
                dateFormatter = remember { DatePickerDefaults.dateFormatter() },
            )
        }
    }
}