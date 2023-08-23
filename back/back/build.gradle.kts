plugins {
  `java-library`
  id("org.metaborg.spoofax.gradle.langspec")
}

dependencies {
  sourceLanguage(project(":front"))
}
