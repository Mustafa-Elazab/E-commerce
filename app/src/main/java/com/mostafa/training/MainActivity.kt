package com.mostafa.training

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mostafa.training.ui.screens.cart.cartRoute
import com.mostafa.training.ui.screens.category.categoryScreenRoute
import com.mostafa.training.ui.screens.contact.contactScreenRoute
import com.mostafa.training.ui.screens.faqs.faqsScreenRoute
import com.mostafa.training.ui.screens.favorites.favoritesScreenRoute
import com.mostafa.training.ui.screens.home.homeRoute
import com.mostafa.training.ui.screens.notification.notificationsRoute
import com.mostafa.training.ui.screens.product.productsScreenRoute
import com.mostafa.training.ui.screens.product_detail.productDetailRoute
import com.mostafa.training.ui.screens.productsByCategory.productsByCategoryScreenRoute
import com.mostafa.training.ui.screens.profile.profileRoute
import com.mostafa.training.ui.screens.search.searchRoute
import com.mostafa.training.ui.screens.splash.splashScreenRoute
import com.mostafa.training.ui.theme.AccentColor
import com.mostafa.training.ui.theme.BaseColor
import com.mostafa.training.ui.theme.SecondaryTextColor
import com.mostafa.training.ui.theme.TrainingTheme
import com.mostafa.training.ui.theme.WhiteColor
import com.mostafa.training.ui.theme.robotoBold
import com.mostafa.training.ui.theme.robotoMedium
import com.mostafa.training.ui.theme.robotoRegular
import com.mostafa.training.utils.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrainingTheme {
                ChangeStatusBarColor()
                BottomNavScreen()
            }
        }
    }

}


@Composable
@Stable
private fun ChangeStatusBarColor() {
    val systemUiController = rememberSystemUiController()
    val isDark = isSystemInDarkTheme()
    LaunchedEffect(key1 = true) {
        if (isDark) {
            systemUiController.setStatusBarColor(color = Gray, darkIcons = true)
        } else {
            systemUiController.setStatusBarColor(color = White, darkIcons = true)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            val visibility = currentRoute(navController = navController) in listOf(
                Screen.Home.route,
                Screen.Search.route,
                Screen.Cart.route,
                Screen.Profile.route
            )
            BottomBar(navController, visibility)
        },
        contentWindowInsets = WindowInsets.safeContent,
        containerColor = BaseColor
    ) {
        AppNavGraph(navController = navController, it.calculateBottomPadding())
    }
}

@Composable
fun AppNavGraph(navController: NavHostController, calculateBottomPadding: Dp) {
    NavHost(navController = navController, startDestination = "splashScreen") {
        homeRoute(
            calculateBottomPadding,
            navController
        )
        searchRoute(
            calculateBottomPadding = calculateBottomPadding,
            navController = navController
        )
        cartRoute(
            navController = navController,
            calculateBottomPadding = calculateBottomPadding
        )
        profileRoute(
            navController = navController,
            calculateBottomPadding = calculateBottomPadding
        )
        productDetailRoute(navController = navController)
        notificationsRoute(navController = navController)
        productsScreenRoute(navController = navController)
        categoryScreenRoute(navController = navController)
        productsByCategoryScreenRoute(navController = navController)
        favoritesScreenRoute(navController = navController)
        faqsScreenRoute(navController = navController)
        contactScreenRoute(navController = navController)
        splashScreenRoute(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController, visibility: Boolean) {
    val screens = listOf<Screen>(
        Screen.Home,
        Screen.Search,
        Screen.Cart,
        Screen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if (visibility) {
        Surface(
            shadowElevation = 8.dp,
        ) {
            NavigationBar(
                containerColor = White,
                contentColor = AccentColor,
            ) {
                screens.forEach { screen ->
                    BottomItem(
                        screen,
                        navController,
                        currentDestination
                    )
                }
            }
        }
    }
}


@Composable
fun RowScope.BottomItem(
    screen: Screen,
    navController: NavHostController,
    currentNavDestination: NavDestination?
) {

    val selected = currentNavDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    NavigationBarItem(
        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = AccentColor,
            unselectedTextColor = SecondaryTextColor,
            indicatorColor = WhiteColor,
            unselectedIconColor = SecondaryTextColor,
            selectedIconColor = AccentColor,
        ),
        icon = {
            if (selected) {
                BottomBarIcon(
                    screen.selectedIcon,
                    screen.title
                )
            } else {
                BottomBarIcon(icon = screen.unSelectedIcon, contentDescription = screen.title)
            }
        },
        label = {
            Text(
                text = stringResource(id = screen.title),
                fontWeight = FontWeight.Bold,
                fontFamily = robotoBold
            )
        },
        alwaysShowLabel = true,
        selected = selected, onClick = {
            navController.navigate(screen.route) {
                navController.graph.startDestinationRoute?.let {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true


                }
            }
        })

}

@Composable
fun BottomBarIcon(
    @DrawableRes icon: Int,
    @StringRes contentDescription: Int
) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = stringResource(id = contentDescription)
    )
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}


