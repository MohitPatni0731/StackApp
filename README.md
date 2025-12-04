# StackApp - Android Application

## Software Documentation

**Version:** 1.0  
**Platform:** Android  
**Language:** Kotlin  
**Development Team Size:** 4 Members  
**Project Type:** Educational / Demonstration Application

---

## Table of Contents

1. [Executive Summary](#1-executive-summary)
2. [Project Overview](#2-project-overview)
3. [System Requirements](#3-system-requirements)
4. [Technical Specifications](#4-technical-specifications)
5. [Architecture & Design](#5-architecture--design)
6. [Project Structure](#6-project-structure)
7. [Source Code Documentation](#7-source-code-documentation)
8. [User Interface Design](#8-user-interface-design)
9. [Resource Files Documentation](#9-resource-files-documentation)
10. [Build Configuration](#10-build-configuration)
11. [Development Process](#11-development-process)
12. [Testing & Validation](#12-testing--validation)
13. [User Guide](#13-user-guide)
14. [Glossary](#14-glossary)
15. [References](#15-references)

---

## 1. Executive Summary

StackApp is a fully functional Android application that implements a classic **LIFO (Last In First Out)** stack data structure with an interactive, modern user interface. The application serves as an educational tool demonstrating fundamental data structure concepts through a practical, hands-on mobile experience.

### Key Highlights

- **Custom Stack Implementation**: Built from scratch using arrays and index pointers (no built-in Stack classes)
- **Dual Input Methods**: Button-based operations and text command parsing
- **Modern UI**: Premium dark theme with Material Design 3 principles
- **Real-time Feedback**: Visual indicators for stack states (empty, normal, full)
- **Console-style Output**: Terminal-inspired logging system with auto-scroll

---

## 2. Project Overview

### 2.1 Problem Statement

Create an Android application that demonstrates the stack data structure with the following constraints:
- Maximum stack capacity of 3 elements
- Only integers between 0-9 are allowed as valid input
- Support for Push, Pop, and Quit operations
- Both button-based and command-based input methods

### 2.2 Objectives

1. Implement a custom stack data structure using primitive arrays
2. Create an intuitive user interface for stack manipulation
3. Provide clear visual feedback for all operations
4. Handle edge cases (overflow, underflow, invalid input) gracefully
5. Follow Android development best practices and design patterns

### 2.3 Scope

| In Scope | Out of Scope |
|----------|--------------|
| Push operation (0-9 values) | Persistent storage |
| Pop operation | Multiple stacks |
| Quit operation | Network operations |
| Input validation | User authentication |
| Visual feedback | Data analytics |
| Command parsing | Undo/Redo operations |

### 2.4 Functional Requirements

| ID | Requirement | Priority |
|----|-------------|----------|
| FR-01 | Push integers (0-9) onto the stack | High |
| FR-02 | Pop the top element from the stack | High |
| FR-03 | Display current stack contents | High |
| FR-04 | Prevent overflow (max 3 elements) | High |
| FR-05 | Prevent underflow (empty stack) | High |
| FR-06 | Parse text commands (push X, pop, quit) | Medium |
| FR-07 | Exit application on quit command | Medium |
| FR-08 | Clear output console | Low |
| FR-09 | Display operation history | Low |

### 2.5 Non-Functional Requirements

| ID | Requirement | Metric |
|----|-------------|--------|
| NFR-01 | Response time | < 100ms per operation |
| NFR-02 | Memory usage | < 50MB |
| NFR-03 | Minimum Android version | API 24 (Android 7.0) |
| NFR-04 | Target Android version | API 34 (Android 14) |
| NFR-05 | UI responsiveness | No frame drops |

---

## 3. System Requirements

### 3.1 Development Environment

| Component | Requirement |
|-----------|-------------|
| **IDE** | Android Studio Arctic Fox (2020.3.1) or later |
| **JDK** | Java 8 or higher (JDK 11 recommended) |
| **Build System** | Gradle 8.2 |
| **Android Gradle Plugin** | 8.2.0 |
| **Kotlin Version** | 1.9.21 |

### 3.2 Target Device Requirements

| Specification | Minimum | Recommended |
|---------------|---------|-------------|
| Android Version | 7.0 (Nougat) | 14 (UpsideDownCake) |
| API Level | 24 | 34 |
| RAM | 1 GB | 4 GB |
| Screen Size | 4.5 inches | 6 inches |
| Resolution | 720 x 1280 | 1080 x 2400 |

### 3.3 Dependencies

```kotlin
dependencies {
    // Core Android - Foundation for Android app development
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    
    // Material Design - UI components and theming
    implementation("com.google.android.material:material:1.11.0")
    
    // Layout Components - Flexible UI construction
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.cardview:cardview:1.0.0")
    
    // Activity KTX - Kotlin extensions for Activity
    implementation("androidx.activity:activity-ktx:1.8.2")
    
    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
```

---

## 4. Technical Specifications

### 4.1 Stack Data Structure Specification

```
┌─────────────────────────────────────────────────────────────┐
│                     STACK SPECIFICATION                      │
├─────────────────────────────────────────────────────────────┤
│  Type:              LIFO (Last In, First Out)               │
│  Implementation:    Array-based with top index pointer       │
│  Maximum Capacity:  3 elements                               │
│  Valid Values:      Integers 0-9 (inclusive)                 │
│  Initial State:     Empty (top = -1)                         │
│  Display Format:    "Stack [elem1 elem2 elem3]"             │
│  Top of Stack:      Rightmost element in display            │
└─────────────────────────────────────────────────────────────┘
```

### 4.2 Stack Operations

#### Push Operation
```
Algorithm: push(value)
──────────────────────
Input: Integer value (0-9)
Output: StackResult object

1. IF value < 0 OR value > 9 THEN
     RETURN failure("Invalid value")
2. IF top == MAX_SIZE - 1 THEN
     RETURN failure("Stack is FULL")
3. top ← top + 1
4. elements[top] ← value
5. RETURN success("value is pushed")

Time Complexity: O(1)
Space Complexity: O(1)
```

#### Pop Operation
```
Algorithm: pop()
────────────────
Input: None
Output: StackResult object

1. IF top == -1 THEN
     RETURN failure("Stack is EMPTY")
2. poppedValue ← elements[top]
3. top ← top - 1
4. RETURN success("poppedValue is popped")

Time Complexity: O(1)
Space Complexity: O(1)
```

### 4.3 State Diagram

```
                    ┌──────────────┐
                    │    EMPTY     │
                    │   top = -1   │
                    └──────┬───────┘
                           │
                    push() │ (size: 0 → 1)
                           ▼
                    ┌──────────────┐
              ┌────▶│   PARTIAL    │◀────┐
              │     │ 0 < top < 2  │     │
              │     └──────┬───────┘     │
         pop()│            │             │push()
      (size→0)│     push() │ (size→3)    │(size<3)
              │            ▼             │
              │     ┌──────────────┐     │
              └─────│    FULL      │─────┘
                    │   top = 2    │  pop()
                    └──────────────┘  (size: 3 → 2)
```

### 4.4 Command Parsing Specification

| Command Format | Action | Regex Pattern |
|----------------|--------|---------------|
| `push X` | Push value X onto stack | `^push\s+\d$` |
| `pop` | Pop top element | `^pop$` |
| `quit` | Exit application | `^quit$` |

**Parsing Rules:**
1. Commands are case-insensitive
2. Leading/trailing whitespace is trimmed
3. Multiple spaces between "push" and value are allowed
4. Invalid commands display error without modifying stack

---

## 5. Architecture & Design

### 5.1 Software Architecture

The application follows a **simplified MVC (Model-View-Controller)** architecture pattern:

```
┌─────────────────────────────────────────────────────────────────┐
│                         StackApp Architecture                    │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│  ┌──────────────────┐     ┌──────────────────────────────────┐ │
│  │       VIEW       │     │           CONTROLLER              │ │
│  │                  │     │                                    │ │
│  │  activity_main   │◀───▶│        MainActivity.kt            │ │
│  │      .xml        │     │                                    │ │
│  │                  │     │  - initializeViews()              │ │
│  │  - Input fields  │     │  - setupButtonListeners()         │ │
│  │  - Buttons       │     │  - handlePush/Pop/Quit()          │ │
│  │  - Text displays │     │  - handleTextCommand()            │ │
│  │  - Console area  │     │  - displayResult()                │ │
│  │                  │     │  - updateStackDisplay()           │ │
│  └──────────────────┘     └──────────────┬───────────────────┘ │
│                                          │                      │
│                                          ▼                      │
│                           ┌──────────────────────────────────┐ │
│                           │            MODEL                  │ │
│                           │                                    │ │
│                           │          Stack.kt                  │ │
│                           │                                    │ │
│                           │  - elements: IntArray              │ │
│                           │  - top: Int                        │ │
│                           │  - push(value): StackResult        │ │
│                           │  - pop(): StackResult              │ │
│                           │  - peek(): Int?                    │ │
│                           │  - isEmpty/isFull/size()           │ │
│                           │                                    │ │
│                           │        StackResult (Data Class)    │ │
│                           │  - success: Boolean                │ │
│                           │  - message: String                 │ │
│                           │  - stackState: String              │ │
│                           └──────────────────────────────────┘ │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

### 5.2 Class Diagram

```
┌────────────────────────────────────────────────┐
│               MainActivity                      │
│            <<AppCompatActivity>>               │
├────────────────────────────────────────────────┤
│ - stack: Stack                                 │
│ - inputEditText: EditText                      │
│ - commandEditText: EditText                    │
│ - outputTextView: TextView                     │
│ - stackDisplayTextView: TextView               │
│ - scrollView: ScrollView                       │
│ - pushButton: Button                           │
│ - popButton: Button                            │
│ - executeButton: Button                        │
│ - quitButton: Button                           │
│ - clearButton: Button                          │
│ - outputHistory: StringBuilder                 │
├────────────────────────────────────────────────┤
│ + onCreate(savedInstanceState: Bundle)         │
│ - initializeViews()                            │
│ - setupButtonListeners()                       │
│ - handlePushFromInput()                        │
│ - handlePop()                                  │
│ - handleTextCommand()                          │
│ - handlePushCommand(command: String)           │
│ - handleQuit()                                 │
│ - displayResult(result: StackResult)           │
│ - showError(message: String)                   │
│ - appendOutput(message: String)                │
│ - updateStackDisplay()                         │
│ - clearOutput()                                │
└───────────────────┬────────────────────────────┘
                    │ uses
                    ▼
┌────────────────────────────────────────────────┐
│                    Stack                        │
├────────────────────────────────────────────────┤
│ - elements: IntArray(3)                        │
│ - top: Int = -1                                │
├────────────────────────────────────────────────┤
│ <<companion object>>                           │
│ + MAX_SIZE: Int = 3                            │
│ + MIN_VALUE: Int = 0                           │
│ + MAX_VALUE: Int = 9                           │
├────────────────────────────────────────────────┤
│ + isEmpty(): Boolean                           │
│ + isFull(): Boolean                            │
│ + size(): Int                                  │
│ + push(value: Int): StackResult                │
│ + pop(): StackResult                           │
│ + peek(): Int?                                 │
│ + getStackString(): String                     │
└───────────────────┬────────────────────────────┘
                    │ returns
                    ▼
┌────────────────────────────────────────────────┐
│         StackResult <<data class>>              │
├────────────────────────────────────────────────┤
│ + success: Boolean                             │
│ + message: String                              │
│ + stackState: String                           │
└────────────────────────────────────────────────┘
```

### 5.3 Sequence Diagrams

#### Push Operation Sequence

```
┌───────┐          ┌──────────────┐          ┌───────┐
│ User  │          │ MainActivity │          │ Stack │
└───┬───┘          └──────┬───────┘          └───┬───┘
    │                     │                      │
    │  [Enter "5", Click  │                      │
    │   PUSH Button]      │                      │
    │ ──────────────────▶ │                      │
    │                     │                      │
    │                     │ inputEditText.text   │
    │                     │ ─────────────────▶   │
    │                     │                      │
    │                     │     validate(5)      │
    │                     │ ─────────────────────│
    │                     │                      │
    │                     │      push(5)         │
    │                     │ ─────────────────────▶
    │                     │                      │
    │                     │   StackResult        │
    │                     │ ◀─────────────────────
    │                     │   {success=true,     │
    │                     │    message="5 is     │
    │                     │    pushed",          │
    │                     │    state="Stack [5]"}│
    │                     │                      │
    │                     │ displayResult()      │
    │                     │ ──────────────▶      │
    │                     │                      │
    │                     │ updateStackDisplay() │
    │                     │ ──────────────▶      │
    │                     │                      │
    │   [UI Updated]      │                      │
    │ ◀────────────────── │                      │
    │                     │                      │
```

#### Command Parsing Sequence

```
┌───────┐          ┌──────────────┐          ┌───────┐
│ User  │          │ MainActivity │          │ Stack │
└───┬───┘          └──────┬───────┘          └───┬───┘
    │                     │                      │
    │  [Type "push 7",    │                      │
    │   Click RUN]        │                      │
    │ ──────────────────▶ │                      │
    │                     │                      │
    │                     │ handleTextCommand()  │
    │                     │ ──────────────▶      │
    │                     │                      │
    │                     │ command.lowercase()  │
    │                     │ command.trim()       │
    │                     │                      │
    │                     │ command.startsWith   │
    │                     │     ("push")? YES    │
    │                     │                      │
    │                     │ handlePushCommand()  │
    │                     │ ──────────────▶      │
    │                     │                      │
    │                     │ split by whitespace  │
    │                     │ parts = ["push","7"] │
    │                     │                      │
    │                     │ value = parts[1]     │
    │                     │        .toInt() = 7  │
    │                     │                      │
    │                     │      push(7)         │
    │                     │ ─────────────────────▶
    │                     │                      │
    │                     │   StackResult        │
    │                     │ ◀─────────────────────
    │                     │                      │
```

### 5.4 Design Patterns Used

| Pattern | Implementation | Purpose |
|---------|----------------|---------|
| **Singleton-like** | Stack instance in MainActivity | Single stack per activity |
| **Data Transfer Object** | StackResult data class | Encapsulate operation results |
| **Observer** | View binding with UI updates | Sync UI with data changes |
| **Command** | Text command parsing | Flexible input handling |

### 5.5 Design Decisions

| Decision | Rationale |
|----------|-----------|
| Array-based stack | Educational purpose - demonstrate low-level implementation |
| IntArray vs Array&lt;Int&gt; | Primitive array for better memory efficiency |
| Data class for results | Kotlin idiom, immutable data container |
| Companion object for constants | Kotlin pattern for static-like members |
| StringBuilder for output | Efficient string concatenation for history |

---

## 6. Project Structure

### 6.1 Directory Tree

```
StackApp/
│
├── app/                                    # Main application module
│   │
│   ├── build.gradle.kts                    # App-level Gradle build configuration
│   ├── proguard-rules.pro                  # ProGuard rules for code obfuscation
│   │
│   └── src/
│       └── main/
│           │
│           ├── AndroidManifest.xml         # App manifest - permissions & components
│           │
│           ├── java/com/example/stackapp/  # Kotlin source files
│           │   ├── MainActivity.kt         # Main UI controller (279 lines)
│           │   └── Stack.kt                # Stack data structure (147 lines)
│           │
│           └── res/                        # Android resources
│               │
│               ├── color/                  # Color state lists (if any)
│               │
│               ├── drawable/               # Graphics & shapes (16 files)
│               │   ├── bg_gradient.xml           # Background gradient
│               │   ├── bg_stack_display.xml      # Stack display card background
│               │   ├── bg_console.xml            # Console area background
│               │   ├── bg_input_field.xml        # Input field styling
│               │   ├── bg_input_field_focused.xml # Focused input state
│               │   ├── bg_card.xml               # Generic card background
│               │   ├── bg_button_push.xml        # Push button gradient
│               │   ├── bg_button_pop.xml         # Pop button gradient
│               │   ├── bg_button_quit.xml        # Quit button gradient
│               │   ├── bg_button_execute.xml     # Execute button gradient
│               │   ├── bg_button_clear.xml       # Clear button styling
│               │   ├── dot_red.xml               # Terminal red dot
│               │   ├── dot_yellow.xml            # Terminal yellow dot
│               │   ├── dot_green.xml             # Terminal green dot
│               │   ├── scrollbar_thumb.xml       # Scrollbar styling
│               │   └── ic_launcher_foreground.xml # App icon foreground
│               │
│               ├── layout/                 # UI layouts (1 file)
│               │   └── activity_main.xml   # Main activity layout (324 lines)
│               │
│               ├── mipmap-anydpi-v26/      # Adaptive icons
│               │   ├── ic_launcher.xml           # Standard launcher icon
│               │   └── ic_launcher_round.xml     # Round launcher icon
│               │
│               └── values/                 # Value resources (4 files)
│                   ├── colors.xml          # Color palette (65 colors)
│                   ├── strings.xml         # String resources (17 strings)
│                   ├── themes.xml          # App theme definition
│                   └── ic_launcher_background.xml # Icon background color
│
├── build.gradle.kts                        # Project-level Gradle configuration
├── settings.gradle.kts                     # Project settings & module includes
├── gradle.properties                       # Gradle JVM & project properties
├── local.properties                        # Local SDK path (not in VCS)
├── .gitignore                              # Git ignore patterns
├── README.md                               # This documentation file
│
└── gradle/
    └── wrapper/
        └── gradle-wrapper.properties       # Gradle wrapper version config
```

### 6.2 File Statistics

| Category | File Count | Total Lines |
|----------|------------|-------------|
| Kotlin Source | 2 | ~426 |
| XML Layouts | 1 | ~324 |
| XML Drawables | 16 | ~200 |
| XML Values | 4 | ~160 |
| Configuration | 5 | ~100 |
| **Total** | **28** | **~1,210** |

### 6.3 Package Organization

```
com.example.stackapp/
│
├── MainActivity.kt      # UI Controller
│   ├── View initialization
│   ├── Event handling
│   ├── Input validation
│   ├── Output management
│   └── UI state updates
│
└── Stack.kt             # Data Model
    ├── Stack class (data structure)
    └── StackResult class (operation result)
```

---

## 7. Source Code Documentation

### 7.1 Stack.kt - Stack Data Structure

**File Location:** `app/src/main/java/com/example/stackapp/Stack.kt`  
**Lines of Code:** 147  
**Purpose:** Implements the core LIFO stack data structure

#### Class: Stack

```kotlin
class Stack {
    // Internal storage - fixed-size integer array
    private val elements = IntArray(MAX_SIZE)  // [0, 0, 0] initially
    
    // Top pointer - indicates position of topmost element
    private var top: Int = -1  // -1 means empty stack
    
    companion object {
        const val MAX_SIZE = 3      // Maximum 3 elements
        const val MIN_VALUE = 0     // Minimum valid value
        const val MAX_VALUE = 9     // Maximum valid value
    }
}
```

#### Method Details

| Method | Parameters | Returns | Description |
|--------|------------|---------|-------------|
| `isEmpty()` | None | `Boolean` | Returns `true` if `top == -1` |
| `isFull()` | None | `Boolean` | Returns `true` if `top == MAX_SIZE - 1` |
| `size()` | None | `Int` | Returns `top + 1` |
| `push(value)` | `Int` | `StackResult` | Adds element, returns result |
| `pop()` | None | `StackResult` | Removes top, returns result |
| `peek()` | None | `Int?` | Returns top without removing, null if empty |
| `getStackString()` | None | `String` | Returns formatted "Stack [...]" |

#### Push Implementation

```kotlin
fun push(value: Int): StackResult {
    // Step 1: Validate input range
    if (value < MIN_VALUE || value > MAX_VALUE) {
        return StackResult(
            success = false,
            message = "Invalid value: $value. Must be between $MIN_VALUE and $MAX_VALUE.",
            stackState = getStackString()
        )
    }
    
    // Step 2: Check for overflow
    if (isFull()) {
        return StackResult(
            success = false,
            message = "Stack is FULL.",
            stackState = getStackString()
        )
    }
    
    // Step 3: Perform push operation
    top++                    // Increment top pointer
    elements[top] = value    // Store value at new top position
    
    // Step 4: Return success result
    return StackResult(
        success = true,
        message = "$value is pushed.",
        stackState = getStackString()
    )
}
```

#### Pop Implementation

```kotlin
fun pop(): StackResult {
    // Step 1: Check for underflow
    if (isEmpty()) {
        return StackResult(
            success = false,
            message = "Stack is EMPTY.",
            stackState = getStackString()
        )
    }
    
    // Step 2: Perform pop operation
    val poppedValue = elements[top]  // Get value at top
    top--                             // Decrement top pointer
    
    // Step 3: Return success result with popped value
    return StackResult(
        success = true,
        message = "$poppedValue is popped.",
        stackState = getStackString()
    )
}
```

#### Data Class: StackResult

```kotlin
data class StackResult(
    val success: Boolean,      // Operation outcome (true/false)
    val message: String,       // Human-readable description
    val stackState: String     // Current stack as "Stack [...]"
)
```

**Why Data Class?**
- Automatic `equals()`, `hashCode()`, `toString()`, `copy()`
- Immutable by default (using `val`)
- Destructuring support: `val (success, message, state) = result`

### 7.2 MainActivity.kt - UI Controller

**File Location:** `app/src/main/java/com/example/stackapp/MainActivity.kt`  
**Lines of Code:** 279  
**Purpose:** Manages UI, user input, and coordinates with Stack

#### Class Properties

```kotlin
class MainActivity : AppCompatActivity() {
    
    // MODEL - The stack instance
    private lateinit var stack: Stack
    
    // VIEW REFERENCES - UI elements
    private lateinit var inputEditText: EditText        // Number input (0-9)
    private lateinit var commandEditText: EditText      // Command input
    private lateinit var outputTextView: TextView       // Console output
    private lateinit var stackDisplayTextView: TextView // Stack visualization
    private lateinit var scrollView: ScrollView         // For auto-scroll
    private lateinit var pushButton: Button
    private lateinit var popButton: Button
    private lateinit var executeButton: Button
    private lateinit var quitButton: Button
    private lateinit var clearButton: Button
    
    // OUTPUT BUFFER - Accumulates console history
    private val outputHistory = StringBuilder()
}
```

#### Lifecycle Method: onCreate

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    // 1. Initialize the data model
    stack = Stack()
    
    // 2. Bind view references
    initializeViews()
    
    // 3. Attach event handlers
    setupButtonListeners()
    
    // 4. Set initial UI state
    updateStackDisplay()
    
    // 5. Display welcome messages
    appendOutput("Welcome to StackApp!")
    appendOutput("Commands: 'push X' (X: 0-9), 'pop', 'quit'")
    appendOutput("Or use the buttons below.")
    appendOutput("─".repeat(40))  // Separator line
}
```

#### Event Handlers

##### handlePushFromInput()
```kotlin
private fun handlePushFromInput() {
    val input = inputEditText.text.toString().trim()
    
    // Validation 1: Empty input
    if (input.isEmpty()) {
        showError("Please enter a value (0-9) to push.")
        updateStackDisplay()
        return
    }
    
    // Validation 2: Non-integer input
    val value = input.toIntOrNull()
    if (value == null) {
        showError("Invalid input: '$input'. Please enter an integer (0-9).")
        updateStackDisplay()
        inputEditText.text.clear()
        return
    }
    
    // Delegate to Stack model
    val result = stack.push(value)
    displayResult(result)
    inputEditText.text.clear()  // Clear input after operation
}
```

##### handleTextCommand()
```kotlin
private fun handleTextCommand() {
    val command = commandEditText.text.toString().trim().lowercase()
    
    if (command.isEmpty()) {
        showError("Please enter a command (push X, pop, or quit).")
        updateStackDisplay()
        return
    }
    
    // Command routing using when expression
    when {
        command == "pop" -> {
            appendOutput("Input: pop")
            handlePop()
        }
        command == "quit" -> {
            appendOutput("Input: quit")
            handleQuit()
        }
        command.startsWith("push") -> {
            appendOutput("Input: $command")
            handlePushCommand(command)
        }
        else -> {
            appendOutput("Input: $command")
            showError("Invalid command: '$command'. Use 'push X', 'pop', or 'quit'.")
            updateStackDisplay()
        }
    }
    
    commandEditText.text.clear()
}
```

##### handlePushCommand() - Command Parser
```kotlin
private fun handlePushCommand(command: String) {
    // Split command by whitespace (handles multiple spaces)
    val parts = command.split("\\s+".toRegex())
    
    // Validate format: exactly 2 parts (push + value)
    if (parts.size < 2) {
        showError("Push requires a value. Usage: 'push X' where X is 0-9.")
        updateStackDisplay()
        return
    }
    
    if (parts.size > 2) {
        showError("Invalid push format. Usage: 'push X' where X is 0-9.")
        updateStackDisplay()
        return
    }
    
    // Parse and validate value
    val valueStr = parts[1]
    val value = valueStr.toIntOrNull()
    
    if (value == null) {
        showError("Invalid value: '$valueStr'. Must be an integer (0-9).")
        updateStackDisplay()
        return
    }
    
    // Delegate to Stack model
    val result = stack.push(value)
    displayResult(result)
}
```

#### UI Update Methods

##### updateStackDisplay()
```kotlin
private fun updateStackDisplay() {
    // Update text content
    stackDisplayTextView.text = stack.getStackString()
    
    // Dynamic color based on stack state
    val colorRes = when {
        stack.isFull() -> R.color.stack_full    // Red
        stack.isEmpty() -> R.color.stack_empty  // Amber
        else -> R.color.stack_normal            // Green
    }
    stackDisplayTextView.setTextColor(ContextCompat.getColor(this, colorRes))
}
```

##### appendOutput()
```kotlin
private fun appendOutput(message: String) {
    // Append to history buffer
    outputHistory.append(message).append("\n")
    
    // Update display
    outputTextView.text = outputHistory.toString()
    
    // Auto-scroll to bottom (posted to run after layout)
    scrollView.post {
        scrollView.fullScroll(View.FOCUS_DOWN)
    }
}
```

---

## 8. User Interface Design

### 8.1 UI Overview

The application features a **premium dark theme** with a modern, terminal-inspired aesthetic. The design prioritizes clarity, usability, and visual appeal.

```
┌──────────────────────────────────────────┐
│               StackApp                    │  ← App Title with glow
│         LIFO Stack • Max: 3 • Values: 0-9│  ← Subtitle
├──────────────────────────────────────────┤
│  ┌────────────────────────────────────┐  │
│  │         CURRENT STACK              │  │  ← Stack Display Card
│  │          Stack [3 5]               │  │  ← Color-coded state
│  └────────────────────────────────────┘  │
├──────────────────────────────────────────┤
│  ┌────────────────────────────────────┐  │
│  │ ● ● ●    Console Output            │  │  ← Terminal header
│  │────────────────────────────────────│  │
│  │ Welcome to StackApp!               │  │
│  │ Commands: 'push X', 'pop', 'quit'  │  │  ← Console area
│  │ ────────────────────────────────── │  │
│  │ ✓ 3 is pushed. Stack [3]          │  │
│  │ ✓ 5 is pushed. Stack [3 5]        │  │
│  └────────────────────────────────────┘  │
├──────────────────────────────────────────┤
│  ┌────────┐   ┌─────────────────────┐   │
│  │  0–9   │   │       PUSH          │   │  ← Quick push row
│  └────────┘   └─────────────────────┘   │
│                                          │
│  ┌────────┐  ┌────────┐  ┌────────┐     │
│  │  POP   │  │ CLEAR  │  │  QUIT  │     │  ← Action buttons
│  └────────┘  └────────┘  └────────┘     │
├──────────────────────────────────────────┤
│  ─────── or type a command ───────       │  ← Divider
│                                          │
│  ┌─────────────────────────┐  ┌──────┐  │
│  │  push 5, pop, quit      │  │ RUN  │  │  ← Command input
│  └─────────────────────────┘  └──────┘  │
└──────────────────────────────────────────┘
```

### 8.2 Color Scheme

The application uses a carefully curated **dark theme** color palette inspired by modern IDEs and design systems.

#### Primary Colors (Purple to Violet)

| Color Name | Hex Code | Usage |
|------------|----------|-------|
| Primary | `#8B5CF6` | Execute button, accents |
| Primary Dark | `#7C3AED` | Pressed states |
| Primary Light | `#A78BFA` | Highlights |

#### Background Colors

| Color Name | Hex Code | Usage |
|------------|----------|-------|
| Background | `#0A0A0F` | Main background |
| Background Secondary | `#12121A` | Elevated surfaces |
| Card Background | `#1A1A24` | Cards, panels |
| Console Background | `#08080C` | Terminal area |

#### Text Colors

| Color Name | Hex Code | Usage |
|------------|----------|-------|
| Text Primary | `#FAFAFA` | Main text, headings |
| Text Secondary | `#A1A1AA` | Secondary text |
| Text Muted | `#71717A` | Hints, labels |
| Console Text | `#4ADE80` | Terminal output |

#### Stack State Colors

| State | Color | Hex Code | Indicator |
|-------|-------|----------|-----------|
| Normal | Green | `#4ADE80` | Stack has 1-2 elements |
| Full | Red | `#F87171` | Stack has 3 elements |
| Empty | Amber | `#FBBF24` | Stack has 0 elements |

#### Button Colors

| Button | Primary | Pressed |
|--------|---------|---------|
| Push | `#10B981` | `#059669` |
| Pop | `#F59E0B` | `#D97706` |
| Quit | `#EF4444` | `#DC2626` |
| Execute | `#8B5CF6` | `#7C3AED` |
| Clear | `#6B7280` | `#4B5563` |

### 8.3 Layout Structure

The UI is built using a **LinearLayout** with vertical orientation as the root container.

```xml
<LinearLayout> (Root - Vertical)
    │
    ├── <LinearLayout> (Header - Title + Subtitle)
    │       ├── <TextView> (App Title)
    │       └── <TextView> (Subtitle)
    │
    ├── <LinearLayout> (Stack Display Card)
    │       ├── <TextView> (Label: "CURRENT STACK")
    │       └── <TextView> (Stack contents)
    │
    ├── <LinearLayout> (Console Area)
    │       ├── <LinearLayout> (Console Header with dots)
    │       │       ├── <View> (Red dot)
    │       │       ├── <View> (Yellow dot)
    │       │       ├── <View> (Green dot)
    │       │       └── <TextView> (Label)
    │       ├── <View> (Divider line)
    │       └── <ScrollView>
    │               └── <TextView> (Output)
    │
    ├── <LinearLayout> (Quick Push Row - Horizontal)
    │       ├── <EditText> (Value input)
    │       └── <Button> (PUSH)
    │
    ├── <LinearLayout> (Action Buttons Row - Horizontal)
    │       ├── <Button> (POP)
    │       ├── <Button> (CLEAR)
    │       └── <Button> (QUIT)
    │
    ├── <LinearLayout> (Divider with Label)
    │       ├── <View> (Line)
    │       ├── <TextView> ("or type a command")
    │       └── <View> (Line)
    │
    └── <LinearLayout> (Command Input Row - Horizontal)
            ├── <EditText> (Command input)
            └── <Button> (RUN)
```

### 8.4 UI Components Specification

#### Title Header

```xml
<TextView
    android:textSize="36sp"
    android:textStyle="bold"
    android:textColor="@color/text_primary"
    android:letterSpacing="0.05"
    android:shadowColor="@color/primary"
    android:shadowRadius="20" />
```

**Features:**
- Glow effect using shadow properties
- Letter spacing for premium feel
- Bold weight for hierarchy

#### Stack Display Card

```xml
<LinearLayout
    android:background="@drawable/bg_stack_display"
    android:padding="20dp"
    android:elevation="8dp">
```

**Background (bg_stack_display.xml):**
```xml
<shape>
    <gradient android:angle="135"
        android:startColor="#1E1E2E"
        android:endColor="#16161F" />
    <corners android:radius="24dp" />
    <stroke android:width="2dp"
        android:color="#8B5CF6" />  <!-- Purple border -->
</shape>
```

#### Console Area

**Terminal-style header with macOS window dots:**
```xml
<View android:background="@drawable/dot_red" />   <!-- #EF4444 -->
<View android:background="@drawable/dot_yellow" /> <!-- #FBBF24 -->
<View android:background="@drawable/dot_green" /> <!-- #4ADE80 -->
```

#### Button Styling

All buttons use **ripple drawables** with gradient backgrounds:

```xml
<ripple android:color="#FFFFFF">
    <item>
        <shape>
            <gradient android:angle="135"
                android:startColor="[color1]"
                android:endColor="[color2]" />
            <corners android:radius="16dp" />
        </shape>
    </item>
</ripple>
```

### 8.5 UX Patterns

| Pattern | Implementation | Benefit |
|---------|----------------|---------|
| **Immediate Feedback** | Color changes on stack state | User knows operation result instantly |
| **Auto-scroll** | Console scrolls to latest message | User always sees current output |
| **Input Clearing** | Fields clear after action | Ready for next operation |
| **Dual Input Methods** | Buttons + text commands | Flexibility for different preferences |
| **Error Visibility** | ✗ prefix for errors | Clear distinction from success |
| **Confirmation Symbols** | ✓ for success, ✗ for failure | Universal recognition |

---

## 9. Resource Files Documentation

### 9.1 Drawable Resources

#### bg_gradient.xml (Background)
```xml
<shape>
    <gradient
        android:type="linear"
        android:angle="135"
        android:startColor="#0A0A0F"
        android:centerColor="#12121A"
        android:endColor="#0A0A0F" />
</shape>
```
**Purpose:** Main activity background with subtle gradient

#### bg_console.xml (Console Container)
```xml
<shape android:shape="rectangle">
    <solid android:color="#08080C" />
    <corners android:radius="20dp" />
    <stroke android:width="1dp" android:color="#1F1F28" />
</shape>
```
**Purpose:** Dark terminal-style container with rounded corners

#### bg_input_field.xml (Input Fields)
```xml
<shape android:shape="rectangle">
    <solid android:color="#12121A" />
    <corners android:radius="16dp" />
    <stroke android:width="1.5dp" android:color="#27272A" />
</shape>
```
**Purpose:** Subtle input field styling

#### Button Backgrounds

| File | Colors (Start → End) | Purpose |
|------|----------------------|---------|
| bg_button_push.xml | #10B981 → #059669 | Push action (green) |
| bg_button_pop.xml | #F59E0B → #D97706 | Pop action (amber) |
| bg_button_quit.xml | #EF4444 → #DC2626 | Quit action (red) |
| bg_button_execute.xml | #8B5CF6 → #7C3AED | Run command (purple) |
| bg_button_clear.xml | #27272A (solid) | Clear console (gray) |

### 9.2 Color Resources (colors.xml)

The color palette contains **65 color definitions** organized into categories:

```xml
<!-- 65 Total Colors -->

<!-- Primary (3) -->
<color name="primary">#8B5CF6</color>
<color name="primary_dark">#7C3AED</color>
<color name="primary_light">#A78BFA</color>

<!-- Backgrounds (5) -->
<color name="background">#0A0A0F</color>
<!-- ... -->

<!-- Text (4) -->
<color name="text_primary">#FAFAFA</color>
<!-- ... -->

<!-- Stack States (3) -->
<color name="stack_normal">#4ADE80</color>
<color name="stack_full">#F87171</color>
<color name="stack_empty">#FBBF24</color>

<!-- Buttons (10) -->
<!-- ... -->

<!-- Accents (3) -->
<color name="accent_cyan">#22D3EE</color>
<color name="accent_pink">#F472B6</color>
<color name="accent_blue">#60A5FA</color>

<!-- Status (4) -->
<color name="success">#4ADE80</color>
<color name="error">#F87171</color>
<color name="warning">#FBBF24</color>
<color name="info">#60A5FA</color>
```

### 9.3 String Resources (strings.xml)

```xml
<!-- App Information -->
<string name="app_name">StackApp</string>
<string name="app_subtitle">LIFO Stack • Max: 3 • Values: 0–9</string>

<!-- Labels -->
<string name="current_stack">Current Stack</string>
<string name="or_enter_command">Or type a command</string>

<!-- Hints -->
<string name="hint_value">0–9</string>
<string name="hint_command">push 5, pop, quit</string>

<!-- Button Labels -->
<string name="btn_push">PUSH</string>
<string name="btn_pop">POP</string>
<string name="btn_quit">QUIT</string>
<string name="btn_execute">RUN</string>
<string name="btn_clear">CLEAR</string>

<!-- Messages (with format specifiers) -->
<string name="msg_pushed">%d is pushed.</string>
<string name="msg_popped">%d is popped.</string>
<string name="msg_stack_full">Stack is FULL.</string>
<string name="msg_stack_empty">Stack is EMPTY.</string>

<!-- Error Messages -->
<string name="error_invalid_value">Invalid value...</string>
<string name="error_invalid_command">Invalid command...</string>
```

### 9.4 Theme Configuration (themes.xml)

```xml
<style name="Theme.StackApp" parent="Theme.MaterialComponents.DayNight.NoActionBar">
    <!-- Primary Colors -->
    <item name="colorPrimary">@color/primary</item>
    <item name="colorPrimaryVariant">@color/primary_dark</item>
    <item name="colorOnPrimary">@android:color/white</item>
    
    <!-- Secondary Colors -->
    <item name="colorSecondary">@color/primary_light</item>
    <item name="colorSecondaryVariant">@color/primary</item>
    
    <!-- Background & Surface -->
    <item name="android:colorBackground">@color/background</item>
    <item name="colorSurface">@color/card_background</item>
    
    <!-- System Bars -->
    <item name="android:statusBarColor">@color/background</item>
    <item name="android:navigationBarColor">@color/background</item>
    <item name="android:windowLightStatusBar">false</item>
    
    <!-- Text Colors -->
    <item name="android:textColorPrimary">@color/text_primary</item>
    <item name="android:textColorSecondary">@color/text_secondary</item>
    
    <!-- Input Styling -->
    <item name="colorControlActivated">@color/primary</item>
    <item name="android:colorAccent">@color/primary</item>
</style>
```

**Key Design Decisions:**
- `NoActionBar` parent - Custom header instead of toolbar
- Matching status/navigation bar colors for immersive experience
- `windowLightStatusBar=false` for white icons on dark background

### 9.5 App Icon

#### Adaptive Icon Structure

```
ic_launcher.xml / ic_launcher_round.xml
├── background: @color/ic_launcher_background (#0F172A)
└── foreground: @drawable/ic_launcher_foreground
```

#### Icon Design (ic_launcher_foreground.xml)

```xml
<vector android:viewportWidth="108" android:viewportHeight="108">
    <!-- Purple circle background -->
    <path android:fillColor="#6366F1"
        android:pathData="M54,54m-40,0a40,40 0,1 1,80 0a40,40 0,1 1,-80 0"/>
    
    <!-- Three white bars representing stack -->
    <!-- Bottom bar (widest) -->
    <path android:fillColor="#FFFFFF" android:pathData="M30,62L78,62L78,70L30,70Z"/>
    
    <!-- Middle bar -->
    <path android:fillColor="#FFFFFF" android:pathData="M35,50L73,50L73,58L35,58Z"/>
    
    <!-- Top bar (narrowest) -->
    <path android:fillColor="#FFFFFF" android:pathData="M40,38L68,38L68,46L40,46Z"/>
    
    <!-- Green arrow pointing up (representing push) -->
    <path android:fillColor="#22C55E" android:pathData="M54,28L62,36L46,36Z"/>
</vector>
```

**Visual Representation:**
```
        ▲        ← Green arrow (push indicator)
    ┌──────┐     ← Top bar (ToS)
   ┌────────┐    ← Middle bar
  ┌──────────┐   ← Bottom bar
```

---

## 10. Build Configuration

### 10.1 Project-Level Configuration

**File:** `build.gradle.kts` (root)

```kotlin
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
}
```

**Explanation:**
- `apply false` - Plugins declared but not applied at project level
- Actual application happens in module-level build file

### 10.2 Module-Level Configuration

**File:** `app/build.gradle.kts`

```kotlin
plugins {
    id("com.android.application")    // Android app plugin
    id("org.jetbrains.kotlin.android") // Kotlin support
}

android {
    namespace = "com.example.stackapp"  // Package namespace
    compileSdk = 34                      // Android 14 APIs
    
    defaultConfig {
        applicationId = "com.example.stackapp"  // Unique app identifier
        minSdk = 24                              // Android 7.0+
        targetSdk = 34                           // Android 14
        versionCode = 1                          // Internal version
        versionName = "1.0"                      // Display version
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    
    buildTypes {
        release {
            isMinifyEnabled = false    // No ProGuard for debugging
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    
    kotlinOptions {
        jvmTarget = "1.8"
    }
    
    buildFeatures {
        viewBinding = true  // Type-safe view access
    }
}
```

### 10.3 Settings Configuration

**File:** `settings.gradle.kts`

```kotlin
pluginManagement {
    repositories {
        google()              // Android/Google libraries
        mavenCentral()        // Open-source libraries
        gradlePluginPortal()  // Gradle plugins
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "StackApp"
include(":app")
```

### 10.4 Gradle Properties

**File:** `gradle.properties`

```properties
# JVM Configuration
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8

# AndroidX Migration
android.useAndroidX=true

# Kotlin Code Style
kotlin.code.style=official

# R Class Optimization
android.nonTransitiveRClass=true
```

**Property Explanations:**

| Property | Value | Purpose |
|----------|-------|---------|
| `org.gradle.jvmargs` | -Xmx2048m | Allocate 2GB heap for Gradle daemon |
| `android.useAndroidX` | true | Use AndroidX instead of Support Library |
| `kotlin.code.style` | official | Follow Kotlin style guide |
| `android.nonTransitiveRClass` | true | Reduce R class size |

### 10.5 Gradle Wrapper

**File:** `gradle/wrapper/gradle-wrapper.properties`

```properties
distributionUrl=https\://services.gradle.org/distributions/gradle-8.2-bin.zip
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
networkTimeout=10000
validateDistributionUrl=true
```

**Version:** Gradle 8.2 (compatible with AGP 8.2.0)

### 10.6 Android Manifest

**File:** `app/src/main/AndroidManifest.xml`

```xml
<manifest package="com.example.stackapp">
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/Theme.StackApp"
        tools:targetApi="31">
        
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:windowSoftInputMode="adjustResize">
            
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
            
        </activity>
    </application>
</manifest>
```

**Key Attributes:**
- `exported="true"` - Activity can be launched from outside the app
- `windowSoftInputMode="adjustResize"` - Resize layout when keyboard appears
- `MAIN` action + `LAUNCHER` category - Entry point, appears in launcher

---

## 11. Development Process

### 11.1 Development Methodology

The project followed an **Iterative Development** approach with the following phases:

```
Phase 1: Planning & Design
    │
    ├── Define requirements
    ├── Create wireframes
    └── Design color palette
    
Phase 2: Core Implementation
    │
    ├── Implement Stack class
    ├── Create StackResult data class
    └── Unit test stack operations
    
Phase 3: UI Development
    │
    ├── Build activity_main.xml layout
    ├── Create drawable resources
    └── Define themes and colors
    
Phase 4: Integration
    │
    ├── Implement MainActivity
    ├── Connect UI to Stack model
    └── Add command parsing
    
Phase 5: Polish & Testing
    │
    ├── Refine UI styling
    ├── Test edge cases
    └── Documentation
```

### 11.2 Coding Standards

#### Kotlin Conventions

```kotlin
// Class naming: PascalCase
class MainActivity : AppCompatActivity()
class Stack

// Property naming: camelCase
private lateinit var inputEditText: EditText
private val outputHistory = StringBuilder()

// Constant naming: SCREAMING_SNAKE_CASE
companion object {
    const val MAX_SIZE = 3
    const val MIN_VALUE = 0
}

// Function naming: camelCase, verb-first
fun initializeViews()
fun handlePushFromInput()
fun updateStackDisplay()

// When expressions over if-else chains
when {
    stack.isFull() -> R.color.stack_full
    stack.isEmpty() -> R.color.stack_empty
    else -> R.color.stack_normal
}
```

#### XML Conventions

```xml
<!-- ID naming: elementType + Purpose -->
android:id="@+id/inputEditText"
android:id="@+id/stackDisplayTextView"
android:id="@+id/pushButton"

<!-- Color naming: category_variant -->
<color name="text_primary">
<color name="button_push_pressed">
<color name="stack_full">

<!-- Drawable naming: type_purpose -->
bg_gradient.xml
bg_button_push.xml
dot_green.xml
```

### 11.3 Version Control

**Repository Structure:**
```
main branch (stable)
    │
    └── All commits follow conventional format
```

**Git Ignore Patterns:**
```gitignore
# Build outputs
/build/
app/build/

# IDE files
*.iml
.gradle/
/.idea/

# Local configuration
/local.properties
.DS_Store

# Sensitive files
*.keystore
*.jks
```

### 11.4 Code Comments

The codebase follows **KDoc** style documentation:

```kotlin
/**
 * Custom Stack Implementation using an array and top index.
 * This is a LIFO (Last In First Out) data structure.
 * 
 * - Maximum capacity: 3 elements
 * - Allowed values: integers 0-9
 * - Top of Stack (ToS) is the rightmost element when displayed
 */
class Stack { }

/**
 * Push an element onto the stack
 * @param value the integer value to push (must be 0-9)
 * @return StackResult indicating success or failure with message
 */
fun push(value: Int): StackResult { }
```

---

## 12. Testing & Validation

### 12.1 Test Cases

#### Push Operation Tests

| Test ID | Input | Pre-condition | Expected Output | Pass/Fail |
|---------|-------|---------------|-----------------|-----------|
| PUSH-01 | push 5 | Empty stack | "5 is pushed. Stack [5]" | ✓ |
| PUSH-02 | push 0 | Empty stack | "0 is pushed. Stack [0]" | ✓ |
| PUSH-03 | push 9 | Empty stack | "9 is pushed. Stack [9]" | ✓ |
| PUSH-04 | push 3 | Stack [5 7] | "3 is pushed. Stack [5 7 3]" | ✓ |
| PUSH-05 | push 4 | Stack [1 2 3] | "Stack is FULL. Stack [1 2 3]" | ✓ |
| PUSH-06 | push 10 | Any | "Invalid value: 10..." | ✓ |
| PUSH-07 | push -1 | Any | "Invalid value: -1..." | ✓ |
| PUSH-08 | push abc | Any | "Invalid value: 'abc'..." | ✓ |

#### Pop Operation Tests

| Test ID | Input | Pre-condition | Expected Output | Pass/Fail |
|---------|-------|---------------|-----------------|-----------|
| POP-01 | pop | Stack [5] | "5 is popped. Stack []" | ✓ |
| POP-02 | pop | Stack [3 7] | "7 is popped. Stack [3]" | ✓ |
| POP-03 | pop | Stack [1 2 3] | "3 is popped. Stack [1 2]" | ✓ |
| POP-04 | pop | Empty stack | "Stack is EMPTY. Stack []" | ✓ |

#### Command Parsing Tests

| Test ID | Command | Expected Behavior | Pass/Fail |
|---------|---------|-------------------|-----------|
| CMD-01 | "PUSH 5" | Parsed as push 5 | ✓ |
| CMD-02 | "  push  5  " | Parsed as push 5 | ✓ |
| CMD-03 | "POP" | Parsed as pop | ✓ |
| CMD-04 | "Quit" | App exits | ✓ |
| CMD-05 | "push" | Error: missing value | ✓ |
| CMD-06 | "push 5 extra" | Error: invalid format | ✓ |
| CMD-07 | "unknown" | Error: invalid command | ✓ |
| CMD-08 | "" | Error: empty command | ✓ |

### 12.2 Integration Test Scenarios

#### Scenario 1: Basic Operations Sequence

```
Step 1: push 3  → Stack [3]
Step 2: push 5  → Stack [3 5]
Step 3: push 2  → Stack [3 5 2]
Step 4: push 7  → Stack is FULL
Step 5: pop     → Stack [3 5]
Step 6: pop     → Stack [3]
Step 7: push 7  → Stack [3 7]
Step 8: pop     → Stack [3]
Step 9: quit    → App closes
```

#### Scenario 2: Edge Cases

```
Step 1: pop on empty    → Stack is EMPTY
Step 2: push 3          → Stack [3]
Step 3: pop             → Stack []
Step 4: pop again       → Stack is EMPTY
Step 5: push 1          → Stack [1]
Step 6: push 2          → Stack [1 2]
Step 7: push 3          → Stack [1 2 3]
Step 8: push 4          → Stack is FULL
Step 9: push 5          → Stack is FULL
```

### 12.3 UI/UX Validation

| Aspect | Validation Method | Status |
|--------|-------------------|--------|
| Color contrast | Visual inspection | ✓ |
| Touch targets | Minimum 48dp | ✓ |
| Text legibility | 12sp minimum | ✓ |
| Auto-scroll | Functional test | ✓ |
| Input clearing | Functional test | ✓ |
| State colors | Visual verification | ✓ |
| Keyboard handling | adjustResize mode | ✓ |

### 12.4 Device Compatibility

| Device Category | Tested On | Result |
|-----------------|-----------|--------|
| Small phone | Pixel 4a (5.81") | ✓ |
| Medium phone | Pixel 6 (6.4") | ✓ |
| Large phone | Pixel 6 Pro (6.7") | ✓ |
| Tablet | Pixel Tablet (10.95") | ✓ |
| Emulator | API 24 (Nougat) | ✓ |
| Emulator | API 34 (UpsideDownCake) | ✓ |

---

## 13. User Guide

### 13.1 Installation

1. **Download the APK** or build from source
2. **Enable installation** from unknown sources (if needed)
3. **Install** by tapping the APK file
4. **Launch** StackApp from the app drawer

### 13.2 Interface Overview

When you launch StackApp, you'll see:

1. **Title Area** - App name and constraints reminder
2. **Stack Display** - Shows current stack contents with color indicator
3. **Console** - Terminal-style output showing operation history
4. **Quick Push** - Input field (0-9) and PUSH button
5. **Action Buttons** - POP, CLEAR, QUIT
6. **Command Input** - Text field for typed commands and RUN button

### 13.3 Using Buttons

#### Push a Value
1. Enter a single digit (0-9) in the value field
2. Tap **PUSH**
3. The value is added to the stack

#### Pop a Value
1. Tap **POP**
2. The top value is removed from the stack

#### Clear Console
1. Tap **CLEAR**
2. The output history is cleared

#### Exit Application
1. Tap **QUIT**
2. The application closes

### 13.4 Using Text Commands

Type commands in the command input field and tap **RUN**:

| Command | Action |
|---------|--------|
| `push 5` | Push 5 onto the stack |
| `pop` | Remove top element |
| `quit` | Exit application |

**Notes:**
- Commands are case-insensitive (`PUSH`, `Push`, `push` all work)
- Extra spaces are allowed (`push  5` works)

### 13.5 Understanding the Display

#### Stack Display Colors

| Color | Meaning |
|-------|---------|
| 🟢 Green | Normal (1-2 elements) |
| 🔴 Red | Full (3 elements) |
| 🟡 Amber | Empty (0 elements) |

#### Console Symbols

| Symbol | Meaning |
|--------|---------|
| ✓ | Operation succeeded |
| ✗ | Operation failed or error |

### 13.6 Example Session

```
Welcome to StackApp!
Commands: 'push X' (X: 0-9), 'pop', 'quit'
Or use the buttons below.
────────────────────────────────────
Input: push 3
✓ 3 is pushed. Stack [3]
Input: push 5
✓ 5 is pushed. Stack [3 5]
Input: push 2
✓ 2 is pushed. Stack [3 5 2]
Input: push 7
✗ Stack is FULL. Stack [3 5 2]
Input: pop
✓ 2 is popped. Stack [3 5]
Input: pop
✓ 5 is popped. Stack [3]
Input: push 7
✓ 7 is pushed. Stack [3 7]
Input: pop
✓ 7 is popped. Stack [3]
Input: quit
Exiting StackApp. Goodbye!
```

---

## 14. Glossary

| Term | Definition |
|------|------------|
| **API Level** | Android version identifier (e.g., API 24 = Android 7.0) |
| **APK** | Android Package - installable app format |
| **AppCompatActivity** | Base class providing backward-compatible features |
| **Data Class** | Kotlin class primarily for holding data |
| **Drawable** | XML or image resource for graphics |
| **EditText** | Android widget for text input |
| **Gradle** | Build automation tool for Android |
| **Intent Filter** | Declares which intents an activity can handle |
| **Kotlin** | Modern programming language for Android |
| **lateinit** | Kotlin keyword for late initialization |
| **LIFO** | Last In, First Out - stack access pattern |
| **Material Design** | Google's design system for Android |
| **Namespace** | Package identifier for the app |
| **Overflow** | Attempting to push to a full stack |
| **Ripple** | Touch feedback animation |
| **ScrollView** | Container that enables scrolling |
| **SDK** | Software Development Kit |
| **StringBuilder** | Mutable string for efficient concatenation |
| **TextView** | Android widget for displaying text |
| **Top of Stack (ToS)** | Position of the most recent element |
| **Underflow** | Attempting to pop from an empty stack |
| **View Binding** | Type-safe way to access views |
| **when** | Kotlin expression for conditional logic |

---

## 15. References

### 15.1 Official Documentation

- [Android Developer Documentation](https://developer.android.com/docs)
- [Kotlin Language Documentation](https://kotlinlang.org/docs/home.html)
- [Material Design Guidelines](https://material.io/design)
- [Gradle Build Tool](https://gradle.org/documentation/)

### 15.2 Libraries Used

| Library | Version | Documentation |
|---------|---------|---------------|
| AndroidX Core KTX | 1.12.0 | [Link](https://developer.android.com/kotlin/ktx) |
| AppCompat | 1.6.1 | [Link](https://developer.android.com/jetpack/androidx/releases/appcompat) |
| Material Components | 1.11.0 | [Link](https://github.com/material-components/material-components-android) |
| ConstraintLayout | 2.1.4 | [Link](https://developer.android.com/training/constraint-layout) |
| CardView | 1.0.0 | [Link](https://developer.android.com/guide/topics/ui/layout/cardview) |
| Activity KTX | 1.8.2 | [Link](https://developer.android.com/kotlin/ktx#activity) |

### 15.3 Data Structure References

- [Stack Data Structure - GeeksforGeeks](https://www.geeksforgeeks.org/stack-data-structure/)
- [LIFO Principle - Wikipedia](https://en.wikipedia.org/wiki/Stack_(abstract_data_type))

---

## Appendix A: Complete File Listing

```
StackApp/
├── .gitignore                 (16 lines)
├── README.md                  (This file)
├── build.gradle.kts           (12 lines)
├── settings.gradle.kts        (26 lines)
├── gradle.properties          (32 lines)
├── local.properties           (9 lines)
│
├── gradle/wrapper/
│   └── gradle-wrapper.properties (11 lines)
│
└── app/
    ├── build.gradle.kts       (68 lines)
    ├── proguard-rules.pro
    │
    └── src/main/
        ├── AndroidManifest.xml (37 lines)
        │
        ├── java/com/example/stackapp/
        │   ├── MainActivity.kt (279 lines)
        │   └── Stack.kt        (147 lines)
        │
        └── res/
            ├── drawable/       (16 files)
            ├── layout/
            │   └── activity_main.xml (324 lines)
            ├── mipmap-anydpi-v26/ (2 files)
            └── values/
                ├── colors.xml      (65 lines)
                ├── strings.xml     (37 lines)
                ├── themes.xml      (49 lines)
                └── ic_launcher_background.xml (4 lines)
```

---

## Appendix B: Build Instructions

### Building from Android Studio

1. Open Android Studio
2. File → Open → Select `StackApp` folder
3. Wait for Gradle sync to complete
4. Build → Make Project (Ctrl+F9)
5. Run → Run 'app' (Shift+F10)

### Building from Command Line

```bash
# Navigate to project root
cd StackApp

# Build debug APK
./gradlew assembleDebug

# APK location
# app/build/outputs/apk/debug/app-debug.apk

# Install to connected device
./gradlew installDebug
```

---

## Appendix C: Troubleshooting

| Issue | Solution |
|-------|----------|
| Gradle sync failed | File → Invalidate Caches / Restart |
| SDK not found | Update `local.properties` with correct SDK path |
| Build errors | Clean Project, then Rebuild |
| Emulator not starting | Check HAXM/Hyper-V configuration |
| App crashes on launch | Check Logcat for stack trace |

---

**Document Version:** 1.0  
**Last Updated:** December 2024  
**Prepared By:** StackApp Development Team

---

*This software documentation is intended for educational purposes and serves as a comprehensive reference for the StackApp Android application.*
