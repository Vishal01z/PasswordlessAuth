<div align="center">

# 🔐 Passwordless Authentication

### Modern Android OTP-based Authentication System

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.23-purple.svg?style=for-the-badge&logo=kotlin)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5.4-brightgreen.svg?style=for-the-badge&logo=jetpack-compose)](https://developer.android.com/jetpack/compose)
[![Material Design 3](https://img.shields.io/badge/Material%20Design-3-blue.svg?style=for-the-badge&logo=material-design)](https://m3.material.io/)
[![Min SDK](https://img.shields.io/badge/Min%20SDK-24-orange.svg?style=for-the-badge&logo=android)](https://developer.android.com)

**Email-based passwordless authentication with OTP verification**

[Features](#-features) • [Architecture](#-architecture) • [Tech Stack](#-tech-stack) • [Getting Started](#-getting-started)

</div>

---

## ✨ Features

<table>
<tr>
<td width="50%">

### 🔑 Authentication
- ✅ Email-based passwordless login
- ✅ 6-digit OTP generation
- ✅ Real-time 60s countdown timer
- ✅ Smart attempt limiting (max 3)
- ✅ Secure OTP resend mechanism

</td>
<td width="50%">

### 💎 User Experience
- ✅ Material Design 3 UI
- ✅ Live session tracking
- ✅ Configuration change handling
- ✅ Comprehensive analytics
- ✅ Intuitive error messages

</td>
</tr>
</table>

---

## 🏗️ Architecture

**MVVM (Model-View-ViewModel)** with clean architecture principles:

```
📦 com.yourapp.passwordlessauth
 ┣ 📂 data
 ┃ ┣ 📜 OtpData.kt          # Data model
 ┃ ┗ 📜 OtpManager.kt       # Business logic
 ┣ 📂 ui
 ┃ ┣ 📂 login
 ┃ ┃ ┗ 📜 LoginScreen.kt    # Email input
 ┃ ┣ 📂 otp
 ┃ ┃ ┗ 📜 OtpScreen.kt      # OTP verification
 ┃ ┣ 📂 session
 ┃ ┃ ┗ 📜 SessionScreen.kt  # Active session
 ┃ ┗ 📂 theme
 ┃   ┗ 📜 Theme.kt          # MD3 theming
 ┣ 📂 viewmodel
 ┃ ┗ 📜 AuthViewModel.kt    # State management
 ┣ 📂 navigation
 ┃ ┗ 📜 AppNavGraph.kt      # Navigation
 ┗ 📜 MainActivity.kt        # Entry point
```

---

## 🛠️ Tech Stack

<div align="center">

| Category | Technology |
|----------|-----------|
| **Language** | ![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=kotlin&logoColor=white) |
| **UI Framework** | ![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=flat-square&logo=jetpack-compose&logoColor=white) |
| **Design System** | ![Material Design](https://img.shields.io/badge/Material%20Design%203-757575?style=flat-square&logo=material-design&logoColor=white) |
| **Architecture** | ![MVVM](https://img.shields.io/badge/MVVM-FF6F00?style=flat-square) |
| **Async** | ![Coroutines](https://img.shields.io/badge/Coroutines-7F52FF?style=flat-square&logo=kotlin&logoColor=white) |
| **Navigation** | ![Navigation Compose](https://img.shields.io/badge/Navigation%20Compose-4285F4?style=flat-square) |
| **Logging** | ![Timber](https://img.shields.io/badge/Timber-3DDC84?style=flat-square&logo=android&logoColor=white) |

</div>

### Key Compose Concepts

```
✓ @Composable functions          ✓ State hoisting pattern
✓ remember & rememberSaveable    ✓ Recomposition handling
✓ LaunchedEffect for side effects ✓ Unidirectional data flow
```

---

## 📋 Requirements

| Requirement | Version |
|------------|---------|
| **Android Studio** | Hedgehog (2023.1.1)+ |
| **Minimum SDK** | 24 (Android 7.0) |
| **Target SDK** | 34 (Android 14) |
| **Kotlin** | 1.9.23 |
| **Gradle** | 8.7+ |

---

## 🚀 Getting Started

### Clone the Repository

```bash
git clone https://github.com/yourusername/passwordless-auth.git
cd passwordless-auth
```

### Open in Android Studio

1. Launch **Android Studio**
2. Select **"Open an Existing Project"**
3. Navigate to the cloned directory
4. Wait for Gradle sync

### Run the App

```bash
# Connect device or start emulator
./gradlew installDebug

# Or press Shift + F10 in Android Studio
```

---

## 🔐 How It Works

### OTP Flow

```
Enter Email → Generate OTP → Store with Expiry → Enter OTP → Validate
                ↑                                              ↓
                └─────────── Resend (if expired) ─────────────┘
```

### Core Logic

#### **1. OTP Generation**
```kotlin
val otp = (100000..999999).random().toString()
val expiryTime = System.currentTimeMillis() + 60_000
otpStore[email] = OtpData(otp, expiryTime, attemptsLeft = 3)
```

#### **2. Live Timer Implementation**
```kotlin
LaunchedEffect(Unit) {
    while (true) {
        delay(1000)
        remainingTime = viewModel.remainingTime() / 1000
        if (remainingTime <= 0) break
    }
}
```

#### **3. Validation Logic**
```kotlin
fun validateOtp(email: String, enteredOtp: String): Boolean {
    val data = otpStore[email] ?: return false
    
    return when {
        System.currentTimeMillis() > data.expiryTime -> false
        data.attemptsLeft <= 0 -> false
        data.otp != enteredOtp -> {
            data.attemptsLeft--
            false
        }
        else -> true
    }
}
```

---

## 📊 Data Structures

### `OtpData` Model

```kotlin
data class OtpData(
    val otp: String,           // 6-digit code
    val expiryTime: Long,      // Unix timestamp
    var attemptsLeft: Int = 3  // Mutable counter
)
```

**Design Rationale:**
- ✅ **Immutable OTP** → Prevents accidental modification
- ✅ **Timestamp-based expiry** → More reliable than countdown
- ✅ **Mutable attempts** → Efficient decrementing without object recreation

### Storage Strategy

```kotlin
private val otpStore = mutableMapOf<String, OtpData>()
```

**Why `Map` over single variable?**

| Aspect | Benefit |
|--------|---------|
| **Multi-user support** | Each email has isolated OTP |
| **O(1) lookup** | Fast retrieval by email key |
| **Isolation** | Failed attempts don't affect other users |

---

## 📈 Analytics Events

<div align="center">

| Event | Trigger | Level |
|-------|---------|-------|
| `OTP_GENERATED` | User requests OTP | `DEBUG` |
| `OTP_SUCCESS` | Correct OTP entered | `INFO` |
| `OTP_FAILED` | Wrong/Expired OTP | `ERROR` |
| `USER_LOGOUT` | Session ended | `INFO` |

</div>

### Example Logs

```
🟦 D/OTP_AUTH: OTP generated for user@example.com: 123456
🟩 I/OTP_AUTH: OTP validation success for user@example.com
🟥 E/OTP_AUTH: OTP validation failure - Incorrect OTP. Attempts left: 2
🟩 I/OTP_AUTH: User logged out - Session duration: 05:23
```

---

## 🎯 Why Timber?

<table>
<tr>
<td width="50%">

### ✅ Advantages
- **Lightweight** (50KB)
- **Zero configuration**
- **Offline-first**
- **Production-ready**
- **Debug tree filtering**

</td>
<td width="50%">

### ❌ Alternatives Considered
- ~~Firebase Analytics~~ → Requires Google Services
- ~~Sentry~~ → Needs API keys, network
- ~~Logcat~~ → No release builds

</td>
</tr>
</table>

### Implementation

```kotlin
// MainActivity.kt
if (BuildConfig.DEBUG) {
    Timber.plant(Timber.DebugTree())
}

// Usage
Timber.tag("OTP_AUTH").d("OTP generated for $email")
```

---

## 🧪 Edge Cases Handled

| Scenario | Solution |
|----------|----------|
| 🕐 **Expired OTP** | Shows "OTP expired", disables verify |
| ❌ **Wrong OTP** | Decrements attempts, shows count |
| 🚫 **Max attempts** | Disables verify, prompts resend |
| 🔄 **Screen rotation** | Persists with ViewModel |
| 📧 **Empty email** | Disables send button |
| ⚠️ **Invalid email** | Validates before sending |
| ⚡ **Rapid resend** | Invalidates previous OTP |
| ⬅️ **Back navigation** | Clears OTP state |

---

## 🤖 AI Assistance Disclosure

<table>
<tr>
<td width="50%" valign="top">

### 🤖 AI-Assisted
- Timber SDK syntax
- Compose best practices
- MD3 theming setup
- Navigation boilerplate
- Build config debugging

</td>
<td width="50%" valign="top">

### 💡 Independently Implemented
- OTP generation logic
- Data structure design
- State management
- Timer implementation
- Attempt limiting
- Navigation flow
- UI/UX decisions
- Edge case handling

</td>
</tr>
</table>

---

## 📚 Learning Resources

- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Material Design 3](https://m3.material.io/)
- [Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)
- [Android Architecture Guide](https://developer.android.com/topic/architecture)

---

## 📝 Assignment Context

```
Timebox: 6-7 hours
Objective: Build passwordless auth with OTP
Backend: Local implementation (no server)
```

**Demonstrates:**
- ✅ Jetpack Compose proficiency
- ✅ State management expertise
- ✅ Clean architecture principles
- ✅ Kotlin coroutines usage
- ✅ Thoughtful data structure design
- ✅ SDK integration capability

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 📞 Contact

<div align="center">

[![GitHub](https://img.shields.io/badge/GitHub-@yourusername-181717?style=for-the-badge&logo=github)](https://github.com/yourusername)
[![Email](https://img.shields.io/badge/Email-your.email@example.com-EA4335?style=for-the-badge&logo=gmail&logoColor=white)](mailto:your.email@example.com)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-yourname-0A66C2?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/yourname)

</div>

---

<div align="center">

### ⭐ Star this repo if it helped you!

**Built with ❤️ using Jetpack Compose**

</div>
