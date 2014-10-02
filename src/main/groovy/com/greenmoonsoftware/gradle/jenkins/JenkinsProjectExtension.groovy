package com.greenmoonsoftware.gradle.jenkins

class JenkinsProjectExtension {
    boolean console = false
    String url = 'http://localhost:8080'
    String jobName

    Config config = new Config()

    def config(Closure c) {
        config = new Config()
        config.with c
    }
}
