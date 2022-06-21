package com.acme.homehealthy.screens.composableScreens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.acme.homehealthy.R
import com.acme.homehealthy.resources.BotonNavContent
import com.acme.homehealthy.screens.BottomNav
import com.acme.homehealthy.screens.Greetings
import com.acme.homehealthy.screens.Screen
import com.acme.homehealthy.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun dietScreen(navController: NavController) {
    var daysOfWeek: List<String> =
        listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Viernes", "Saturday")


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black)
    ) {

        Column() {
            Greetings(name = "This is your diet")
            caloriesRow()
            dayOfWeek(daysOfWeek = daysOfWeek, navController)
        }

        BottomNav(
            items = listOf(
                BotonNavContent("Training", R.drawable.dumbbellfortraining_89135),
                BotonNavContent(
                    "Nutrition",
                    R.drawable.bodybuilding_nutrition_protein_fitness_diet_icon_149055
                ),
                BotonNavContent("Profile", R.drawable.profile_121261)
            ),
            modifier = Modifier.align(Alignment.BottomEnd),
            navController = navController,
            initialSelectedIndex = 1
        )
    }
}


@Composable
fun caloriesRow() {
    Box(
        modifier = Modifier
            .background(Color.Black)
            .padding(top = 15.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(15.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(TransparentBlack)
                .padding(horizontal = 15.dp, vertical = 20.dp)
                .fillMaxWidth()
        ) {
            // Spacer(modifier = Modifier.width(25.dp))
            circularCaloriesBar(
                percentage = 0.5f,
                number = 100,
                _color = ProteinBar,
                typeOfMacro = "Protein",
                animDelay = 2
            )
            // Spacer(modifier = Modifier.width(25.dp))

            circularCaloriesBar(
                percentage = 0.3f,
                number = 100,
                _color = CarbsBar,
                typeOfMacro = "Carbs",
                animDelay = 14
            )
            //Spacer(modifier = Modifier.width(25.dp))

            circularCaloriesBar(
                percentage = 0.2f,
                number = 100,
                _color = FatBar,
                typeOfMacro = "Fat",
                animDelay = 17
            )
        }


    }
}


@Composable
fun circularCaloriesBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    _color: androidx.compose.ui.graphics.Color,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 6000,
    animDelay: Int = 10,
    typeOfMacro: String
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = _color,
                -90f,
                360 * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = typeOfMacro,
                color = GrayWhite,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = (curPercentage.value * number).toInt().toString(),
                color = GrayWhite,
                fontSize = fontSize,
                fontWeight = FontWeight.W400
            )
        }


    }

}


@ExperimentalFoundationApi
@Composable
fun dayOfWeek(daysOfWeek: List<String>, navController: NavController) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 100.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(daysOfWeek.size) {
            DayRow(dayOfWeek = daysOfWeek[it], navController)
        }
    }
}

@Composable
fun DayRow(dayOfWeek: String, navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
            .clickable {navController.navigate(Screen.DietDetailScreen.withArgs(dayOfWeek))  }
    ) {
        Text(text = dayOfWeek, color = Color.Black)
    }
}