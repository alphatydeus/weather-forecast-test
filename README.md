# weather-forecast-test
App displaying the 5 day weather forecast for the city of Rome.
I build this Project using Android Studio 2.3.3. Just open the Project and build. No other requirements.
This app uses http://openweathermap.org/forecast5 API.

How to improve  the app:

	* Instead of using Log.e integrate with Firebase crash reporting and performance services.
	* Add Registration/Login using Firebase Auth.
	* Integrate Analytics to  understand better user behaviour and improve the app.
	* Add notifications to make the user return to the app and in case some ads are shown it will increase the earnings. ALso we could show alerts in case of extreme weather.
	* Make the app more Testable by integrating Testing libraries and using ModelViewPresenter pattern and RxAndroid.
	* Simplify the communication between components (DayFragment and the API). At the moment the Fragments will not even save the state.
	* Translate the app into multiple languages.
	* Integrate the app with Google assistant to show cards from our app when user is asking for weather
	* Integrate with Google Maps library in case the user wants to type a city instead of using his Gps position. This way we will autocomplete the city when typing. As an alternative we could use Firebase database to store all the city names.
	* Add Tablet specific layouts to use wiser the available white space.
	* Add daily min max average temperature.
	* Make heavy use of animations because the app UI is simple the animations will make the app stand out from hundreds of other weather apps.
	* Build Homescreen widgets.
	* Add possibility to switch between two cities.
	* Add licenses for Open Source  libraries and attribution to ForecastWetherMap.com

