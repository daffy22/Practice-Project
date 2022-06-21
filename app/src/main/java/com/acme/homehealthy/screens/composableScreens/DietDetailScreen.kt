package com.acme.homehealthy.screens.composableScreens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.acme.homehealthy.R
import com.acme.homehealthy.data.models.Diet
import com.acme.homehealthy.screens.Screen
import com.acme.homehealthy.screens.trainingsView
import com.acme.homehealthy.ui.theme.*
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MainDietDetailScreen(dayOfWekk: String?, _diets: List<Diet>, navController: NavController) {
    Box() {



        dietList(_diets, dayOfWekk!!, navController)
    }
}


@Composable
fun dietList(diets: List<Diet>, day: String, navController: NavController) {
    for (diet in diets) {
        if (diet.day == day) {
            fietRow(diet, day, navController)
        }
    }

}

@Composable
fun fietRow(diet: Diet, day: String, navController: NavController) {
    Box(
        modifier = Modifier
            .background(Color.Black)
            .padding(20.dp)
            .clip(RoundedCornerShape(25.dp))
            .clipToBounds()
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.backicon),
            contentDescription = "sdsdsd",
            modifier = Modifier
                .clickable {
                    navController.navigate(Screen.DietScreen.route)
                }
                .width(40.dp)
                .height(40.dp)
                .background(Color.White)
        )
        Text(text = day)
        Box(
            modifier = Modifier
                .background(TransparentBlack)
        ) {
            Column {
                Row(


                ) {
                    GlideImage(
                        imageModel = diet.breakfastImg, modifier = Modifier
                            .size(200.dp, 220.dp)
                    )
                    Text(
                        text = diet.breackfast,
                        color = Gray,
                        fontStyle = FontStyle(5),
                        modifier = Modifier.padding(start = 15.dp, end =15.dp, top = 35.dp, bottom = 5.dp)
                    )
                    FoodStats(valueStat = 30, color = ProteinBar , statName = "Protein")
                    FoodStats(valueStat = 30, color = CarbsBar , statName = "Carbs")
                    FoodStats(valueStat = 30, color = FatBar , statName = "Fat")
                }
                Spacer(modifier = Modifier.height(25.dp))
                Row() {
                    GlideImage(
                        imageModel = diet.lunchImg, modifier = Modifier
                            .size(200.dp, 220.dp)
                    )
                    Text(text = diet.lunch)
                }
                Spacer(modifier = Modifier.height(25.dp))
                Row() {
                    GlideImage(
                        imageModel = diet.dinnerImg, modifier = Modifier
                            .size(200.dp, 220.dp)
                    )
                    Text(text = diet.dinner)
                }
            }

        }
    }
}

@Composable
fun FoodStats(
    valueStat: Int,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    color: Color,
    height: Dp = 28.dp,
    statName: String
){

    var animationPLayed by remember {mutableStateOf(false)}

    val currentPercent = animateFloatAsState(
        targetValue = if(animationPLayed){
            valueStat/100.toFloat()
        } else 0f,
        animationSpec = tween(
            animDuration,
            animDelay
        )
    )
    LaunchedEffect(key1 = true) {animationPLayed = true}

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(CircleShape)
            .background(Gray)
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(currentPercent.value)
                .clip(CircleShape)
                .background(color)
                .padding(horizontal = 10.dp)

        ){
            Text(text = statName,
            fontWeight = FontWeight.Light
                )
            Text(text = (currentPercent.value * 100).toInt().toString(),
            fontWeight = FontWeight.ExtraBold
                )

        }
    }
}