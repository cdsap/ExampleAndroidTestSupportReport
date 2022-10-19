plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

afterEvaluate {
    tasks.withType<com.android.build.gradle.internal.tasks.DeviceProviderInstrumentTestTask>().names.forEach { // 2
        com.gradle.enterprise.gradleplugin.test.ImportJUnitXmlReports.register(
            tasks,
            tasks.named(it),
            com.gradle.enterprise.gradleplugin.test.JUnitXmlDialect.ANDROID_CONNECTED
        )
    }
}


android {
    namespace = "com.example.myapplication"

    defaultConfig {

        namespace = "com.example.myapplication"
        versionCode = 1
        versionName = " 1"
        compileSdkVersion = "android-33"
        targetSdk = 33
        minSdk = 23
    }
    buildTypes {
        val debug by getting {
            applicationIdSuffix = ".debug"
        }
        val release by getting {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )

            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
