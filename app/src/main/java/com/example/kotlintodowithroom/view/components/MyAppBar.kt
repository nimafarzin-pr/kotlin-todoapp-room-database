package com.example.kotlintodowithroom.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyAppBar(
    endAction: (() -> Unit)? = null,
    startAction: (() -> Unit)? = null,
    endIcon: ImageVector? = null,
    startIcon: ImageVector? = null,
    title: Int
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (startIcon != null) {
            IconButton(onClick = { startAction?.invoke() }) {
                Icon(
                    imageVector = startIcon,
                    contentDescription = "start",
                    modifier = Modifier.size(35.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Text(
            text = stringResource(id = title),
            modifier = Modifier.weight(1f),
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimary
        )
        if (endIcon != null) {
            IconButton(onClick = { endAction?.invoke() }) {
                Icon(
                    imageVector = endIcon,
                    contentDescription = "end",
                    modifier = Modifier.size(35.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

    }


}