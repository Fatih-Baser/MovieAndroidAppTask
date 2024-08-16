MoviesFragment 
A collection of movies currently playing in Cinemas is displayed.Also added to this screen are movies added to Favorites
All movies are saved in local database.When any movie was added to favorites, the "isUserFavoriteMovie" variable in my movies model was given a value of 1 or 0. In this way, the favorites section was created.

SearchViewFragment
Movie searching was enabled with SearchView on the SearchMovie screen. The results were automatically displayed directly in the list during the search.

MovieDetailsFragment
Name, release date, video of the trailer, genres, poster, overview, cast and more added


Implemented unit tests for key Room database operations: insert, get, and update.

Technologies Used
1. Kotlin
Description: Primary programming language for the app.
2. AndroidX Libraries
Core KTX
AppCompat
ConstraintLayout
Legacy Support
3. Jetpack Libraries
ViewModel
LiveData
Navigation Component
Room
4. Dependency Injection
Dagger Hilt
5. Networking
Retrofit
Gson Converter
6. Coroutines
Kotlin Coroutines
7. Image Loading
Glide
8. Logging
Timber
9. UI Components
Material Components
10. YouTube Player
Android YouTube Player
11. Testing
JUnit
Truth
Espresso
AndroidX Testing
12. View Binding
View Binding
