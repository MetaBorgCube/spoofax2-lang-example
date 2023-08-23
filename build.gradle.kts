plugins {
  // Set versions for plugins to use, only applying them in subprojects (apply false here).
  id("org.metaborg.spoofax.gradle.langspec") version "0.5.7" apply false
  id("org.metaborg.spoofax.gradle.project") version "0.5.7" apply false
  id("org.metaborg.spoofax.gradle.test") version "0.5.7" apply false
}

allprojects {
  group = "org.example"
  version = "0.1.0-SNAPSHOT"

  repositories {
    maven("https://artifacts.metaborg.org/content/groups/public/")
  }
}

subprojects {
  // Configure Java to use Java 8 and UTF-8 encoding.
  plugins.withId("java") {
    val javaVersion = JavaVersion.VERSION_1_8
    configure<JavaPluginExtension> {
      sourceCompatibility = javaVersion
      targetCompatibility = javaVersion
    }
    tasks.withType<JavaCompile> {
      options.encoding = "UTF-8"
    }
  }
}
