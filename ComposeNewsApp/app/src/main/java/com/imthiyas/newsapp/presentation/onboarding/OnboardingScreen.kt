package com.imthiyas.newsapp.presentation.onboarding

import android.widget.Space
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imthiyas.newsapp.presentation.Dimens.PageIndicatorWidth
import com.imthiyas.newsapp.presentation.Dimens.mediumPadding2
import com.imthiyas.newsapp.presentation.common.NewsButton
import com.imthiyas.newsapp.presentation.common.NewsTextButton
import com.imthiyas.newsapp.presentation.onboarding.components.OnboardingPage
import com.imthiyas.newsapp.presentation.onboarding.components.PageIndicator
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable

fun OnboardingScreen(
    event: (OnBoardingEvent)-> Unit,
    pagerState: PagerState = rememberPagerState(initialPage = 0) { pages.size }
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // val pagerState = rememberPagerState(initialPage = 0) { pages.size }

        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    3 -> listOf("", "Welcome")
                    else -> listOf("", "")
                }
            }
        }


        HorizontalPager(state = pagerState) { index ->
            OnboardingPage(page = pages[index])
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = mediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )



            Row(verticalAlignment = Alignment.CenterVertically) {

                val scope = rememberCoroutineScope()

                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(text = buttonState.value[0], onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }

                    })
                }

                NewsButton(text = buttonState.value[1], onClick = {
                    scope.launch {
                        if (pagerState.currentPage == 3) {
                            event(OnBoardingEvent.saveAppEntry)
                        } else {
                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }
                })
            }
        }


        Spacer(modifier = Modifier.weight(0.2f))
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PreviewOnboardingScreen0() {
    val pagerState = rememberPagerState(initialPage = 0) { pages.size }
    OnboardingScreen(pagerState)
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PreviewOnboardingScreen1() {
    val pagerState = rememberPagerState(initialPage = 1) { pages.size }
    OnboardingScreen(pagerState)
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PreviewOnboardingScreen2() {
    val pagerState = rememberPagerState(initialPage = 2) { pages.size }
    OnboardingScreen(pagerState)
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PreviewOnboardingScreen3() {
    val pagerState = rememberPagerState(initialPage = 3) { pages.size }
    OnboardingScreen(pagerState)
}