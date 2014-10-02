package com.greenmoonsoftware.gradle.jenkins.triggers

class Trigger {
    final name
    ScmTrigger scmTrigger

    Trigger(n) {
        name = n
    }

    def scmTrigger(Closure c) {
        scmTrigger = new ScmTrigger()
        scmTrigger.with c
    }

    String getConfig() {
        def config = new StringBuilder()
        config << scmTrigger?.config
    }
}
