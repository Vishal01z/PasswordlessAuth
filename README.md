Here's a copy-paste ready README.md for your GitHub repository:

markdown# 🔐 Passwordless Authentication App

A modern Android application demonstrating **passwordless authentication** using Email + OTP (One-Time Password) flow, built with **Jetpack Compose** and following **clean architecture** principles.

## ✨ Features

- ✅ **Email-based Authentication** - No password required
- ✅ **6-digit OTP Generation** - Locally generated and validated
- ✅ **Real-time OTP Expiry** - 60-second countdown timer
- ✅ **Attempt Limiting** - Maximum 3 verification attempts
- ✅ **Session Management** - Live session duration tracking
- ✅ **Resend OTP** - Invalidates old OTP and resets attempts
- ✅ **Analytics Logging** - Integrated with Timber for event tracking
- ✅ **Material Design 3** - Modern, clean UI with Jetpack Compose
- ✅ **State Persistence** - Survives configuration changes (screen rotation)

## 🏗️ Architecture

This project follows **MVVM (Model-View-ViewModel)** architecture with clear separation of concerns:
```
app/
├── data/
│   ├── OtpData.kt           # Data model for OTP
│   └── OtpManager.kt        # Business logic for OTP management
├── ui/
│   ├── login/
│   │   └── LoginScreen.kt   # Email input screen
│   ├── otp/
│   │   └── OtpScreen.kt     # OTP verification screen
│   ├── session/
│   │   └── SessionScreen.kt # Active session screen
│   └── theme/               # Material Design 3 theming
├── viewmodel/
│   └── AuthViewModel.kt     # State management
├── navigation/
│   └── AppNavGraph.kt       # Navigation graph
└── MainActivity.kt          # App entry point
```

## 🛠️ Tech Stack

### Language & Framework
- **Kotlin** - 100% Kotlin codebase
- **Jetpack Compose** - Modern declarative UI toolkit
- **Material Design 3** - Latest Material Design components

### Architecture Components
- **ViewModel** - Lifecycle-aware state management
- **Navigation Compose** - Type-safe navigation
- **Coroutines** - Asynchronous programming
- **State Hoisting** - Unidirectional data flow

### External SDK
- **Timber** - Logging framework for analytics and debugging

### Key Compose Concepts Used
- `@Composable` functions
- `remember` and `rememberSaveable` for state
- `LaunchedEffect` for side effects
- State hoisting pattern
- Recomposition handling

## 📋 Requirements

- **Android Studio** Hedgehog (2023.1.1) or later
- **Minimum SDK** 24 (Android 7.0)
- **Target SDK** 34 (Android 14)
- **Kotlin** 1.9.23
- **Gradle** 8.7+

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/passwordless-auth.git
cd passwordless-auth
```

### 2. Open in Android Studio
- Open Android Studio
- Select "Open an Existing Project"
- Navigate to the cloned directory
- Wait for Gradle sync to complete

### 3. Build and Run
- Connect an Android device or start an emulator
- Click the "Run" button or press `Shift + F10`
- The app will install and launch automatically

## 🔑 How It Works

### OTP Logic & Expiry Handling

#### 1. **OTP Generation**
- **6-digit random number** generated using Kotlin's `random()`
- **Per-email storage** using `MutableMap<String, OtpData>`
- **Expiry timestamp** set to current time + 60 seconds
- **Initial attempts** set to 3

#### 2. **OTP Validation**
- Checks if OTP exists for the email
- Validates expiry time (current time vs. stored expiry)
- Checks remaining attempts (max 3)
- Decrements attempts on incorrect OTP
- Returns success/failure

#### 3. **Live Timer Implementation**
```kotlin
LaunchedEffect(Unit) {
    while (true) {
        delay(1000)
        remainingTime = viewModel.remainingTime() / 1000
        if (remainingTime <= 0) break
    }
}
```
- Uses `LaunchedEffect` for coroutine-based timer
- Updates every second
- Automatically stops when expired

#### 4. **Resend OTP Flow**
- Calling `generateOtp()` again **invalidates** the previous OTP
- **Resets** `attemptsLeft` to 3
- **Resets** expiry timer to 60 seconds

### Data Structures Used

#### `OtpData` (Data Class)
```kotlin
data class OtpData(
    val otp: String,           // The 6-digit OTP
    val expiryTime: Long,      // Timestamp when OTP expires
    var attemptsLeft: Int = 3  // Mutable - decrements on failed attempts
)
```

**Why this structure?**
- ✅ **Immutable OTP** - Can't be accidentally changed
- ✅ **Timestamp-based expiry** - More reliable than countdown
- ✅ **Mutable attempts** - Allows decrementing without recreating object

#### `MutableMap<String, OtpData>`
```kotlin
private val otpStore = mutableMapOf()
```

**Why Map instead of single variable?**
- ✅ **Multi-user support** - Each email has its own OTP
- ✅ **O(1) lookup** - Fast retrieval by email key
- ✅ **Isolation** - One user's failed attempts don't affect others

## 📊 Analytics Events Logged

Using **Timber**, the following events are logged:

| Event | Trigger | Log Level |
|-------|---------|-----------|
| **OTP Generated** | User requests OTP | `DEBUG` |
| **OTP Validation Success** | Correct OTP entered | `INFO` |
| **OTP Validation Failure** | Wrong OTP / Expired / Max attempts | `ERROR` |
| **Logout** | User clicks logout button | `INFO` |

### Example Log Output
```
D/OTP_AUTH: OTP generated for user@example.com: 123456
I/OTP_AUTH: OTP validation success for user@example.com
E/OTP_AUTH: OTP validation failure - Incorrect OTP. Attempts left: 2
I/OTP_AUTH: User logged out - Session duration: 05:23
```

## 🎯 External SDK: Why Timber?

I chose **Timber** over Firebase Analytics and Sentry for this project because:

### ✅ Pros
1. **Lightweight** - Only 50KB, minimal impact on APK size
2. **Zero Configuration** - Single line initialization
3. **No External Dependencies** - Works offline, no API keys needed
4. **Perfect for Development** - Easy to debug and test
5. **Production-Ready** - Can be extended with custom `Tree` implementations

### ❌ Why Not Others?
- **Firebase Analytics** - Overkill for local assignment, requires Google Services
- **Sentry** - Requires account setup, API keys, network calls

### Timber Implementation
```kotlin
// MainActivity.kt
if (BuildConfig.DEBUG) {
    Timber.plant(Timber.DebugTree())
}