//@Composable
//fun NavigatePage() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
//        composable(route = Screen.LoginScreen.route) {
//            LoginScreen(navController = navController)
//        }
//        composable(route = Screen.OtpScreen.route) {
//            OtpScreen(navController = navController)
//        }
//    }
//
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen(navController: NavController) {

    val context = LocalContext.current
    var otpValue: String? = null
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NormalTextComponent(
                value = "Check Your Phone , We Have Sent \n You The Code At",
                fontSize = 12,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(top = 2.dp))
            ClickableText(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Blue,
                        fontFamily = robotoBold
                    )
                ) {
                    append("+20155465611")
                }
            }, onClick = {

            })
            Spacer(modifier = Modifier.padding(top = 16.dp))
            ClickableText(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Red,
                        fontFamily = robotoBold
                    )
                ) {
                    append("Not My Phone Number")
                }
            }, onClick = {

            })
            Spacer(modifier = Modifier.padding(top = 16.dp))
            //otp
            OtpTextField(modifier = Modifier, length = 4, onFilled = {
                otpValue = it
            })
            Spacer(modifier = Modifier.padding(top = 16.dp))
            NormalTextComponent(
                value = "Didn't Receive The Code ?",
                fontSize = 12,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            ClickableText(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Blue,
                        fontFamily = robotoBold
                    )
                ) {
                    append("Send")
                }
            }, onClick = {

            })
            Spacer(modifier = Modifier.padding(top = 16.dp))
            NormalTextComponent(
                value = "00:00",
                fontSize = 12,
                textAlign = TextAlign.Center
            )

            CenteredButton(onClick = { }, text = "Next")
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberInputField(
    value: String,
    onValueChange: (String) -> Unit,
    title: String,
    modifier: Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {

        }),
        placeholder = {
            Text(
                text = title,
                fontFamily = robotoRegular
            )
        },

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = White,
            unfocusedBorderColor = White,

            )
    )
}

@Composable
fun NextButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Black,
            contentColor = White
        )
    ) {
        Text(text = "Click Me")
    }
}


@Composable
fun NormalTextComponent(
    value: String,
    fontSize: Int,
    textAlign: TextAlign,
    textColor: Color? = Black,
    fontFamily: FontFamily? = robotoRegular,
    fontWeight: FontWeight? = FontWeight.Normal
) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = fontSize.sp,
            color = textColor!!,
            fontWeight = fontWeight,
            fontStyle = FontStyle.Normal,
            fontFamily = fontFamily
        ),
        textAlign = textAlign
    )
}

@Composable
fun CenteredButton(
    onClick: () -> Unit,
    text: String
) {
    Button(
        modifier = Modifier.fillMaxWidth(.6f),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue,
            contentColor = White
        )
    ) {
        Text(text = text)
    }
}


@Composable
fun VerticalLine() {
    Box(
        modifier = Modifier
            .fillMaxHeight(.8f)
            .padding(top = 1.dp, bottom = 1.dp)
            .size(width = 1.dp, height = 10.dp)
            .background(Black)
    )
}


