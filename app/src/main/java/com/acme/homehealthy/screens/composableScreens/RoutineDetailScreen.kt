package com.acme.homehealthy.screens.composableScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.acme.homehealthy.data.models.RoutineDetail
import com.acme.homehealthy.ui.theme.*
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun RoutineDetailScreen(
    navController: NavController,
    bodyPart: String,
    routineDetailList: List<RoutineDetail>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack)
    ) {
        Column() {
            RoutineGoals()
            RoutineDetailList(_routineDetailList = routineDetailList, bodyPart = bodyPart)

        }

    }
}

@Composable
fun RoutineGoals() {
    Box(
        modifier = Modifier
            .background(DeepBlack)
            .padding(top = 15.dp, start = 15.dp, end = 20.dp, bottom = 10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
    )
    {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(15.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(TransparentBlack)
                .padding(horizontal = 15.dp, vertical = 20.dp)
                .fillMaxWidth()
        ) {
            FoodStats(valueStat = 96, color = hypertrophy, statName = "Hypertrofphy")
            Spacer(modifier = Modifier.height(25.dp))
            FoodStats(valueStat = 45, color = Strength, statName = "Strengh")
            Spacer(modifier = Modifier.height(25.dp))
            FoodStats(valueStat = 78, color = WheightLose, statName = "Wheight Loss")
        }
    }
}

@Composable
fun RoutineDetailList(_routineDetailList: List<RoutineDetail>, bodyPart: String) {
    LazyColumn() {
        items(_routineDetailList) { _routineDetail ->

            if (bodyPart.uppercase() == "BICEPS" || bodyPart.uppercase() == "TRICEPS" && _routineDetail.name.uppercase() == "ARMS") {
                RoutineDetailItem(_routineDetail = _routineDetail)

            } else if (bodyPart.uppercase() == _routineDetail.name.uppercase()) {
                RoutineDetailItem(_routineDetail = _routineDetail)
            }
            //RoutineDetailItem(_routineDetail = _routineDetail)

        }
    }
}

@Composable
fun RoutineDetailItem(_routineDetail: RoutineDetail) {

        Card(
            elevation = 13.dp,
            backgroundColor = DeepBlack,
            border = BorderStroke(2.dp, cardNight),
            shape = RoundedCornerShape(90.dp),
            modifier = Modifier.padding(start = 80.dp)
        )
        {
            GlideImage(
                imageModel = _routineDetail.url,
                contentScale = ContentScale.Fit,
                circularReveal = CircularReveal(duration = 1200),
                modifier = Modifier
                    .padding(top = 15.dp, start = 15.dp, bottom = 15.dp, end = 15.dp)
                    .size(200.dp, 120.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
}
