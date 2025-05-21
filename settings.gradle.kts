pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Add JitPack repository for PDF viewer and other libraries
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "EduLearn"
include(":app")