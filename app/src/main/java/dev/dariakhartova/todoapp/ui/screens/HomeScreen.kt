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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.dariakhartova.todoapp.R
import dev.dariakhartova.todoapp.ui.theme.AppTheme
import kotlinx.coroutines.delay

@Composable
fun HomeScreen() {
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
fun TaskScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onFabClick: () -> Unit
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBarTaskContent()
        },
        snackbarHost = {
            if (state.value.isError) {
                Snackbar {
                    Text(
                        text = stringResource(R.string.error_toast_text)
                    )
                }
                LaunchedEffect(key1 = Unit) {
                    delay(3000L)
                    viewModel.onSnackBarShown()
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick,
                shape = CircleShape,
                containerColor = AppTheme.colorScheme.blue
            ) {
                Icon(
                    Icons.Filled.Add,
                    "Floating action button.",
                    tint = AppTheme.colorScheme.white
                )
            }
        },

        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(AppTheme.colorScheme.primaryBackground)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(AppTheme.colorScheme.white)

                ) {
                    items(state.value.todoList) {
                        TaskRow(it.description, it.isDone)
                    }

                }

            }
        })
}

@Composable
private fun TopBarTaskContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colorScheme.primaryBackground)
            .padding(start = 48.dp, bottom = 16.dp, top = 82.dp)
    )
    {
        Text(
            text = stringResource(R.string.todos_title),
            modifier = Modifier.padding(top = 48.dp),
            style = AppTheme.typography.largeTitle
        )
        Text(
            text = stringResource(R.string.todos_title_done),
            style = AppTheme.typography.body,
            color = AppTheme.colorScheme.labelTertiary
        )
    }
}

@Composable
fun TaskRow(text: String, checked: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Checkbox(checked = checked, onCheckedChange = {})
        Text(text, maxLines = 3)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            Icons.Outlined.Info,
            "Floating action button.",
            tint = AppTheme.colorScheme.lightGray
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TaskScreenPreview() {
    TaskScreen(onFabClick = {})
}