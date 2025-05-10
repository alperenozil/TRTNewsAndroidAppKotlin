## 🎥 Project Demo

Click below to watch the video
[![Tanıtım Videosu](https://img.youtube.com/vi/3KTQF4SONTo/0.jpg)](https://www.youtube.com/shorts/3KTQF4SONTo)

## **TRT Android Assignment Project Documentation**

### **1. Introduction**

This Android project is built using the **MVVM pattern** combined with **Clean Architecture** principles and utilizes **Jetpack Compose** for the UI layer. The goal of this architecture is to ensure scalability, maintainability, and testability of the application by separating concerns into distinct layers.

---

### **2. Project Structure**

This project follows a modular structure based on Clean Architecture, where the app is divided into multiple modules that each serve a specific purpose. The main modules are:

* **data**: Manages data operations (API, database, repositories).
* **domain**: Contains the business logic and domain models.
* **ui**: Contains the Android-specific code (UI, activities, views).
* **di**: Provides database, network and AI modules via Hilt.
* **common**: Utility classes and constants used across all modules.

---

### **3. Architecture Overview**

This project adopts **Clean Architecture** principles, which divide the codebase into layers, each with its own responsibilities:

1. **Presentation Layer (UI)**:

   * **View**: The `@Composable` functions in Jetpack Compose represent the UI elements.
   * **ViewModel**: The `ViewModel` handles UI-related logic and communicates with the domain layer.

2. **Domain Layer**:

   * Contains business logic and use cases. This layer is independent of Android-specific components.
   * **Use Cases**: Encapsulate specific actions or operations in the app (e.g., fetching data, saving data).
   * **Entities**: Represent business models used across the app.

3. **Data Layer**:

   * Manages data from external sources (network, database).
   * **Repository**: Acts as a mediator between the domain layer and data sources (network, database).
   * **Data Sources**: Responsible for interacting with APIs and databases.

---

### **4. Layers and Responsibilities**

#### **4.1 Presentation Layer (UI)**

The presentation layer is where the UI resides. It contains **Jetpack Compose UI components** and the **ViewModel**.

* **Composables**: UI components defined using Jetpack Compose (`@Composable` functions).
* **ViewModel**: Responsible for managing UI state, handling user input, and communicating with use cases in the domain layer.

```kotlin
@Composable
fun NewsScreen(navController: NavController, viewModel: NewsViewModel = hiltViewModel()) {
    val newsState = viewModel.newsState.collectAsState()
    val input: MutableState<String> = remember { mutableStateOf("") }
    val searchKeyword: MutableState<String> = remember { mutableStateOf("") }
    Column(
        Modifier ...
            
```

#### **4.2 Domain Layer**

The domain layer contains use cases, which are responsible for handling business logic. The entities represent the core data models.

* **UseCase**: Each use case represents an action that can be performed in the app, e.g., "Get News Details"

```kotlin
class GetNewsDetailsUseCase @Inject constructor(
    private val openAiRepository: OpenAiRepository
) {
    suspend fun summarize(urlPath: String): Flow<Resource<String>> = openAiRepository.summarizeText(urlPath)
}
```

#### **4.3 Data Layer**

The data layer interacts with APIs, databases, or local storage to provide data to the domain layer.

* **Repositories**: Act as a bridge between the domain layer and data sources (network, database).

```kotlin
class NewsRepositoryImpl @Inject constructor(
    private val frenchApi: FrenchApi,
    private val arabicApi: ArabicApi,
    private val balkanApi: BalkanApi,
    private val albanianApi: AlbanianApi,
    private val macedonianApi: MacedonianApi,
    private val russianApi: RussianApi,
    private val germanApi: GermanApi,
    private val dao: NewsDao
) : NewsRepository {
    override suspend fun getNews(): Flow<Resource<List<News>>> =
        flow {
```

---

### **5. Data Flow**

1. **UI** triggers an action, for example, a button click or screen load.
2. The **ViewModel** communicates with the appropriate **UseCase** to fetch or manipulate data.
3. The **UseCase** uses the repository in the **Data Layer** to fetch the data.
4. The repository communicates with the necessary data sources (e.g., remote API, local database).
5. The **UseCase** returns the data or result back to the **ViewModel**.
6. The **ViewModel** updates the UI state, which is observed by the **Composable** to update the UI.

---

### **6. Dependency Injection**

This project uses **Hilt** for dependency injection to manage dependencies across different layers.

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object OpenAiModule {
    @Provides
    @Singleton
    fun provideOpenAiApi(): OpenAiApi = Retrofit.Builder()
        .baseUrl(URL_OPEN_AI)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient()).build().create(OpenAiApi::class.java)

    ...
}
```

---

### **7. Testing Strategy**

* **Unit Tests**: Tests the business logic in the **Domain** layer.
* **Integration Tests**: Tests the interaction between layers (e.g., ViewModel, UseCase, Repository).

Testing libraries used:

* **JUnit** for unit testing.
* **MockK** for mocking dependencies.
* **Hilt** for dependency injection in tests.

---

### **8. Jetpack Compose**

Jetpack Compose is used for building the UI in a declarative manner. It provides a clean and intuitive way to design UIs with minimal boilerplate.

* **State Management**: The state of the UI is managed using `State` and `StateFlow`.
* **Reactivity**: Compose automatically re-composes the UI when the state changes.
* **Navigation**: `NavController` from Jetpack Compose Navigation is used for managing screen transitions.

Example:

```kotlin
fun BottomNavBar() {
    val rootNavController = rememberNavController()
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            BottomAppBar { ...
```

---

### **9. Libraries Used**
* **Open AI**: For text summarizations and content recommendations.
* **Google Translate Api**: For text translations.
* **Jetpack Compose**: For UI development.
* **Hilt**: For dependency injection.
* **Retrofit**: For network operations.
* **Room**: For local data storage.
* **Coroutines**: For asynchronous operations.
* **Flow**: For reactive streams.
* **JUnit & MockK**: For testing.

---

### **10. Conclusion**

This project structure adheres to modern Android development practices, ensuring that the codebase is maintainable, scalable, and testable. By using **MVVM**, **Clean Architecture**, and **Jetpack Compose**, the project is modular, decoupled, and responsive to change.

---

### **11. Future Considerations**

* Implementing additional checks for languages in the database to prevent re-translating every time when user changes the language.
