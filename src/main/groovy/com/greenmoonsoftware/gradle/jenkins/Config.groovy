package com.greenmoonsoftware.gradle.jenkins

import com.greenmoonsoftware.gradle.jenkins.builders.Builder
import com.greenmoonsoftware.gradle.jenkins.properties.Props
import com.greenmoonsoftware.gradle.jenkins.publishers.Publisher
import com.greenmoonsoftware.gradle.jenkins.scm.Scm
import com.greenmoonsoftware.gradle.jenkins.triggers.Trigger

class Config {
    final name
    String description = ''
    boolean keepDependencies = false
    Props props
    Scm scm
    boolean canRoam = false
    boolean disabled = false
    boolean blockBuildWhenDownstreamBuilding = false
    boolean blockBuildWhenUpstreamBuilding = false
    Trigger triggers
    boolean concurrentBuild = false
    Builder builders
    Publisher publishers

    Config(n) {
        name = n
    }

    def props(Closure c) {
        props = new Props()
        props.with c
    }

    def scm(Closure c) {
        scm = new Scm()
        scm.with c
    }

    def triggers(Closure c) {
        triggers = new Trigger()
        triggers.with c
    }

    def builders(Closure c) {
        builders = new Builder()
        builders.with c
    }

    def publishers(Closure c) {
        publishers = new Publisher()
        publishers.with c
    }
}
