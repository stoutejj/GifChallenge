About the Project

Project is using Giffy APIs to load trending gif and it's detail.

Architecture:
1. MVVM Architecture with ViewState
2. Repository pattern is used for fetching data

Libraries Used:
1. Lifecycle : For ViewModel and LiveData
2. Glide: For ImageLoading
3. Retrofit: For API Calling
4. GsonConverter: To convert response into kotlin object
5. RxJava: To do the API call on io thread

Future Improvements:
1. Implementation of Search Image
2. Caching support with Room Library
3. Unit testing with Junit and MockK
4. RxJava could be replaced with Coroutine
5. Better UI
