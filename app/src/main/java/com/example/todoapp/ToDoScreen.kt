
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.todoapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ToDoScreen(viewModel: ToDoViewModel) {

    val tasks = viewModel.tasks
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Simple To-Do") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (inputText.isNotBlank()) {
                        viewModel.addTask(inputText)
                        inputText = ""
                    }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("New task") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (tasks.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text("No tasks yet — add one with + button")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(tasks, key = { it.id }) { item ->
                        TaskRow(
                            item = item,
                            onDelete = { viewModel.removeTaskById(item.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TaskRow(item: ToDoItem, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(item.text, modifier = Modifier.weight(1f))
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}
