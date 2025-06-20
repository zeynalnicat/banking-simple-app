
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("com.google.devtools.ksp")
    alias (libs.plugins.room)
//    id("com.squareup.sqldelight") version "2.0.0"

}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }



    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }



    sourceSets {
        val commonMain by getting
        val commonTest by getting

        val androidMain by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(libs.native.driver)
            }
        }


        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.material)


            implementation(libs.androidx.material.icons.core)
            implementation(libs.androidx.material3)
            implementation(libs.android.driver)
            implementation(libs.androidx.material)


        }
        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                implementation(libs.runtime)
                implementation(libs.sqlite.bundled)
                implementation(libs.androidx.room.runtime)

                implementation(compose.materialIconsExtended)
                implementation(libs.navigation)
                implementation(libs.cmp.preference)

                // Remove Android lifecycle & navigation libs here:
                // implementation(libs.androidx.lifecycle.viewmodel.compose)
                // implementation(libs.androidx.lifecycle.viewmodel)

                // implementation(libs.androidx.lifecycle.runtimeCompose)
            }
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        iosMain.dependencies {
            implementation(libs.native.driver)
        }
    }

}

configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "com.intellij" && requested.name == "annotations") {
            useTarget("org.jetbrains:annotations:23.0.0")
        }
    }
}

android {
    namespace = "org.banking.simple.app"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.banking.simple.app"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}



room {
    schemaDirectory("$projectDir/schemas")
}


dependencies {
    ksp(libs.androidx.room.compiler)
    debugImplementation(compose.uiTooling)
}




