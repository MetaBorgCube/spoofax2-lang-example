rootProject.name = "spoofax2-lang-example"

// Get plugins from metaborg repository.
pluginManagement {
  repositories {
    maven("https://artifacts.metaborg.org/content/groups/public/")
  }
}

// Enable metadata for Gradle versions < 6.0.
if(org.gradle.util.VersionNumber.parse(gradle.gradleVersion).major < 6) {
  enableFeaturePreview("GRADLE_METADATA")
}

// Convenience function for including projects in subdirectories.
fun String.includeProject(id: String, path: String = "$this/$id") {
  include(id)
  project(":$id").projectDir = file(path)
}

"front".run {
  includeProject("front")
  includeProject("front.example")
  includeProject("front.test")
}
"back".run {
  includeProject("back")
}
