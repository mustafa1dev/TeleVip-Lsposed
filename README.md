# TeleVip LSPosed

<p>
  <img src="https://img.shields.io/badge/Platform-Android-green">
  <img src="https://img.shields.io/badge/Framework-LSPosed%20%7C%20Vector-blue">
  <img src="https://img.shields.io/badge/Xposed%20API-93%20%7C%20102-blueviolet">
  <img src="https://img.shields.io/badge/License-GPL--3.0-orange">
</p>

A powerful Xposed module that adds advanced customization features to Telegram clients.


## ✨ Features

### Privacy
- Hide "Seen" status in:
    - Private chats
    - Channels and Groups
- Hide "Typing..." indicator
- Hide online status
- Hide phone number
- Hide story view status
- Show deleted messages
- Prevent deletion of secret media

### Media & Stories
- Save protected stories to gallery
- Save voice messages
- Enable secret media
- Save message edit history

### Telegram Modifications
- Remove content saving restrictions
- Disable stories
- Hide pinned messages
- Disable channel swipe
- Disable profile swipe
- Disable update notifications
- Disable number rounding

### Performance
- Boost Telegram download speed

### Premium
- Enable Local Premium


> More features are available but not listed here.


# 📱 Supported Clients

| Client | Version |
|---|---|
| Telegram | 12.8.3 (69222) |
| Telegram Beta | 12.9.0 (69579) |
| Telegram Web | 12.8.3 (69229) |
| TG Connect | 11.13.1 (11130109) |
| Plus Messenger | 12.8.1.0 (22350) |
| Nagram | 12.8.1 (1239) |
| NagramX | 12.8.1-2bcd1bd (1253) |
| Nagram XF | 12.7.3 (1245) |
| Nekogram | 12.8.1 (69160) |
| Nekogram X | any (`nekox.messenger`, unobfuscated) |
| Cherrygram | 12.8.1 (69160) |
| Nicegram | 1.55.0 (2139) |
| iMe | 12.8.1 (12080102) |
| iMe Direct | 12.8.1 (12080109) |
| X Plus | 12.0.1 (61669) |
| ForkClient | 12.8.4.0 (691908) |
| ForkClient Beta | 12.8.4.0 (691909) |
| Skygram | 10.20.6 (40639) |
| Teegra | 10.3.2 (41469) |
| Telegraph | 12.8.1.1 (69172) |
| Telega | 2.4.3 (107) |
| Momogram | 12.6.4 |
| Forkgram Classic | 12.8.10.0 |
| Turrit | 1.8.9.9.5 |


# 🧩 Supported frameworks

TeleVip ships **two entry points** and uses whichever one the installed framework activates, so a
single APK covers both the old and the new Xposed module contracts.

| Framework | Module API | Entry point |
|---|---|---|
| Vector 2.2+ (JingMatrix) | 102 (modern libxposed) | `META-INF/xposed/java_init.list` → `com.my.televip.xposed.TeleVipModule` |
| LSPosed 1.10+ | 100/102 (modern libxposed) | same as above |
| LSPosed (older), EdXposed, LSPatch | 93 (legacy) | `assets/xposed_init` → `com.my.televip.MainHook` |

Zygisk providers: **Zygisk Next / NeoZygisk**, Magisk built-in Zygisk and KernelSU are all supported —
the module talks to the Xposed framework only through `com.my.televip.xposed.XBridge` and never
assumes a particular loader. In particular it no longer depends on `initZygote()` for its own APK
path, which is what used to break language loading on app-scoped modules under Zygisk Next.



# 🛠️ Building

```bash
./gradlew :app:assembleRelease
```

Requirements: JDK 17, Android SDK 36. The two Xposed APIs are `compileOnly` dependencies
(`de.robv.android.xposed:api:82` and `io.github.libxposed:api:102.0.0`), so neither is packaged —
the framework provides its own implementation at runtime.

The `Nekogram` and `Cherrygram` resolvers hold R8 name mappings that are **specific to one client
build**. When a mismatch is detected TeleVip now logs a single explicit warning at startup instead
of failing silently; regenerate those tables when either client updates.



# 📢 Updates

All TeleVip updates are published on Telegram:

➡️ https://t.me/t_l0_e


# ⚠️ Warning
> This module is intended for educational purposes only. Its use may result in issues with your Telegram account, including the risk of banning or suspension. Use it at your own risk.


# 📄 License

This project is licensed under the **GNU General Public License v3.0 (GPLv3)**.

See the [LICENSE](./LICENSE) file for more information.


# Credits

Partially based on:

- [Re-Telegram](https://github.com/Sakion-Team/Re-Telegram).


Developed by **@mustafa1dev**