@Composable
fun LoginScreen(navController: NavController) {
    val phoneNumber = remember {
        mutableStateOf("")
    }



    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5F)
        ) {
            Image(
                painter = painterResource(id = R.drawable.car),
                contentDescription = "Login Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                .background(White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                NormalTextComponent(
                    "Hello Nice To Meet You!",
                    fontSize = 12,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(top = 2.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(), contentAlignment = Alignment.TopStart
                ) {
                    SpannableTextExample(fText = "Keep Moving With", sText = " InDrive")
                }


                Spacer(modifier = Modifier.padding(top = 20.dp))

                Box(
                    modifier = Modifier
                        .height(56.dp)
                        .padding(start = 4.dp, end = 4.dp)
                        .fillMaxWidth()
                        .border(
                            border = BorderStroke(.2.dp, Black),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.eg_flag),
                            contentDescription = "flag",
                            modifier = Modifier.fillMaxWidth(.1f)
                        )


                        Text(
                            modifier = Modifier.fillMaxWidth(.1f),
                            text = "+20", style = TextStyle(
                                fontFamily = robotoBold,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        VerticalLine()

                        NumberInputField(
                            value = phoneNumber.value,
                            onValueChange = { phoneNumber.value = it },
                            title = "Phone",
                            modifier = Modifier
                                .background(
                                    color = White
                                )
                                .border(border = BorderStroke(0.dp, White))
                        )

                    }
                }


                Spacer(modifier = Modifier.padding(top = 20.dp))

                CenteredButton(text = "Next", onClick = {

                })


                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp),
                    contentAlignment = Alignment.BottomCenter
                )
                {
                    ClickableTermsAndPolicy()
                }


            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    length: Int,
    onFilled: (code: String) -> Unit
) {

    var code: List<Char> by remember {
        mutableStateOf(listOf())
    }

    val focusRequesters: List<FocusRequester> = remember {
        val temp = mutableListOf<FocusRequester>()
        repeat(length) {
            temp.add(FocusRequester())
        }
        temp
    }

    Row(modifier = Modifier.height(50.dp)) {
        (0 until length).forEach { index ->
            OutlinedTextField(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .focusOrder(focusRequester = focusRequesters[index]) {
                        focusRequesters[index + 1].requestFocus()
                    },
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Center, color = Black
                ),
                singleLine = true,
                value = code.getOrNull(index = index)?.takeIf {
                    it.isDigit()
                }?.toString() ?: "",
                onValueChange = { value: String ->
                    if (focusRequesters[index].freeFocus()) {
                        val temp = code.toMutableList()
                        if (value == "") {
                            if (temp.size > index) {
                                temp.removeAt(index)
                                code = temp
                                focusRequesters.getOrNull(index - 1)?.requestFocus()
                            }
                        } else {
                            if (code.size > index) {
                                temp[index] = value.getOrNull(0) ?: ' '
                            } else {
                                temp.add(value.getOrNull(0) ?: ' ')
                                code = temp
                                focusRequesters.getOrNull(
                                    index + 1
                                )?.requestFocus() ?: onFilled(
                                    code.joinToString(separator = "")
                                )
                            }
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
            )
            Spacer(modifier = modifier.width(15.dp))
        }
    }

}


@Composable
fun ClickableTermsAndPolicy() {
    val context = LocalContext.current
    val terms = "Terms Of Services"
    val policy = "Privacy Policy"

    val defaultStyle = SpanStyle(
        color = Black,
        fontSize = 16.sp,
        fontFamily = robotoMedium,
    )
    val linkStyle = SpanStyle(
        color = Blue,
        fontSize = 16.sp,
        fontFamily = robotoMedium
    )

    val annotatedString = buildAnnotatedString {
        withStyle(defaultStyle) {
            append("By Creating An Account, You Agree To Our ")
        }
        pushStringAnnotation("Link", terms)
        withStyle(style = linkStyle) {
            append(terms)
        }
        pop()
        withStyle(defaultStyle) {
            append(" And ")
        }
        pushStringAnnotation("Link", policy)
        withStyle(style = linkStyle) {
            append(policy)
        }
        pop()
    }

    ClickableText(

        text = annotatedString,
        style = TextStyle(textAlign = TextAlign.Center),
        onClick = { offset ->
            val annotations =
                annotatedString.getStringAnnotations("Link", start = offset, end = offset)
            annotations.firstOrNull()?.let { annotation ->
                when (annotation.item) {
                    terms -> {
                        Toast.makeText(context, "first", Toast.LENGTH_SHORT).show()
                    }

                    policy -> {
                        Toast.makeText(context, "second", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    )
}


@Composable
fun SpannableTextExample(fText: String, sText: String) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        ) {
            append(fText)
        }
        withStyle(
            style = SpanStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        ) {
            append(sText)
        }
    }

    Text(text = annotatedString)
}


