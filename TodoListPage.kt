package com.example.todoapp
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Checkbox


@Composable
fun TodolistPage(viewmodel: TodoViewModel) {

    val todoList by viewmodel.todoList.observeAsState(emptyList())
    val inputText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = inputText.value,  // Access the value here
                onValueChange = { inputText.value = it },  // Update the value here
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    if (inputText.value.isNotBlank()) {
                        Log.d("TodolistPage", "Adding Todo: ${inputText.value}")
                        viewmodel.addTodo(inputText.value)
                        inputText.value = ""
                    } else {
                        Log.d("TodolistPage", "Input is empty, not adding Todo")
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(text = "Add")
            }
        }
        LazyColumn {
            itemsIndexed(todoList) { index, item ->
                TodoItem(item = item, onDelete = {
                    viewmodel.deleteTodo(item.id) },
                    onToggleCompletion = { viewmodel.toggleTodoCompletion(item.id)
                })
            }
        }
    }
}

@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit, onToggleCompletion: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
            .clip(AbsoluteRoundedCornerShape(16.dp))
            .background(if (item.completed) Color.Gray else MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = item.completed,
            onCheckedChange = { onToggleCompletion() }
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("HH:mm:aa, dd/MM", Locale.ENGLISH).format(item.createdAt),
                fontSize = 10.sp,
                color = Color.LightGray
            )
            Text(
                text = item.title,
                fontSize = 20.sp,
                color = if (item.completed) Color.LightGray else Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}

