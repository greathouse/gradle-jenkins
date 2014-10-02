package com.greenmoonsoftware.gradle.jenkins.properties

import com.greenmoonsoftware.gradle.jenkins.properties.pipeline.Pipeline

class Props {
    final name
    Pipeline pipeline

    Props(n) {
        name = n
    }

    def pipeline(Closure c) {
        pipeline = new Pipeline()
        pipeline.with c
    }

    String getConfig() {
        def config = new StringBuilder()
        config << pipeline?.config
        config
    }
}
