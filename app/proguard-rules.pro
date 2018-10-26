# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/hanxiaoxing/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-ignorewarnings
# 抑制警告
-optimizationpasses 5
#代码混淆的压缩比例，值在0-7之间

-dontusemixedcaseclassnames
#混淆后类名都为小写

-dontskipnonpubliclibraryclasses
#指定不去忽略非公共的库的类

-dontskipnonpubliclibraryclassmembers
#指定不去忽略非公共的库的类的成员

-dontpreverify
#不做预校验的操作

-verbose
-printmapping proguardMapping.txt
#生成原类名和混淆后的类名的映射文件

-optimizations !code/simplification/cast,!field/*,!class/merging/*
#指定混淆是采用的算法

-keepattributes *Annotation*,InnerClasses
#不混淆Annotation

-keepattributes Signature
#不混淆泛型

-keepattributes SourceFile,LineNumberTable


-dontwarn com.squareup.okhttp.**

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}
-keepclassmembers class * {
    void *(**On*Event);
}

#保持 native 方法不被混淆
 -keepclasseswithmembernames class * {
     native <methods>;
 }
 #保持自定义控件类不被混淆
 -keepclasseswithmembers class * {
     public <init>(android.content.Context, android.util.AttributeSet);
 }

 #保持自定义控件类不被混淆
 -keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
 }


 # Butterknife
 -dontwarn butterknife.internal.**
 -keep class butterknife.** { *; }
 -keep class **$$ViewInjector { *; }

 -keepclasseswithmembernames class * {
     @butterknife.InjectView <fields>;
 }

 -keepclasseswithmembernames class * {
     @butterknife.OnClick <methods>;
     @butterknife.OnEditorAction <methods>;
     @butterknife.OnItemClick <methods>;
     @butterknife.OnItemLongClick <methods>;
     @butterknife.OnLongClick <methods>;
 }

 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public class * extends com.bumptech.glide.module.AppGlideModule
 -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }

 # for DexGuard only
# -keepresourcexmlelements manifest/application/meta-data@value=GlideModule