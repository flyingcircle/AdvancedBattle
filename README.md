IDE setup:

The latest release of the Android SDK can be found here: http://developer.android.com/sdk/index.html .
It's about 400MB make sure in a spot that can download something that size.

You'll want to run the Eclipse that is in the eclipse folder of the SDK that you downloaded.


Getting the project repository:

After you've forked the project and downloaded it to your local machine, import the project into Eclipse.

To download the Google Play Services package, download it via the Android SDK Manager.
Feel free to download any extensions for it.

Next, you'll need to import the google-play-services-lib project into Eclipse.
It can be found at <android-sdk>/extras/google/google_play_services/libproject/google-play-services_lib/ .
Don't forget to then add that library as a dependency for the project!
You can do that via Project -> Properties -> Android.
You may first need to label the library project as a library by following the same steps and checking "Is Library".
You will also need the BaseGameUtils library. It can be found here: https://github.com/playgameservices/android-samples .

Fork the project and download it to your local machine.
Then import it and set it up as a library the same way you did for the Google Play Services library.

If more help is needed, see https://developers.google.com/games/services/android/quickstart .
