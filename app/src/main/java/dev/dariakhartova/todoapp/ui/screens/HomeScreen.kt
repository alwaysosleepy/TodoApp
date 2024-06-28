package dev.dariakhartova.todoapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.dariakhartova.todoapp.R
import dev.dariakhartova.todoapp.ui.theme.AppTheme
import kotlinx.coroutines.delay

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
    var taskText by remember { mutableStateOf("") }
    var isDone by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBarContent {
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(AppTheme.colorScheme.primaryBackground)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BasicTextField(
                    value = taskText,
                    onValueChange = { taskText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(AppTheme.colorScheme.white)
                        .padding(8.dp),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 18.sp,
                        color = AppTheme.colorScheme.labelPrimary
                    ),
                    decorationBox = { innerTextField ->
                        if (taskText.isEmpty()) {
                            Text(
                                text = stringResource(R.string.description_text),
                                style = AppTheme.typography.body,
                                color = AppTheme.colorScheme.labelTertiary,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                        innerTextField()
                    }
                )

                Text(
                    text = stringResource(R.string.priority_title),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                Text(
                    text = stringResource(R.string.priority_ordinary),
                    color = AppTheme.colorScheme.labelTertiary,
                    fontSize = 14.sp
                )

                Divider(color = AppTheme.colorScheme.lightGray)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.deadline_text), fontWeight = FontWeight.Medium, fontSize = 16.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(
                        checked = isDone,
                        onCheckedChange = { isDone = it }
                    )
                }

                Divider(color = AppTheme.colorScheme.lightGray)

                Button(
                    onClick = {},
                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = AppTheme.colorScheme.lightGray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.delete_text),
                        color = AppTheme.colorScheme.white
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBarContent(onCloseClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colorScheme.primaryBackground)
            .padding(
                top = 48.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Icon(
            Icons.Filled.Close,
            contentDescription = null,
            modifier = Modifier.clickable(onClick = onCloseClick)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.save_button_text),
            fontWeight = FontWeight.Bold,
            style = AppTheme.typography.button,
            color = AppTheme.colorScheme.blue
        )
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