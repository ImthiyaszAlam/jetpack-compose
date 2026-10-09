package com.imthiyas.cmpdemo.data.repository

import com.imthiyas.cmpdemo.domain.model.Category
import com.imthiyas.cmpdemo.domain.model.FoodItem
import com.imthiyas.cmpdemo.domain.repository.FoodRepository

class FoodRepositoryImpl : FoodRepository {
    private val categories = listOf(
        Category("1", "Burgers", "🍔"),
        Category("2", "Pizza", "🍕"),
        Category("3", "Sushi", "🍣"),
        Category("4", "Desserts", "🍰"),
        Category("5", "Drinks", "🧋"),
        Category("6", "Biryani", "🍲")
    )

    private val foodItems = listOf(
        FoodItem(
            id = "1",
            name = "The Ultimate Truffle Burger",
            description = "Double smashed Angus beef patty, truffle mayo, caramelised onions & melted Swiss cheese.",
            price = 9.99,
            category = "Burgers",
            rating = 4.8,
            discountText = "60% OFF up to $12",
            deliveryTime = "16 mins",
            distance = "1.4 km",
            cuisines = "Burgers, American, Fast Food",
            isBestseller = true,
            emoji = "🍔"
        ),
        FoodItem(
            id = "2",
            name = "Woodfire Pepperoni Feast",
            description = "Hand-tossed sourdough crust loaded with spicy pepperoni, hot honey & fresh mozzarella.",
            price = 14.49,
            category = "Pizza",
            rating = 4.9,
            discountText = "FLAT $5 OFF",
            deliveryTime = "22 mins",
            distance = "2.8 km",
            cuisines = "Pizza, Italian, Fast Food",
            isBestseller = true,
            emoji = "🍕"
        ),
        FoodItem(
            id = "3",
            name = "Dragon Roll & Salmon Nigiri",
            description = "Tempura shrimp, avocado, topped with fresh Atlantic salmon & spicy eel sauce.",
            price = 16.50,
            category = "Sushi",
            rating = 4.7,
            discountText = "20% OFF",
            deliveryTime = "28 mins",
            distance = "3.5 km",
            cuisines = "Japanese, Sushi, Seafood",
            isBestseller = false,
            emoji = "🍣"
        ),
        FoodItem(
            id = "4",
            name = "Belgian Chocolate Molten Cake",
            description = "Warm decadent chocolate cake with a gooey molten center served with vanilla bean gelato.",
            price = 6.99,
            category = "Desserts",
            rating = 4.9,
            discountText = "BOGO Free",
            deliveryTime = "14 mins",
            distance = "0.9 km",
            cuisines = "Bakery, Desserts, Ice Cream",
            isBestseller = true,
            emoji = "🍰"
        ),
        FoodItem(
            id = "5",
            name = "Royal Hyderabadi Dum Biryani",
            description = "Aromatic basmati rice cooked with tender marinated chicken, saffron, and secret royal spices.",
            price = 12.99,
            category = "Biryani",
            rating = 4.8,
            discountText = "50% OFF",
            deliveryTime = "25 mins",
            distance = "3.1 km",
            cuisines = "Biryani, Mughlai, North Indian",
            isBestseller = true,
            emoji = "🍲"
        ),
        FoodItem(
            id = "6",
            name = "Boba Brown Sugar Milk Tea",
            description = "Freshly brewed Assam black tea with chewy tapioca pearls and creamy Hokkaido milk.",
            price = 5.49,
            category = "Drinks",
            rating = 4.6,
            discountText = "30% OFF",
            deliveryTime = "10 mins",
            distance = "0.6 km",
            cuisines = "Beverages, Bubble Tea, Cafe",
            isBestseller = false,
            emoji = "🧋"
        )
    )

    override suspend fun getFoodItems(): List<FoodItem> = foodItems
    override suspend fun getCategories(): List<Category> = categories
}
