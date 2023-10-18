package com.example.weight_tracker.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weight_tracker.WeightViewModel

@Composable
fun WeightList(model: WeightViewModel) {
    val weights = model.getAllWeights().observeAsState(listOf())
    LazyColumn {
        item {
            Text(
                text = "Saved weights:",
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        items(weights.value) {
            Text("${it.date}: ${it.weight}")
        }
    }
}