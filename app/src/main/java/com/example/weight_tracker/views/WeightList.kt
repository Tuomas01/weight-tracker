package com.example.weight_tracker.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeightList() {
    val list = listOf(":DD", ":DDD")
    LazyColumn {
        item {
            Text(
                text = "Saved weights:",
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        items(list) {
            Text(it)
        }
    }
}