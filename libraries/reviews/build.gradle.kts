plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_ANDROID_EXTENSIONS)
    kotlin("kapt")
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }

        getByName(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    sourceSets {
        //getByName("test").java.srcDir("${project.rootDir}/app/build/generated/source/navigation-args/debug")
        getByName("main") {
            res.setSrcDirs(
                arrayListOf(
                    //"src/main/java/com/kosigo/showcase/library/catalog/catalog/res",
                    "src/main/res"
                )
            )
        }
    }

    testOptions {
        unitTests.isReturnDefaultValues = TestOptions.IS_RETURN_DEFAULT_VALUES
    }
}

dependencies {
    implementation(project(ModuleDependency.LIBRARY_BASE))

    implementation(LibraryDependency.RETROFIT)
    implementation(LibraryDependency.RETROFIT_MOSHI_CONVERTER)
    implementation(LibraryDependency.SUPPORT_CONSTRAINT_LAYOUT)
    implementation(LibraryDependency.COORDINATOR_LAYOUT)
    implementation(LibraryDependency.RECYCLER_VIEW)
    implementation(LibraryDependency.MATERIAL)
    implementation(LibraryDependency.FRAGMENT_KTX)
    implementation(LibraryDependency.K_ANDROID)
    implementation(LibraryDependency.LOTTIE)
    implementation(LibraryDependency.COIL)

    // Navigation
    //api ("androidx.navigation:navigation-runtime-ktx:2.3.0-beta01")
    //implementation("androidx.navigation:navigation-fragment-ktx:2.3.0-beta01")
    //implementation("androidx.navigation:navigation-ui-ktx:2.3.0-beta01")
    //implementation("androidx.navigation:navigation-fragment-ktx:2.2.2")
    //implementation("androidx.navigation:navigation-ui-ktx:2.2.2")
    implementation(LibraryDependency.NAVIGATION_FRAGMENT_KTX)
    implementation(LibraryDependency.NAVIGATION_UI_KTX)

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.3.7")

    implementation(LibraryDependency.FIREBASE_FIRESTORE_KTX)
    implementation(LibraryDependency.FIREBASE_UI_AUTH)
    implementation(LibraryDependency.FIREBASE_AUTH)

    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.50")
    implementation("com.google.android.material:material:1.2.0-alpha06")

    implementation("androidx.room:room-runtime:2.2.5")
    implementation("androidx.room:room-ktx:2.2.5")
    kapt("androidx.room:room-compiler:2.2.5")
    //kapt("androidx.room:room-compiler:2.2.5")


}
