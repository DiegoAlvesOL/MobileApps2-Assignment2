# MobileApps2-Assignment2

Android app built for the Mobile Apps II module (Dorset College Dublin, Assignment 2). A single app with a main menu that demonstrates State, a Backend as a Service (Firebase Authentication) and Exceptions in Kotlin.

**Student:** Diego Alves de Oliveira (ID 78952)

## Features

1. **State**: two counters shown side by side, one using `remember` and one using `rememberSaveable`. After rotating the device, the first one resets to zero and the second one keeps its value. State is hoisted: `CounterDisplay` and `LoginScreen` are stateless and `MainActivity` owns the state.
2. **BaaS**: Firebase Authentication with email and password (register, sign in, sign out). The counters screen is only shown to a signed-in user.
3. **Exceptions 1**: `check()` throws `IllegalStateException` and `require()` throws `IllegalArgumentException`, each handled by a specific `catch`.
4. **Exceptions 2**: custom exception `WordTooLongException`, thrown when a word has more than 10 characters and handled by a specific `catch`.

The results of the exceptions demos are shown on screen and in Logcat with the tag `ExceptionsLab`.

## Tech stack

* Kotlin 2.2.10
* Jetpack Compose with Material 3
* Firebase Authentication (Firebase BoM 34.17.0)
* Gradle with a version catalog (`libs.versions.toml`)

## How to run

1. Clone the repository and open the `StateAuthApp` folder in Android Studio.
2. Wait for the Gradle sync to finish.
3. Select an emulator (tested on Medium Phone, API 36.1) and press Run.
4. In the app, use **Login with Firebase** and register a user (the password needs at least 6 characters), or use **Exceptions** to run the demos.

The Firebase configuration file `app/google-services.json` is included in the repository.

## Project structure

```
StateAuthApp/app/src/main/java/com/diegoalves/stateauthapp/
├── MainActivity.kt          entry point, owns the state and the navigation
├── exceptions/
│   ├── DemoResult.kt
│   ├── PreconditionDemo.kt  check() and require()
│   ├── WordChecker.kt       throws WordTooLongException
│   └── WordTooLongException.kt
└── ui/
    ├── AppScreen.kt         navigation enum (MENU, LOGIN, EXCEPTIONS)
    ├── CounterDisplay.kt
    ├── CounterScreen.kt
    ├── ExceptionsScreen.kt
    ├── LoginScreen.kt
    ├── MainMenuScreen.kt
    └── theme/               colors and theme
```

## Notes

The Firebase BoM was kept at 34.17.0 because `firebase-auth` from BoM 35.x requires Kotlin 2.4 metadata and this project uses Kotlin 2.2.10.