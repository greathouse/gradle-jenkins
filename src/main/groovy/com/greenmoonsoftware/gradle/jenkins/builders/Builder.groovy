package com.greenmoonsoftware.gradle.jenkins.builders

class Builder {
    final name
    Gradle gradleBuild

    Builder(n) {
        name = n
    }

    def gradleBuild(Closure c) {
        gradleBuild = new Gradle()
        gradleBuild.with c
    }

    String getConfig() {
        def config = new StringBuffer()
        config << gradleBuild?.config
    }
}
