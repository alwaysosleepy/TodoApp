package dev.dariakhartova.todoapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.dariakhartova.todoapp.data.model.TodoItem

@Composable
fun EntryScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(startDestination = "task_list", navController = navController) {
        composable("task_list") {
            TaskScreen(onFabClick = {
                navController.navigate("task_detail")
            })
        }
        composable("task_detail") {
            TaskDetail()
        }
    }
}

@Composable
fun TaskDetail(modifier: Modifier = Modifier) {
    
    Scaffold(
        topBar = {
            Row(Modifier.padding(
                top = 48.dp,
                start = 16.dp,
                end = 16.dp
            )){
                Icon(Icons.Filled.Close, contentDescription = null)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Сохранить",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                    )
            }
        }
    ){ padding ->
        Box(modifier = Modifier.padding(padding))
        
    }

}

@Composable
fun TaskScreen(
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    modifier: Modifier = Modifier,
    onFabClick: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    Scaffold(topBar = {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(start = 48.dp, bottom = 16.dp, top = 48.dp)){
            Text(
                text = "Мои дела",
                modifier = Modifier.padding(top = 48.dp),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
            Text(
                text = "Выполнено — 5"
                )
        }
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick,
                shape = CircleShape,
                containerColor = Color.Blue
            ) {
                Icon(Icons.Filled.Add, "Floating action button.", tint = Color.White)
            }
        },

        content = { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn {
                items(state.value.todoList){
                    TaskRow(it.description, it.isDone)
                }

            }

        }
    })
}

@Composable
fun TaskRow(text: String, checked: Boolean, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Checkbox(checked = checked, onCheckedChange = {})
        Text(text, maxLines = 3)
        Spacer(modifier = Modifier.weight(1f))
        Icon(Icons.Outlined.Info, "Floating action button.", tint = Color.LightGray)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TaskScreenPreview() {
    TaskScreen(onFabClick = {})
}