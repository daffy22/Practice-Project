package com.acme.homehealthy.screens.composableScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.acme.homehealthy.R
import com.acme.homehealthy.data.models.User
import com.acme.homehealthy.resources.BotonNavContent
import com.acme.homehealthy.screens.BottomNav
import com.acme.homehealthy.ui.theme.*
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun profileScreen(user: User, navController: NavController) {
    Box(
        modifier = Modifier
            .background(DeepBlack)
            .fillMaxSize()
    ) {
        profileDataSection(user)
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
            initialSelectedIndex = 2
        )
    }
}


@Composable
fun profileDataSection(user: User) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, end = 15.dp, top = 35.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.Transparent)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(TransparentBlack)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /*Image(
                painter = rememberImagePainter(
                    data = imgUrl
                ),
                contentDescription = trainings.name,
                modifier = Modifier
                    .size(200.dp, 120.dp)
                    .clip(RoundedCornerShape(25.dp))
            )*/
            GlideImage(
                imageModel = "https://800noticias.com/cms/wp-content/uploads/2019/08/Dwayne-Johnson.jpg",
                contentScale = ContentScale.Crop,
                circularReveal = CircularReveal(duration = 5000),
                modifier = Modifier
                    .size(300.dp, 320.dp)
                    .clip(RoundedCornerShape(25.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TransparentBlack)
                    .padding(bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(20.dp))


                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .padding(3.dp)
                        .clip(RoundedCornerShape(15.dp))
                ) {
                    Box(modifier = Modifier.background(DeepBlue)) {
                        Row() {
                            Text(
                                text = "Level of experience",
                                style = MaterialTheme.typography.caption,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(35.dp))

                            Text(
                                text = user.experience,
                                style = MaterialTheme.typography.caption,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(20.dp))


                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .padding(3.dp)
                        .clip(RoundedCornerShape(15.dp))
                ) {
                    Box(modifier = Modifier.background(DeepBlue)) {
                        Row() {
                            Text(
                                text = "Number of trainings per week",
                                style = MaterialTheme.typography.caption,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(60.dp))

                            Text(
                                text = user.diasEntrenamiento.toString(),
                                style = MaterialTheme.typography.caption,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(20.dp))


                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .padding(3.dp)
                        .clip(RoundedCornerShape(15.dp))
                ) {
                    Box(modifier = Modifier.background(DeepBlue)) {
                        Row() {
                            Text(
                                text = "GOAL",
                                style = MaterialTheme.typography.caption,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(100.dp))

                            Text(
                                text = user.objective,
                                style = MaterialTheme.typography.caption,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                        }

                    }
                }

            }
        }
    }
}