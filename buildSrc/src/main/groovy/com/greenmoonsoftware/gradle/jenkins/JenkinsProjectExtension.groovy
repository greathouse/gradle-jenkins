package com.greenmoonsoftware.gradle.jenkins

class JenkinsProjectExtension {
    boolean console = false
    String url = 'http://localhost:8080'
    String jobName

    Config template = new Config()
    Config config = new Config()

    def template(Closure c) {
        template = new Config()
        template.with c
    }

    def config(Closure c) {
        config = new Config()
        config.with c
    }
}
