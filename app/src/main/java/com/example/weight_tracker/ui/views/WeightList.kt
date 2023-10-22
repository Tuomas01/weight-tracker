package com.example.weight_tracker.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.sharp.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weight_tracker.WeightViewModel

@Composable
fun WeightList(model: WeightViewModel, navController: NavController) {
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
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = { navController.navigate("addweight") }
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text("Add new weight")
                Icon(Icons.Default.ArrowForward, contentDescription = "Next Page")
            }
        }
    }
}