# Clean Architecture Refactoring

Refactor the CMPDemo app into **Clean Architecture** principles, dividing the codebase into three distinct layers (**Domain**, **Data**, and **Presentation**) to ensure clear separation of concerns, testability, and scalability.

## User Review Required

> [!IMPORTANT]
> This refactoring will restructure `shared/src/commonMain/kotlin/com/imthiyas/cmpdemo/` into:
> - **Domain Layer**: Core business models, repository interfaces, and use cases (pure Kotlin, zero UI dependencies).
> - **Data Layer**: Repository implementations and data sources.
> - **Presentation Layer**: `HomeViewModel` (Jetpack Lifecycle ViewModel) and Compose UI components.

## Open Questions

- Should we keep the existing Zomato-inspired UI features during refactoring? Yes, all Zomato features (search, filters, cart management, 4 bottom nav tabs, Gold banner, etc.) will be fully preserved and driven through the new Clean Architecture `HomeViewModel` and Use Cases.

## Proposed Changes

### Shared Module (`shared/src/commonMain/kotlin/com/imthiyas/cmpdemo`)

#### [NEW] Domain Layer
- **`domain/model/FoodModels.kt`**: `FoodItem`, `CartItem`, `Category`, `BottomNavTab`.
- **`domain/repository/FoodRepository.kt`**: Interface defining `getFoodItems()` and `getCategories()`.
- **`domain/usecase/GetFoodItemsUseCase.kt`**: Use case for retrieving & filtering food items.
- **`domain/usecase/GetCategoriesUseCase.kt`**: Use case for retrieving food categories.

#### [NEW] Data Layer
- **`data/repository/FoodRepositoryImpl.kt`**: Concrete implementation of `FoodRepository` providing sample data.

#### [NEW] Presentation Layer
- **`presentation/viewmodel/HomeViewModel.kt`**: Manages app state (selected tab, search query, selected category, selected filter, cart items) and exposes actions.
- **`presentation/ui/HomeScreen.kt`**: Composable UI observing `HomeViewModel` and rendering the Zomato-grade food app interface.

## Verification Plan

### Automated Tests
- Build verification via Gradle task: `./gradlew :desktopApp:classes`

### Manual Verification
- Run the app (Desktop or Android) and verify that all tab switching, cart additions, search, and category filtering operate smoothly via the Clean Architecture ViewModel and Use Cases.
