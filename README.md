# 🎬 Movie App Task

This project is an Android application that displays a list of movies currently playing in cinemas. The app includes features such as searching for movies, viewing detailed movie information, and managing favorite movies.

## 📱 Features

### 🎥 MoviesFragment
- **Current Movies Display:** Shows a list of movies currently playing in cinemas.
- **Favorites Section:** Displays movies that have been added to the favorites list. All movies are stored in a local database.
- **Favorites Management:** Movies can be marked as favorites. The `isUserFavoriteMovie` variable in the movie model is used to track this, with values of 1 for favorite and 0 for non-favorite.

### 🔍 SearchViewFragment
- **Movie Search:** Enables searching for movies using a `SearchView` on the search screen.
- **Real-Time Results:** Search results are automatically displayed in a list as the user types.

### 📝 MovieDetailsFragment
- **Detailed Movie Information:** Provides detailed information about a movie, including its name, release date, trailer video, genres, poster, overview, cast, and more.

## 📷 Screenshots

Here are some screenshots of the application:

### Home Screen
![Home Screen](https://github.com/Fatih-Baser/MovieAndroidAppTask/blob/main/Screenshots/screen1.jpeg)

### Search Screen
![Search Screen](https://github.com/Fatih-Baser/MovieAndroidAppTask/blob/main/Screenshots/screen2.png)

### Movie Details Screen
![Movie Details Screen](https://github.com/Fatih-Baser/MovieAndroidAppTask/blob/main/Screenshots/screen3.png)


### 🧪 Unit Testing
- **Room Database Operations:** Includes unit tests for key database operations such as inserting, retrieving, and updating movies.

## 🛠️ Technologies Used

### Kotlin
- The primary programming language used in this app.

### AndroidX Libraries
- **Core KTX:** Provides Kotlin extensions for Android.
- **AppCompat:** Ensures compatibility with older Android versions.
- **ConstraintLayout:** Helps build complex layouts with a flat view hierarchy.
- **Legacy Support:** Provides backward compatibility for older APIs.

### Jetpack Libraries
- **ViewModel:** Manages UI-related data in a lifecycle-conscious way.
- **LiveData:** Holds data that is observable and lifecycle-aware.
- **Navigation Component:** Handles navigation within the app.
- **Room:** Provides an abstraction layer over SQLite to allow fluent database access.

### Dependency Injection
- **Dagger Hilt:** Simplifies dependency injection in Android apps.

### Networking
- **Retrofit:** A type-safe HTTP client for Android and Java.
- **Gson Converter:** Converts JSON data into Java objects using Gson.

### Coroutines
- **Kotlin Coroutines:** Simplifies asynchronous programming in Android.

### Image Loading
- **Glide:** Efficient image loading and caching library.

### Logging
- **Timber:** A flexible and easy-to-use logging library for Android.

### UI Components
- **Material Components:** Implements Material Design components like buttons, text fields, etc.

### YouTube Player
- **Android YouTube Player:** Integrates YouTube videos into the app.

### Testing
- **JUnit:** A framework for unit testing.
- **Truth:** A library for making assertions in tests.
- **Espresso:** A UI testing framework for Android.
- **AndroidX Testing:** Provides additional testing utilities for Android.

### View Binding
- Simplifies interactions with UI elements by generating binding classes from XML layout files.
