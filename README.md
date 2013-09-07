**********************************************************
IDE Setup
**********************************************************

The latest release of the Android SDK can be found here: http://developer.android.com/sdk/index.html .
It's about 400MB make sure in a spot that can download something that size.

You'll want to run the Eclipse that is in the eclipse folder of the SDK that you downloaded.

**********************************************************
Cloning the repository
**********************************************************

The repository is located at: https://github.com/flyingcircle/Capstone.git

To get a copy of the repository on your local machine you can run the command:
git clone https://github.com/flyingcircle/Capstone.git

After you've forked the project and downloaded it to your local machine, import the project into Eclipse.

***********************************************************
Google Play Services
***********************************************************
To download the Google Play Services package, download it via the Android SDK Manager.
Feel free to download any extensions for it.

Next, you'll need to import the google-play-services-lib project into Eclipse.
It can be found at <android-sdk>/extras/google/google_play_services/libproject/google-play-services_lib/ .
Don't forget to then add that library as a dependency for the project!
You can do that via Project -> Properties -> Android.
You may first need to label the library project as a library by following the same steps and checking "Is Library".

************************************************************
Base Game Utils Library
************************************************************
You will also need the BaseGameUtils library. It can be found here: https://github.com/playgameservices/android-samples .

Fork the project and download it to your local machine.
Then import it and set it up as a library the same way you did for the Google Play Services library.

If more help is needed, see https://developers.google.com/games/services/android/quickstart .

*************************************************************
AndEngine Library
*************************************************************

This project also makes use of the AndEngine library which can be gotten from here: https://github.com/nicolasgramlich/AndEngine

This will need to be imported like the above libraries in Eclipse. Along with that in your SDK manager, you will need to install the SDK Platform, System Images(all except MIPS), and Google APIs for all Android APIs from the newest to API 8.

I also downloaded the TMXTiledMapExtension

For these libraries you will have to make sure that the Java Compiler version being used is 1.6 in Properties > Java Compiler

And that the Project build target has a valid Android API selected.

Make sure to add AndEngin to the Capstone build path and the AndEngineTMXTiledMapExtension build path.

And the AndEngineTMXTiledMapExtension also needs to be added to Capstone.