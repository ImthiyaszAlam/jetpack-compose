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
import com.imthiyas.newsapp.presentation.common.NewsTextButton
import com.imthiyas.newsapp.presentation.onboarding.components.OnboardingPage
import com.imthiyas.newsapp.presentation.onboarding.components.PageIndicator
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable

fun OnboardingScreen(
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val pagerSate = rememberPagerState(initialPage = 0) { pages.size }

        val buttonState = remember {
            derivedStateOf {
                when (pagerSate.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }


        HorizontalPager(state = pagerSate) { index ->
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
                selectedPage = pagerSate.currentPage
            )
        }


        Row(verticalAlignment = Alignment.CenterVertically) {

            val scope = rememberCoroutineScope()

            if (buttonState.value[0].isNotEmpty()) {
                NewsTextButton(text = buttonState.value[0], onClick = {
                    scope.launch {
                        pagerSate.animateScrollToPage(page = pagerSate.currentPage - 1)
                    }

                })
            }

            NewsTextButton(text = buttonState.value[1], onClick = {
                scope.launch {
                    if (pagerSate.currentPage == 3) {
                        //TODO: Navigate to Home Screen
                    } else {
                        pagerSate.animateScrollToPage(page = pagerSate.currentPage + 1)
                    }
                }
            })
        }


    }
}

@Preview
@Composable
private fun PreviewOnboardingScreen() {
    OnboardingScreen()
}