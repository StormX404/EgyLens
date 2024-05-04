package com.abdroid.egylens.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdroid.egylens.R
import com.abdroid.egylens.presentation.onBoardingScreen.Dimens
import com.abdroid.egylens.presentation.onBoardingScreen.Dimens.MediumPadding1

fun Modifier.shimmerEffect(cornerRadius: CornerRadius = CornerRadius(x = 12f, y = 12f)) = composed {
    val transition = rememberInfiniteTransition(label = "shimmer effect")
    val alpha = transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "transparency of the background color"
    ).value
    val color = colorResource(id = R.color.divider).copy(alpha = alpha)
    drawBehind {
        drawRoundRect(
            color = color,
            cornerRadius = cornerRadius
        )
    }
}

@Composable
fun ShimmerEffect(modifier: Modifier) {
    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(150.dp)
                    .shimmerEffect()

            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .shimmerEffect(),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .shimmerEffect(),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .height(60.dp)
                    .weight(.8f)
                    .shimmerEffect()

            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .shimmerEffect(),
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            )
            {
                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .width(160.dp)
                        .shimmerEffect()

                )
            }
            Box(
                modifier = Modifier
                    .height(400.dp)
                    .width(300.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .shimmerEffect()

            )
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(160.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .shimmerEffect()

            )

        }
    }
}

@Composable
fun CardShimmerEffect(modifier: Modifier) {
    Row(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = Dimens.ExtraSmallPadding)
                .height(Dimens.ArticleCardSize)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .padding(horizontal = MediumPadding1)
                    .shimmerEffect()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(horizontal = MediumPadding1)
                        .height(30.dp)
                        .shimmerEffect()
                )

            }
        }
    }
}

@Composable
fun ShimmerEffectList(modifier: Modifier = Modifier.padding(horizontal = 20.dp)) {
    Column (Modifier , horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.spacedBy(15.dp)){
        CardShimmerEffect(modifier)
        CardShimmerEffect(modifier)
        CardShimmerEffect(modifier)
        CardShimmerEffect(modifier)
    }
}

/*@Preview
@Composable
private fun ShimmerEffectPrev() {
    ShimmerEffect()
}*/
@Preview
@Composable
private fun CardShimmerEffectPrev() {
    ShimmerEffectList()
}