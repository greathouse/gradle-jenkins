package com.greenmoonsoftware.gradle.jenkins.publishers

import com.greenmoonsoftware.gradle.jenkins.publishers.artifact.ArtifactArchiver
import com.greenmoonsoftware.gradle.jenkins.publishers.buildtrigger.BuildTrigger
import com.greenmoonsoftware.gradle.jenkins.publishers.htmlpublisher.HtmlPublisher
import com.greenmoonsoftware.gradle.jenkins.publishers.junit.JUnitResultArchiver
import com.greenmoonsoftware.gradle.jenkins.publishers.mailer.Mailer

class Publisher {
    final name
    ArtifactArchiver artifactArchiver
    JUnitResultArchiver jUnitResultArchiver
    HtmlPublisher htmlPublisher
    BuildTrigger buildTrigger
    Mailer mailer

    Publisher(n) {
        this.name = n
    }

    def artifactArchiver(Closure c) {
        artifactArchiver = new ArtifactArchiver()
        artifactArchiver.with c
    }

    def junitResultArchiver(Closure c) {
        jUnitResultArchiver = new JUnitResultArchiver()
        jUnitResultArchiver.with c
    }

    def htmlPublisher(Closure c) {
        htmlPublisher = new HtmlPublisher()
        htmlPublisher.with c
    }

    def buildTrigger(Closure c) {
        buildTrigger = new BuildTrigger()
        buildTrigger.with c
    }

    def mailer(Closure c) {
        mailer = new Mailer()
        mailer.with c
    }

    String getConfig() {
        def config = new StringBuilder()
        config << artifactArchiver?.config ?: ''
        config << jUnitResultArchiver?.config ?: ''
        config << htmlPublisher?.config ?: ''
        config << buildTrigger?.config ?: ''
        config << mailer?.config ?: ''
        config
    }
}