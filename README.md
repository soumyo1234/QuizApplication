# Quiz Application

A native Android multiple-choice quiz app written in Kotlin. Choose a topic, answer a ten-question quiz, review your score and medal, and revisit saved attempts from the scoreboard.

## Features

- Quiz categories: Software Development and Data Structures.
- Ten multiple-choice questions per quiz, selected in random order without repeats.
- Immediate feedback after each submitted answer.
- Result screen with a score out of 10 and a medal based on the score:
  - Gold: 9–10
  - Silver: 8
  - Bronze: 4–7
  - Try Again: 0–3
- Scoreboard of saved quiz attempts.
- Scores are stored locally on the device using Android `SharedPreferences`; there are no accounts or network services.

## Requirements

- Android Studio with Android SDK Platform 36.1 installed.
- JDK 17 or a JDK version supported by Android Gradle Plugin 9.1.
- An Android device or emulator running Android 7.0 (API 24) or later to install the app.

The project uses Gradle Wrapper 9.8, so a separate Gradle installation is not required. Gradle may download the wrapper distribution and project dependencies on the first build.

## Build and Run

1. Open the project root in Android Studio and allow Gradle sync to finish.
2. Select the `app` run configuration and an Android API 24+ device or emulator.
3. Run the app. The launcher activity opens the category selection screen.

To build a debug APK from Windows PowerShell in the project root:

```powershell
.\gradlew.bat assembleDebug
```

The APK is generated at `app/build/outputs/apk/debug/app-debug.apk`.

To install the debug build on a connected device or running emulator:

```powershell
.\gradlew.bat installDebug
```

## Tests

Run local JVM unit tests:

```powershell
.\gradlew.bat testDebugUnitTest
```

Run Android instrumented tests on a connected device or emulator:

```powershell
.\gradlew.bat connectedDebugAndroidTest
```

The project includes a sample local unit test and instrumented tests for reading and writing score data in `SharedPreferences`.

## Project Structure

```text
app/src/main/
├── AndroidManifest.xml
├── java/com/example/quizapplication/
│   ├── MainActivity.kt          # Category selection and app navigation
│   ├── QuizActivity.kt          # Question selection, answers, and scoring
│   ├── Question.kt              # Question data model
│   ├── QuestionData.kt          # Software and data-structure question sets
│   ├── ResultActivity.kt        # Result display and local score saving
│   └── ScoreboardActivity.kt    # Displays locally saved attempts
└── res/
    ├── layout/                  # XML layouts for the four screens
    └── values/                  # App name, colors, and themes
```

## Score Storage

Completed attempts are stored in the private `Scores` preferences file. Each record contains the category, score, and completion date/time. Records remain on the device between app launches and are removed if the app's data is cleared or the app is uninstalled. The scoreboard does not sync records between devices.

## Configuration

- Application ID: `com.example.quizapplication`
- Minimum SDK: Android 7.0 (API 24)
- Target SDK: Android 16 (API 36)
- Version: `1.0` (version code `1`)
- Main module: `app`