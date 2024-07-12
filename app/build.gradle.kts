plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.relay)
}

android {
    namespace = "com.uam.bolsatrabajo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.uam.bolsatrabajo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.sourceforge.jtds)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.lottie)
    implementation(libs.exoplayer)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.google.exoplayer.ui)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.material3) // Asegurar que solo se usa material3
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.play.services.cast.framework)
    testImplementation(libs.junit)
    implementation(libs.accompanist.navigation.animation)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.1.0")

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)

    // ViewModel Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx.v283)
    implementation(libs.androidx.lifecycle.viewmodel.compose.v283)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.runtime.livedata)

    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.logging.interceptor)
    implementation(libs.converter.scalars)
    implementation(libs.androidx.material)
}

// Asegúrate de tener esto al final del archivo
apply(plugin = "com.google.gms.google-services")

// Añade esta configuración para manejar las dependencias de las tareas
project.afterEvaluate {
    tasks.configureEach {
        if (name == "mapDebugSourceSetPaths") {
            mustRunAfter(tasks.named("processDebugGoogleServices"))
        }
    }
}
