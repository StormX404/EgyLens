package com.abdroid.egylens.presentation.profile.screens.aboutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdroid.egylens.R
import com.abdroid.egylens.presentation.common.CustomTopAppBar
import com.abdroid.egylens.presentation.common.TeamMember
import com.abdroid.egylens.ui.theme.notoFont

@Composable
fun AboutScreen() {
    val teamMembers = listOf(
        TeamMembersData(R.drawable.team_leader, "Omar Nabil", "Team Leader | Back End Developer"),
        TeamMembersData(R.drawable.android, "Abdelrahman Yasser", "Android Developer"),
        TeamMembersData(R.drawable.ai, "Ahmed Abdelkhalek", "AI Developer"),
        TeamMembersData(R.drawable.ui_ux, "Omnia Ahmed", "UI / UX Designer"),
        TeamMembersData(R.drawable.web, "Dina Abdelrahman", "Web Developer"),
        TeamMembersData(R.drawable.data, "Mostafa Ali", "Data collector"),
    )
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CustomTopAppBar(text = "About", height = 60)
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = colorResource(id = R.color.divider)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        colorResource(id = R.color.text_field_border),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .clip(RoundedCornerShape(14.dp))
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Egy Lens is an Android app that scans statues to provide detailed info and Statues comes to life .",
                    fontFamily = notoFont,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.main_text)
                )
            }
        }

        Text(
            text = "Team Members",
            fontFamily = notoFont,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.main_text)
        )

        LazyColumn(
            modifier = Modifier.padding(vertical = 10.dp , horizontal = 10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(teamMembers) { member ->
                TeamMember(icon = member.icon, name = member.name, title = member.title)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AboutScreenPrev() {
    AboutScreen()
}