plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.newshub"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.newshub"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    viewBinding {
        enable = true
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
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //paging
    implementation("androidx.paging:paging-runtime:3.2.1")

    //sdp
    implementation("com.intuit.sdp:sdp-android:1.1.0")

    //ssp
    implementation("com.intuit.ssp:ssp-android:1.1.0")

    //picasso
    implementation("com.squareup.picasso:picasso:2.8")
}