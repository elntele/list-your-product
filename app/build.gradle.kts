import com.android.build.gradle.internal.scope.ProjectInfo.Companion.getBaseName
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs")
}


android {
    namespace = "com.br.jc.list_your_product"
    compileSdk = 34

    // take token access as a variable in from local.properties
    // see the other part in buildfeatures
    val localProperties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { stream ->
            localProperties.load(stream)
        }
    }

    val tmdbAccessToken: String? = localProperties.getProperty("TMDB_ACCESS_TOKEN")

    defaultConfig {
        applicationId = "com.br.jc.list_your_product"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        //access to the token in
        buildConfigField("String", "TMDB_ACCESS_TOKEN", "\"${tmdbAccessToken}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
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
    buildFeatures {
        viewBinding {
            enable = true
        }
        // take token access as a variable in from local.properties
        // you need access the BuildConfig to get variable in project
        buildConfig = true
    }




    productFlavors{
        flavorDimensions += "product"

        create("wetherSearch"){
            dimension = "product"
            applicationIdSuffix = ".wetherSearch"
            manifestPlaceholders["appName"]= "WetherSearch"
        }

       create("movieForNight") {
            dimension ="product"
            applicationIdSuffix =".movieForNight"
           manifestPlaceholders["appName"]= "movieForNight"

        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    //koin
    api("io.insert-koin:koin-android:4.0.0-RC1")
    implementation(kotlin("script-runtime"))
    //firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-analytics")
    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
}