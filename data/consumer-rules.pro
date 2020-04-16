-keep class kotlin.SynchronizedLazyImpl { *; }
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.SerializationKt
-keep,includedescriptorclasses class com.sorrowblue.qiichan.data.**$$serializer { *; } # <-- change package name to your app's
-keepclassmembers class com.sorrowblue.qiichan.data.AccessToken { # <-- change package name to your app's
    *** Companion;
}
-keepclasseswithmembers class com.sorrowblue.qiichan.data.AccessToken { # <-- change package name to your app's
    kotlinx.serialization.KSerializer serializer(...);
}
