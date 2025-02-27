import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.androidlearning.sunnyweather"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.androidlearning.sunnyweather"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") { //  可以命名为 "release"
            storeFile = (file("/Users/xiong/walterxiong.jks")) // 替换为您的密钥库文件路径
            storePassword = "xj522500" // 替换为您的密钥库密码
            keyAlias = "walterxiongdev" // 替换为您的 KeyAlias
            keyPassword = "xj522500" // 替换为您的 KeyPassword
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // 添加 Room 依赖
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    // 可选，如果使用 Kotlin 协程则推荐添加
    implementation(libs.androidx.room.ktx)

    implementation(libs.androidx.lifecycle.livedata.ktx)

    implementation(libs.jackson.module.kotlin)
    implementation(libs.androidx.swiperefreshlayout)

    implementation(libs.glide)

}