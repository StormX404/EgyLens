package com.abdroid.egylens.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.abdroid.egylens.R
import com.abdroid.egylens.ui.theme.notoFont
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun CustomDialog(
    title: String,
    desc: String,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
            ) {
                Spacer(modifier = Modifier.height(36.dp))
                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.text_field_bg),
                            shape = RoundedCornerShape(16.dp)
                        )
                        //.border(width = 1.dp, color = colorResource(id = R.color.text_field_border) , shape = RoundedCornerShape(16.dp))

                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = title,
                            fontFamily = notoFont,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.main_text)
                        )
                        //Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = desc,
                            fontFamily = notoFont,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.second_text)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            //.padding(8.dp),
                            onClick = {
                                onDismiss()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.main_button),
                                contentColor = colorResource(id = R.color.button_text)
                            ),
                            shape = RoundedCornerShape(size = 6.dp)
                        ) {
                            Text(
                                text = "Done",
                                fontFamily = notoFont,
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(id = R.color.button_text)
                            )

                        }
                    }
                }
            }

            SuccessHeader(
                modifier = Modifier
                    .size(95.dp)
                    .background(colorResource(id = R.color.text_field_bg), shape = CircleShape)
                    .align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun SuccessHeader(modifier: Modifier) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.check_your_email))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        /*iterations = LottieConstants.IterateForever,
        isPlaying = true*/)

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = modifier,)
}



@Composable
fun LogOutDialog(
    title: String,
    desc: String,
    onDismiss: () -> Unit,
    onContinue: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
            ) {
                Spacer(modifier = Modifier.height(36.dp))
                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.text_field_bg),
                            shape = RoundedCornerShape(16.dp)
                        )
                    //.border(width = 1.dp, color = colorResource(id = R.color.text_field_border) , shape = RoundedCornerShape(16.dp))

                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = title,
                            fontFamily = notoFont,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.main_text)
                        )
                        //Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = desc,
                            fontFamily = notoFont,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.second_text)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(modifier = Modifier
                            .fillMaxWidth() ,
                            verticalAlignment = Alignment.CenterVertically ,
                            horizontalArrangement = Arrangement.SpaceAround) {
                            Button(
                                modifier = Modifier
                                    .height(40.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                //.padding(8.dp),
                                onClick = {
                                    onDismiss()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.main_button),
                                    contentColor = colorResource(id = R.color.button_text)
                                ),
                                shape = RoundedCornerShape(size = 6.dp)
                            ) {
                                Text(
                                    text = "Cancel",
                                    fontFamily = notoFont,
                                    fontWeight = FontWeight.SemiBold,
                                    color = colorResource(id = R.color.button_text)
                                )

                            }
                            Button(
                                modifier = Modifier
                                    .height(40.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                //.padding(8.dp),
                                onClick = {
                                    onContinue()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.scanner_button),
                                    contentColor = colorResource(id = R.color.button_text)
                                ),
                                shape = RoundedCornerShape(size = 6.dp)
                            ) {
                                Text(
                                    text = "Logout",
                                    fontFamily = notoFont,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )

                            }

                        }

                    }
                }
            }

            LogoutHeader(
                modifier = Modifier
                    .size(95.dp)
                    .background(colorResource(id = R.color.text_field_bg), shape = CircleShape)
                    .align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun LogoutHeader(modifier: Modifier) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.warning))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true)

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = modifier,)
}

@Composable
fun ScanDialog(
    title: String,
    desc: String,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
            ) {
                Spacer(modifier = Modifier.height(36.dp))
                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.text_field_bg),
                            shape = RoundedCornerShape(16.dp)
                        )
                    //.border(width = 1.dp, color = colorResource(id = R.color.text_field_border) , shape = RoundedCornerShape(16.dp))

                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = title,
                            fontFamily = notoFont,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.main_text)
                        )
                        //Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = desc,
                            fontFamily = notoFont,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.second_text)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(modifier = Modifier
                            .fillMaxWidth() ,
                            verticalAlignment = Alignment.CenterVertically ,
                            horizontalArrangement = Arrangement.SpaceAround) {
                            Button(
                                modifier = Modifier
                                    .height(40.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                //.padding(8.dp),
                                onClick = {
                                    onDismiss()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.main_button),
                                    contentColor = colorResource(id = R.color.button_text)
                                ),
                                shape = RoundedCornerShape(size = 6.dp)
                            ) {
                                Text(
                                    text = "Details",
                                    fontFamily = notoFont,
                                    fontWeight = FontWeight.SemiBold,
                                    color = colorResource(id = R.color.button_text)
                                )

                            }

                        }

                    }
                }
            }
            SuccessStatueHeader(
                modifier = Modifier
                    .size(90.dp)
                    .background(colorResource(id = R.color.text_field_bg), shape = CircleShape)
                    .align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun SuccessStatueHeader(modifier: Modifier) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.succesfull))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        /*iterations = LottieConstants.IterateForever,
        isPlaying = true*/)

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = modifier)
}


@Preview
@Composable
private fun LogoutDialogPrev() {
    ScanDialog(title = "Ramses II",
        desc = "This is a description of the statue which scanned by AI Model ",
        onDismiss = {},
    )
}