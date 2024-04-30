package com.abdroid.egylens.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdroid.egylens.R
import com.abdroid.egylens.ui.theme.notoFont


@Composable
fun EmailTextField(
    //modifier: Modifier,
    hintValue: String, leadingIcon: Painter,
    email: String,
    onEmailChange: (String) -> Unit
) {
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .border(
                1.dp,
                colorResource(id = R.color.text_field_border),
                shape = RoundedCornerShape(14.dp)
            )
            //.padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(colorResource(id = R.color.text_field_bg))
            .padding(8.dp),

        value = email
        , onValueChange =
            onEmailChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(
            color = colorResource(id = R.color.text_field_text),
            fontSize = 12.sp,
            fontFamily = notoFont,
            fontWeight = FontWeight.SemiBold,
        ),
        decorationBox = {innerTextField ->
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = leadingIcon,
                    contentDescription = null,
                    tint = colorResource(id = R.color.text_field_icon)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Spacer(modifier = Modifier
                    .width(1.dp)
                    .height(15.dp)
                    .background(colorResource(id = R.color.text_field_icon))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(modifier = Modifier.weight(1f)){
                    if (email.isEmpty()){
                        Text(
                            text = hintValue,
                            fontSize = 12.sp,
                            fontFamily = notoFont,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.text_field_hint)
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Composable
fun NameTextField(
    hintValue: String,
    leadingIcon: Painter,
    name: String,
    onNameChange: (String) -> Unit
) {
    val transformedName = remember(name) {
        name.split(" ").joinToString(" ") { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } }
    }

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .border(
                1.dp,
                colorResource(id = R.color.text_field_border),
                shape = RoundedCornerShape(14.dp)
            )
            .clip(RoundedCornerShape(14.dp))
            .background(colorResource(id = R.color.text_field_bg))
            .padding(8.dp),
        value = transformedName,
        onValueChange = { newValue ->
            if (newValue.length <= 15) {
                onNameChange(newValue)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(
            color = colorResource(id = R.color.text_field_text),
            fontSize = 12.sp,
            fontFamily = notoFont,
            fontWeight = FontWeight.SemiBold,
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = leadingIcon,
                    contentDescription = null,
                    tint = colorResource(id = R.color.text_field_icon)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Spacer(
                    modifier = Modifier
                        .width(1.dp)
                        .height(15.dp)
                        .background(colorResource(id = R.color.text_field_icon))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(modifier = Modifier.weight(1f)) {
                    if (name.isEmpty()) {
                        Text(
                            text = hintValue,
                            fontSize = 12.sp,
                            fontFamily = notoFont,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.text_field_hint)
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}


@Composable
fun PasswordTextField(
    hintValue: String,
    leadingIcon: Painter,
    password: String,
    onPasswordChange: (String) -> Unit,
    isPasswordOpen: Boolean,
    onTogglePasswordVisibility: () -> Unit
) {
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .border(
                1.dp,
                colorResource(id = R.color.text_field_border),
                shape = RoundedCornerShape(14.dp)
            )
            .clip(RoundedCornerShape(14.dp))
            .background(colorResource(id = R.color.text_field_bg))
            .padding(8.dp),
        value = password,
        onValueChange = onPasswordChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        maxLines = 1,
        singleLine = true,
        visualTransformation = if (!isPasswordOpen) PasswordVisualTransformation() else VisualTransformation.None,
        textStyle = TextStyle(
            color = colorResource(id = R.color.text_field_text),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = notoFont,
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = leadingIcon,
                    contentDescription = null,
                    tint = colorResource(id = R.color.text_field_icon)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Spacer(
                    modifier = Modifier
                        .width(1.dp)
                        .height(15.dp)
                        .background(colorResource(id = R.color.text_field_icon))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(modifier = Modifier.weight(1f)) {
                    if (password.isEmpty()) {
                        Text(
                            text = hintValue,
                            fontSize = 12.sp,
                            fontFamily = notoFont,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.text_field_hint)
                        )
                    }
                    innerTextField()
                }
                IconButton(onClick = onTogglePasswordVisibility) {
                    if (!isPasswordOpen) {
                        Icon(
                            painter = painterResource(id = R.drawable.eye),
                            contentDescription = "",
                            tint = colorResource(id = R.color.text_field_eye),
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.eye_slash),
                            contentDescription = "",
                            tint = colorResource(id = R.color.text_field_eye),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    )
}
