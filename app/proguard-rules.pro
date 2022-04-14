-verbose
-allowaccessmodification
-repackageclasses

# Note that you cannot just include these flags in your own
# configuration file; if you are including this file, optimization
# will be turned off. You'll need to either edit this file, or
# duplicate the contents of this file and remove the include of this
# file from your project's proguard.config path property.

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# We only need to keep ComposeView + FragmentContainerView
-keep public class androidx.compose.ui.platform.ComposeView {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# For enumeration classes
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-dontwarn androidx.**

-keepattributes SourceFile,
                LineNumberTable,
                RuntimeVisibleAnnotations,
                RuntimeVisibleParameterAnnotations,
                RuntimeVisibleTypeAnnotations,
                AnnotationDefault

-renamesourcefileattribute SourceFile

-dontwarn org.conscrypt.**

# Dagger
-dontwarn com.google.errorprone.annotations.*

# Retain the generic signature of retrofit2.Call until added to Retrofit.
# Issue: https://github.com/square/retrofit/issues/3580.
# Pull request: https://github.com/square/retrofit/pull/3579.
-keep,allowobfuscation,allowshrinking class retrofit2.Call

# Retain annotation default values for all annotations.
# Required until R8 version >= 3.1.12+ (in AGP 7.1.0+).
-keep,allowobfuscation,allowshrinking @interface *

#-ignorewarnings
#-keep class com.huawei.hianalytics.**{*;}
#-keep class com.huawei.agconnect.**{*;}
#-dontwarn com.huawei.agconnect.**
#-keep class com.hianalytics.android.**{*;}
#-keep class com.huawei.updatesdk.**{*;}
#-keep class com.huawei.hms.**{*;}
#-keep interface com.huawei.hms.analytics.type.HAEventType{*;}
#-keep interface com.huawei.hms.analytics.type.HAParamType{*;}
#-keepattributes Exceptions, Signature, InnerClasses, LineNumberTable

-ignorewarnings
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.huawei.hianalytics.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}

# Chucker uses GSON and TypeToken:
# https://r8.googlesource.com/r8/+/refs/heads/main/compatibility-faq.md#gson-with-full-mode
-keepattributes Signature
-keep class com.google.gson.reflect.TypeToken { *; }
-keep class * extends com.google.gson.reflect.TypeToken

# okhttp
-keepattributes Signature
-keepattributes Annotation
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}

-keep @com.squareup.moshi.JsonQualifier @interface *

# Enum field names are used by the integrated EnumJsonAdapter.
# values() is synthesized by the Kotlin compiler and is used by EnumJsonAdapter indirectly
# Annotate enums with @JsonClass(generateAdapter = false) to use them with Moshi.
-keepclassmembers @com.squareup.moshi.JsonClass class * extends java.lang.Enum {
    <fields>;
    **[] values();
}

# Keep helper method to avoid R8 optimisation that would keep all Kotlin Metadata when unwanted
-keepclassmembers class com.squareup.moshi.internal.Util {
    private static java.lang.String getKotlinMetadataClassName();
}

# Keep ToJson/FromJson-annotated methods
-keepclassmembers class * {
  @com.squareup.moshi.FromJson <methods>;
  @com.squareup.moshi.ToJson <methods>;
}

-keep class com.developersancho.model.** { *; }
-keepclasseswithmembers class com.developersancho.model.** { *; }