# Walkthrough - Clean Architecture Refactoring

Refactored the Compose Multiplatform food delivery application into **Clean Architecture** principles across Android, Desktop, and iOS.

## Changes

### Shared Module (`shared/src/commonMain/kotlin/com/imthiyas/cmpdemo`)

#### [NEW] Domain Layer (`domain/`)
- **`model/FoodModels.kt`**: Core business entities (`FoodItem`, `CartItem`, `Category`, `BottomNavTab`).
- **`repository/FoodRepository.kt`**: Repository interface defining data contracts (`getFoodItems()`, `getCategories()`).
- **`usecase/GetFoodItemsUseCase.kt`**: Business use case for fetching food items.
- **`usecase/GetCategoriesUseCase.kt`**: Business use case for fetching food categories.

#### [NEW] Data Layer (`data/`)
- **`repository/FoodRepositoryImpl.kt`**: Concrete implementation of `FoodRepository` supplying rich Zomato sample restaurant and dish data.

#### [NEW] Presentation Layer (`presentation/`)
- **`viewmodel/HomeViewModel.kt`**: Lifecycle ViewModel managing UI state (active tab, search query, selected category/filter, cart items) and calling domain use cases.
- **`ui/HomeScreen.kt`**: Compose UI rendering the Zomato-inspired food app interface observing `HomeViewModel`.

#### [MODIFY] App Entrypoint (`App.kt`)
- Updated `App` to render `com.imthiyas.cmpdemo.presentation.ui.HomeScreen`.

## Verification Results

### Automated Tests
- Successfully compiled shared and desktop targets:
  - `./gradlew :desktopApp:classes` -> **Build successful**
