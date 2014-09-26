package com.greenmoonsoftware.gradle.jenkins.scm

class Scm {
    final name
    Git git

    Scm(n) {
        name = n
    }

    def git(Closure c) {
        git = new Git()
        git.with c
    }

    String getConfig() {
        git.config
    }
}