// Usage
Timber.tag("OTP_AUTH").d("OTP generated for $email")
```

## 🧪 Edge Cases Handled

| Edge Case | Handling |
|-----------|----------|
| **Expired OTP** | Shows "OTP expired" message, disables verify button |
| **Incorrect OTP** | Decrements attempts, shows remaining attempts |
| **Max Attempts Exceeded** | Disables verify button, prompts to resend |
| **Screen Rotation** | State persists using ViewModel |
| **Empty Email** | Disabled "Send OTP" button |
| **Invalid Email Format** | Validation before sending OTP |
| **Rapid Resend Clicks** | Each resend invalidates previous OTP |
| **Navigation Back** | Clears OTP state properly |

## 🤖 AI Assistance Disclosure

### 🤖 Used AI/GPT For:
- ✅ Timber SDK integration syntax
- ✅ Compose UI best practices (remember vs rememberSaveable)
- ✅ Material Design 3 theming setup
- ✅ Navigation Compose boilerplate
- ✅ Debugging build configuration issues

### 💡 Implemented Independently:
- ✅ **OTP generation and validation logic**
- ✅ **Data structure design** (Map-based storage)
- ✅ **State management** (ViewModel architecture)
- ✅ **Timer implementation** (LaunchedEffect coroutine)
- ✅ **Attempt limiting mechanism**
- ✅ **Navigation flow** between screens
- ✅ **UI/UX design decisions**
- ✅ **Edge case handling**

### 📚 Understanding & Learning:
- Studied official Android Compose documentation
- Researched MVVM architecture patterns
- Experimented with different state management approaches
- Tested various timer implementation strategies
- Analyzed best practices for error handling

## 📝 Assignment Context

**Timebox:** 6-7 hours  
**Objective:** Build a passwordless authentication flow with OTP  
**Backend:** Not required - all logic implemented locally  

This project demonstrates understanding of:
- Jetpack Compose fundamentals
- State management and lifecycle handling
- Clean architecture principles
- Kotlin coroutines
- Data structure selection reasoning
- External SDK integration

## 📞 Contact

For questions or feedback about this project:
- **GitHub:** [@yourusername](https://github.com/yourusername)
- **Email:** your.email@example.com

---

⭐ **If this project helped you learn, please consider giving it a star!**

---

**Built with ❤️ using Jetpack Compose**

Instructions to Use:

Create a file named README.md in your project root (same level as app folder)
Copy the entire content above
Replace yourusername with your actual GitHub username
Replace your.email@example.com with your email
Add screenshots if you want (optional but recommended)
Commit and push to GitHub
