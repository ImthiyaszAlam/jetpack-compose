package com.imthiyas.cmpdemo.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imthiyas.cmpdemo.domain.model.BottomNavTab
import com.imthiyas.cmpdemo.domain.model.CartItem
import com.imthiyas.cmpdemo.domain.model.Category
import com.imthiyas.cmpdemo.domain.model.FoodItem
import com.imthiyas.cmpdemo.presentation.viewmodel.HomeViewModel

val ZomatoRed = Color(0xFFE23744)
val ZomatoGreen = Color(0xFF24963F)
val ZomatoDark = Color(0xFF1C1C1C)
val ZomatoSurface = Color(0xFFFFFFFF)
val ZomatoBackground = Color(0xFFF8F9FA)
val ZomatoGold = Color(0xFFFFD700)

fun formatPrice(price: Double): String {
    val cents = (price * 100).toInt()
    val dollars = cents / 100
    val remainder = kotlin.math.abs(cents % 100)
    val remainderStr = if (remainder < 10) "0$remainder" else "$remainder"
    return "$$dollars.$remainderStr"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel { HomeViewModel() }) {
    val selectedTab = viewModel.selectedTab
    val cartItems = viewModel.cartItems
    val selectedCategory = viewModel.selectedCategory
    val selectedFilter = viewModel.selectedFilter
    val searchQuery = viewModel.searchQuery
    val foodItems = viewModel.foodItems
    val categories = viewModel.categories

    val customColorScheme = lightColorScheme(
        primary = ZomatoRed,
        onPrimary = Color.White,
        primaryContainer = Color(0xFFFFECEE),
        onPrimaryContainer = ZomatoRed,
        surface = ZomatoSurface,
        background = ZomatoBackground
    )

    MaterialTheme(colorScheme = customColorScheme) {
        Scaffold(
            topBar = {
                Surface(
                    color = ZomatoSurface,
                    tonalElevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("📍", fontSize = 20.sp)
                                Spacer(modifier = Modifier.width(6.dp))
                                Column {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("Home", fontWeight = FontWeight.Black, fontSize = 15.sp, color = ZomatoDark)
                                        Text(" ▾", color = ZomatoRed, fontWeight = FontWeight.Bold)
                                    }
                                    Text("123 Silicon Valley, CA", fontSize = 11.sp, color = Color.Gray)
                                }
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    shape = CircleShape,
                                    color = Color(0xFFF0F0F0)
                                ) {
                                    Text("⚡", fontSize = 14.sp, modifier = Modifier.padding(8.dp))
                                }
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(ZomatoRed.copy(alpha = 0.1f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("👨‍💻", fontSize = 18.sp)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { viewModel.updateSearchQuery(it) },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Search restaurant, item or cuisine", color = Color.Gray, fontSize = 13.sp) },
                            leadingIcon = { Text("🔍", fontSize = 16.sp) },
                            trailingIcon = {
                                Row(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.padding(end = 8.dp)) {
                                    Text("|", color = Color.LightGray)
                                    Text("🎙️", fontSize = 16.sp)
                                    Text("🎛️", fontSize = 16.sp)
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = ZomatoRed,
                                unfocusedBorderColor = Color.LightGray.copy(alpha = 0.8f),
                                focusedContainerColor = Color(0xFFF9F9F9),
                                unfocusedContainerColor = Color(0xFFF9F9F9)
                            )
                        )
                    }
                }
            },
            bottomBar = {
                NavigationBar(
                    containerColor = ZomatoSurface,
                    tonalElevation = 8.dp
                ) {
                    BottomNavTab.entries.forEach { tab ->
                        val cartCount = cartItems.sumOf { it.quantity }
                        val isSelected = selectedTab == tab
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { viewModel.selectTab(tab) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = ZomatoRed,
                                selectedTextColor = ZomatoRed,
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray,
                                indicatorColor = ZomatoRed.copy(alpha = 0.12f)
                            ),
                            icon = {
                                Box {
                                    Text(
                                        text = when (tab) {
                                            BottomNavTab.ORDER -> "🍔"
                                            BottomNavTab.DINING -> "🥂"
                                            BottomNavTab.OFFERS -> "🏷️"
                                            BottomNavTab.PROFILE -> "👤"
                                        },
                                        fontSize = 22.sp
                                    )
                                    if (tab == BottomNavTab.ORDER && cartCount > 0) {
                                        Badge(
                                            modifier = Modifier.align(Alignment.TopEnd),
                                            containerColor = ZomatoRed
                                        ) {
                                            Text("$cartCount", color = Color.White, fontSize = 10.sp)
                                        }
                                    }
                                }
                            },
                            label = { Text(tab.title, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                when (selectedTab) {
                    BottomNavTab.ORDER -> OrderTabContent(
                        searchQuery = searchQuery,
                        selectedCategory = selectedCategory,
                        onCategorySelect = { viewModel.selectCategory(it) },
                        selectedFilter = selectedFilter,
                        onFilterSelect = { viewModel.selectFilter(it) },
                        foodItems = foodItems,
                        categories = categories,
                        onAddToCart = { viewModel.addToCart(it) }
                    )
                    BottomNavTab.DINING -> DiningTabContent()
                    BottomNavTab.OFFERS -> OffersTabContent(
                        cartItems = cartItems,
                        onRemove = { viewModel.removeFromCart(it) },
                        onAdd = { viewModel.addToCart(it.foodItem) },
                        onClear = { viewModel.clearCart() }
                    )
                    BottomNavTab.PROFILE -> ProfileTabContent()
                }
            }
        }
    }
}

@Composable
fun OrderTabContent(
    searchQuery: String,
    selectedCategory: String,
    onCategorySelect: (String) -> Unit,
    selectedFilter: String,
    onFilterSelect: (String) -> Unit,
    foodItems: List<FoodItem>,
    categories: List<Category>,
    onAddToCart: (FoodItem) -> Unit
) {
    val filteredItems = foodItems.filter { item ->
        val matchesCategory = selectedCategory == "All" || item.category == selectedCategory
        val matchesSearch = item.name.contains(searchQuery, ignoreCase = true) || item.cuisines.contains(searchQuery, ignoreCase = true)
        val matchesFilter = when (selectedFilter) {
            "Rating 4.0+" -> item.rating >= 4.0
            "Offers" -> item.discountText != null
            "Fast Delivery" -> (item.deliveryTime.take(2).trim().toIntOrNull() ?: 30) < 20
            "Bestsellers" -> item.isBestseller
            else -> true
        }
        matchesCategory && matchesSearch && matchesFilter
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(ZomatoBackground),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color(0xFFE8F5E9)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("⚡", fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Delivery in 16 mins • Live tracking", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                    }
                }
                Text("🛡️ Safe Delivery", fontSize = 11.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
            }
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ZomatoDark)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("👑 ZOMATO", fontSize = 12.sp, fontWeight = FontWeight.Black, color = ZomatoGold)
                            Text(" GOLD", fontSize = 12.sp, fontWeight = FontWeight.Black, color = Color.White)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Extra 30% OFF across 1,200+ spots", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text("Free delivery on all orders above $10", fontSize = 11.sp, color = Color.Gray)
                    }
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ZomatoGold),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text("Explore", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }

        item {
            Column {
                Text(
                    text = "What's on your mind?",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = ZomatoDark,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    val allCats = listOf(Category("0", "All", "🌟")) + categories
                    items(allCats) { cat ->
                        val isSelected = selectedCategory == cat.name || (cat.name == "All" && selectedCategory == "All")
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .width(74.dp)
                                .clickable { onCategorySelect(cat.name) }
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(68.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(if (isSelected) ZomatoRed.copy(alpha = 0.1f) else Color(0xFFF1F2F4))
                                    .border(
                                        width = if (isSelected) 2.dp else 0.dp,
                                        color = if (isSelected) ZomatoRed else Color.Transparent,
                                        shape = RoundedCornerShape(16.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(cat.emoji, fontSize = 30.sp)
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = cat.name,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) ZomatoRed else ZomatoDark,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val filters = listOf("All", "Rating 4.0+", "Offers", "Fast Delivery", "Bestsellers")
                items(filters) { filter ->
                    val isSelected = selectedFilter == filter
                    FilterChip(
                        selected = isSelected,
                        onClick = { onFilterSelect(filter) },
                        label = { Text(filter, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = ZomatoRed,
                            selectedLabelColor = Color.White,
                            containerColor = ZomatoSurface
                        )
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${filteredItems.size} delivery restaurants available",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = ZomatoDark
                )
                Text("Sort ↕", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
            }
        }

        items(filteredItems) { item ->
            LatestZomatoFoodCard(foodItem = item, onAddToCart = { onAddToCart(item) })
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun LatestZomatoFoodCard(foodItem: FoodItem, onAddToCart: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ZomatoSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(175.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFF0EF), Color(0xFFFFD6D8))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(foodItem.emoji, fontSize = 72.sp)

                if (foodItem.discountText != null) {
                    Surface(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(12.dp),
                        shape = RoundedCornerShape(6.dp),
                        color = ZomatoRed
                    ) {
                        Text(
                            text = foodItem.discountText,
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                if (foodItem.isBestseller) {
                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp),
                        shape = RoundedCornerShape(6.dp),
                        color = Color.Black.copy(alpha = 0.7f)
                    ) {
                        Text(
                            text = "⭐ BESTSELLER",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = foodItem.name,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = ZomatoDark,
                        modifier = Modifier.weight(1f)
                    )
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = ZomatoGreen
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("${foodItem.rating}", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            Text(" ★", color = Color.White, fontSize = 9.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(3.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = foodItem.cuisines,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "⏱️ ${foodItem.deliveryTime} • ${foodItem.distance}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = ZomatoDark
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = foodItem.description,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(10.dp))

                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = formatPrice(foodItem.price * 1.25),
                            fontSize = 11.sp,
                            color = Color.Gray,
                            textDecoration = TextDecoration.LineThrough
                        )
                        Text(
                            text = formatPrice(foodItem.price),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = ZomatoDark
                        )
                    }

                    Button(
                        onClick = onAddToCart,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ZomatoRed),
                        contentPadding = PaddingValues(horizontal = 22.dp, vertical = 6.dp)
                    ) {
                        Text("ADD +", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun DiningTabContent() {
    Column(
        modifier = Modifier.fillMaxSize().background(ZomatoBackground).padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🥂", fontSize = 56.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Text("Zomato Dining Out", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = ZomatoDark)
        Spacer(modifier = Modifier.height(6.dp))
        Text("Book tables at top rated venues & save up to 50% with Gold.", fontSize = 14.sp, color = Color.Gray, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
    }
}

@Composable
fun OffersTabContent(
    cartItems: List<CartItem>,
    onRemove: (CartItem) -> Unit,
    onAdd: (CartItem) -> Unit,
    onClear: () -> Unit
) {
    val subtotal = cartItems.sumOf { it.foodItem.price * it.quantity }
    val deliveryFee = if (cartItems.isEmpty()) 0.0 else 2.99
    val total = subtotal + deliveryFee

    if (cartItems.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize().background(ZomatoBackground).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("🛍️", fontSize = 56.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Text("Your Cart is Empty", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = ZomatoDark)
            Spacer(modifier = Modifier.height(6.dp))
            Text("Explore delicious dishes from the Order tab!", fontSize = 14.sp, color = Color.Gray)
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize().background(ZomatoBackground).padding(16.dp)
        ) {
            Text("Order Summary", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = ZomatoDark)
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(cartItems) { cartItem ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = ZomatoSurface)
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                Text(cartItem.foodItem.emoji, fontSize = 26.sp)
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(cartItem.foodItem.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                    Text(formatPrice(cartItem.foodItem.price), color = ZomatoRed, fontWeight = FontWeight.Bold)
                                }
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = { onRemove(cartItem) }) {
                                    Text("➖", fontSize = 12.sp)
                                }
                                Text("${cartItem.quantity}", fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp))
                                IconButton(onClick = { onAdd(cartItem) }) {
                                    Text("➕", fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ZomatoSurface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Item Total", color = Color.Gray)
                        Text(formatPrice(subtotal), fontWeight = FontWeight.Medium)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Delivery Partner Fee", color = Color.Gray)
                        Text(formatPrice(deliveryFee), fontWeight = FontWeight.Medium)
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("To Pay", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(formatPrice(total), fontWeight = FontWeight.Black, fontSize = 18.sp, color = ZomatoRed)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onClear,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ZomatoRed),
                        contentPadding = PaddingValues(vertical = 14.dp)
                    ) {
                        Text("Place Order • ${formatPrice(total)}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileTabContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ZomatoBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(ZomatoGold.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Text("👑", fontSize = 42.sp)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Alex Johnson", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = ZomatoDark)
            Spacer(modifier = Modifier.height(2.dp))
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = ZomatoGold.copy(alpha = 0.3f)
            ) {
                Text("⭐ ZOMATO GOLD VIP", fontSize = 11.sp, fontWeight = FontWeight.Black, color = Color(0xFF6B5300), modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp))
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ZomatoSurface)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("📍 Delivery Address", fontWeight = FontWeight.Bold, color = ZomatoDark)
                Text("123 Silicon Valley Road, Apt 4B, San Francisco, CA", color = Color.Gray, fontSize = 14.sp)
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ZomatoSurface)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("📦 Order History", fontWeight = FontWeight.Bold, color = ZomatoDark)
                Text("• The Ultimate Truffle Burger — Delivered", fontSize = 13.sp, color = Color.DarkGray)
                Text("• Woodfire Pepperoni Feast — Delivered", fontSize = 13.sp, color = Color.DarkGray)
            }
        }
    }
}
