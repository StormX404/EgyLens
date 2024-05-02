package com.abdroid.egylens.presentation.common

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.abdroid.egylens.R
import com.abdroid.egylens.presentation.onBoardingScreen.Dimens.ExtraSmallPadding2
import com.abdroid.egylens.presentation.onBoardingScreen.Dimens.IconSize
import com.abdroid.egylens.ui.theme.notoFont

@Composable
fun AppBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = colorResource(id = R.color.bottom_bar)
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Icon(
                            painter = if (index == selectedItem) painterResource(id = item.filled)
                            else painterResource(id = item.iconOutlined),
                            contentDescription = null,
                            modifier = Modifier.size(IconSize),
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        Text(
                            text = item.text,
                            fontFamily = notoFont,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            //color = colorResource(id = R.color.main_text)
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.nav_bar_selected_icon),
                    selectedTextColor = colorResource(id = R.color.nav_bar_selected_text),
                    unselectedIconColor = colorResource(id = R.color.nav_bar_un_selected_icon),
                    unselectedTextColor = colorResource(id = R.color.nav_bar_un_selected_text),
                    indicatorColor = colorResource(id = R.color.bottom_bar),
                ),
            )
        }
    }
}

data class BottomNavigationItem(
    @DrawableRes val iconOutlined : Int,
    @DrawableRes val filled : Int,
    val text: String
)

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview() {
        AppBottomNavigation(items = listOf(
            BottomNavigationItem(iconOutlined = R.drawable.home_outlined,filled = R.drawable.home_bold, text = "Home"),
            BottomNavigationItem(iconOutlined = R.drawable.heart_outlined,filled = R.drawable.heart_bold, text = "Favorites"),
            BottomNavigationItem(iconOutlined = R.drawable.search_normal_outlined,filled = R.drawable.search_normal ,text = "Search"),
            BottomNavigationItem(iconOutlined = R.drawable.bookmark_outlined,filled = R.drawable.bookmark_bold, text = "Profile"),
        ), selectedItem = 0, onItemClick = {})
}