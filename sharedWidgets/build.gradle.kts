import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.10"
    id("maven-publish")
}

group = "com.brilliant.brilliant_widget_library"
version = "1.0.0"

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
        publishLibraryVariants("release")
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    /**
     * To generate the podspec file for the library, run: ./gradlew :sharedWidgets:podspec
     */
    cocoapods {
        summary = "This is a shared widget library for Kotlin Multiplatform"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "15.0"
        framework {
            baseName = "sharedWidgets"
            isStatic = false
        }
        // Maps custom Xcode configuration to NativeBuildType
        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["Development"] = NativeBuildType.DEBUG
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation("org.jetbrains.compose.runtime:runtime:1.6.10")
            implementation("org.jetbrains.compose.foundation:foundation:1.6.10")
            implementation("org.jetbrains.compose.material:material:1.6.10")
        }
        androidMain.dependencies {
            implementation("androidx.activity:activity-compose:1.8.2")
        }
        iosMain.dependencies {
            implementation("org.jetbrains.compose.ui:ui:1.6.10")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.brilliant.brilliant_widget_library"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

/**
 * To publish the library -
 * `./gradlew :sharedWidgets:publishToMavenLocal`
 */
publishing {
    repositories {
        mavenLocal() // Publish to local Maven repository
    }
}