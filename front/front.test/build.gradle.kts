plugins {
  id("org.metaborg.spoofax.gradle.test")
}

spoofaxTest {
  languageUnderTest.set(org.metaborg.core.language.LanguageIdentifier.parse("org.example:front:0.1.0-SNAPSHOT"))
}

dependencies {
  compileLanguage(project(":front"))
}

// HACK: also run tests when building, not just when checking. Needs to be fixed in Spoofax 2 Gradle plugin.
tasks.getByName("build").dependsOn("spoofaxTest")
