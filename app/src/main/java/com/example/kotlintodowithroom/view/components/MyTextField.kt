package com.example.kotlintodowithroom.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    value: String,
    placeHolder: String,
    onValueChange: (String) -> Unit,
    validator: (String) -> Boolean,
    errorMessage: String?
) {
    val isError = validator(value)

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange.invoke(it) },
        isError = validator(value),
        maxLines = 3,
        label = { Text(text = placeHolder) },
        placeholder = {
            Text(text = placeHolder)

        },
        textStyle = TextStyle(
            fontWeight = FontWeight.SemiBold, fontSize = 17.sp
        ),

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Green,
            unfocusedBorderColor = Color.Gray,
            errorBorderColor = Color.Red
        )
    )

    if (isError) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            text = errorMessage ?: "this field is required",
            color = Color.Red
        )
    }
}
