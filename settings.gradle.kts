pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://agora.dequecloud.com:443/artifactory/libs-release")
            credentials {
                val localProps = java.util.Properties().apply {
                    val f = file("local.properties")
                    if (f.exists()) load(f.inputStream())
                }
                username = localProps["axe.username"] as String? ?: ""
                password = localProps["axe.password"] as String? ?: ""
            }
        }
    }
}

rootProject.name = "EmbelShop"
include(":app")
