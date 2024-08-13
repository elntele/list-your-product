import com.android.build.gradle.internal.scope.ProjectInfo.Companion.getBaseName

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.br.jc.list_your_product"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.br.jc.list_your_product"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    //binding
    viewBinding {
        enable = true
    }

    productFlavors{
        flavorDimensions += "product"

        create("wetherSearch"){
            dimension = "product"
            applicationIdSuffix = ".wetherSearch"
            manifestPlaceholders["appName"]= "WetherSearch"
        }

       create("goodMovieForTheLastNight") {
            dimension ="product"
            applicationIdSuffix =".goodMovieForTheLastNight"
           manifestPlaceholders["appName"]= "goodMovieForTheLastNight"

        }
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

    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    //koin
    api("io.insert-koin:koin-android:4.0.0-RC1")
    implementation(kotlin("script-runtime"))
}