# Spoofax 2 Language Example

Example showing how to build Spoofax 2 languages from the command-line with Maven or Gradle.
Contains two extremely simple languages:
- `front`: default Spoofax 2 project, with the start constructor changed to `Front`.
- `back`: not a real language, but just extends `front`. Has a source dependency to `front`, and adds a 'Compile' builder to it.

## Maven Build

Requires Maven to be installed.
See https://spoofax.dev/howtos/development/setup-maven-for-spoofax-dev/ on how to install the recommended Maven version.

Run:

```
mvn clean verify
```

## Gradle Build

A Gradle wrapper is included, so Gradle does not need to be installed.
A [JDK version 8 or higher is required](https://docs.gradle.org/current/userguide/installation.html#sec:prerequisites).

Run:

```
./gradlew build
```

The Gradle build does not build Eclipse plugins, features, and update sites.
