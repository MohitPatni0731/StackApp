# StackApp

A fully functional Android application that implements a classic LIFO (Last In First Out) stack data structure with an interactive user interface.

## Features

- **Custom Stack Implementation**: Array-based stack with index/top variable (no built-in Stack classes)
- **Stack Constraints**:
  - Maximum size: 3 elements
  - Allowed values: integers 0-9 only
  - Top of Stack (ToS) is the rightmost element when displayed

## Supported Operations

### Push
- **Button**: Enter a value (0-9) in the input field and tap "PUSH"
- **Command**: Type `push X` (where X is 0-9) and tap "RUN"

### Pop
- **Button**: Tap the "POP" button
- **Command**: Type `pop` and tap "RUN"

### Quit
- **Button**: Tap the "QUIT" button
- **Command**: Type `quit` and tap "RUN"

## Example Usage

| Input | Output |
|-------|--------|
| push 3 | 3 is pushed. Stack [3] |
| push 5 | 5 is pushed. Stack [3 5] |
| push 2 | 2 is pushed. Stack [3 5 2] |
| push 7 | Stack is FULL. Stack [3 5 2] |
| pop | 2 is popped. Stack [3 5] |
| pop | 5 is popped. Stack [3] |
| push 7 | 7 is pushed. Stack [3 7] |
| pop | 7 is popped. Stack [3] |
| quit | App exits |

## Project Structure

```
StackApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/stackapp/
│   │   │   ├── MainActivity.kt      # Main UI and event handling
│   │   │   └── Stack.kt              # Custom stack implementation
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   └── activity_main.xml # UI layout
│   │   │   ├── values/
│   │   │   │   ├── colors.xml        # Color definitions
│   │   │   │   ├── strings.xml       # String resources
│   │   │   │   └── themes.xml        # App theme
│   │   │   └── color/
│   │   │       └── text_input_box_stroke.xml
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts              # App-level build config
├── build.gradle.kts                  # Project-level build config
├── settings.gradle.kts               # Project settings
├── gradle.properties                 # Gradle properties
└── gradle/wrapper/
    └── gradle-wrapper.properties     # Gradle wrapper config
```

## Requirements

- **Android Studio**: Arctic Fox (2020.3.1) or later
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Language**: Kotlin

## Setup Instructions

1. **Open Android Studio**
2. **Open the project**: File → Open → Select the `StackApp` folder
3. **Wait for Gradle sync** to complete
4. **Run the app**: Click the "Run" button or press Shift+F10
5. **Select a device**: Choose an emulator or connected Android device

## Input Validation

- **Push values**: Must be integers between 0-9 (inclusive)
- **Invalid commands**: Displays error message and preserves stack state
- **Stack overflow**: Displays "Stack is FULL" message
- **Stack underflow**: Displays "Stack is EMPTY" message

## UI Features

- **Dark theme** with modern Material Design
- **Console-style output** showing operation history
- **Color-coded stack display**:
  - Green: Normal state
  - Red: Stack is full
  - Amber: Stack is empty
- **Auto-scrolling** output area
- **Clear button** to reset output history

## License

This project was created for educational purposes.

