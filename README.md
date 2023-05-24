# Cordova AppVersion plugin (ONLY ANDOIRD)

Reads the version of your app from the target build settings.

Compatible with android API 33

## Installation

### With cordova-cli

If you are using [cordova-cli](https://github.com/apache/cordova-cli), install
with:

    cordova plugin add https://github.com/MrRotella/cordova-plugin-app-version.git

## Use from Javascript

If you are using jQuery, AngularJS, WinJS or any Promise/A library (Bluebird), promise style is supported. Use something like:

    cordova.getAppVersion.getVersionNumber().then(function (version) {
        $('.version').text(version);
    });

If not, pass a callback function:

    cordova.getAppVersion.getVersionNumber(function (version) {
        alert(version);
    });

In addition to the version number you can also retrieve other details about your application:

### getAppName

Returns the name of the app. E.g. "My Awesome App"

### getPackageName

Returns the package name of the app - the reversed domain name app identifier like com.example.myawesomeapp

### getVersionCode

Returns the build identifier of the app

### getVersionNumber

Returns the version number of the app
