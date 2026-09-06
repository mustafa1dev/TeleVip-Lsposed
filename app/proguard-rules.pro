# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# --- Xposed -------------------------------------------------------------------
# Legacy entry point, referenced by name from assets/xposed_init.
-keep class com.my.televip.MainHook { *; }

# Modern libxposed entry point, referenced by name from META-INF/xposed/java_init.list.
-dontwarn io.github.libxposed.annotation.**
-adaptresourcefilecontents META-INF/xposed/java_init.list
-keep,allowoptimization,allowobfuscation public class * extends io.github.libxposed.api.XposedModule {
    public <init>();
}

# The compat layer resolves client classes/members by name at runtime.
-keep class com.my.televip.xposed.** { *; }
-keep class com.my.televip.base.AbstractMethodHook { *; }
-keep class com.my.televip.base.AbstractMethodHook$MethodHookParam { *; }
-keepclassmembers class com.my.televip.Clients.** { *; }
