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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.dariakhartova.todoapp.R
import dev.dariakhartova.todoapp.ui.theme.AppTheme

@Composable
fun TaskDetail() {
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
                TaskDescriptionInput(taskText = taskText, onValueChange = { taskText = it })

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

                Deadline(isDone = isDone, onValueChange = { isDone = it})

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
private fun TaskDescriptionInput(taskText: String, onValueChange: (String) -> Unit) {
    BasicTextField(
        value = taskText,
        onValueChange = onValueChange,
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
                TaskDescriptionPlaceholder()
            }
            innerTextField()
        }
    )

}

@Composable
private fun TaskDescriptionPlaceholder(){
    Text(
        text = stringResource(R.string.description_text),
        style = AppTheme.typography.body,
        color = AppTheme.colorScheme.labelTertiary,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
private fun Deadline (isDone: Boolean, onValueChange: (Boolean) -> Unit) {
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
            onCheckedChange = onValueChange
        )
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