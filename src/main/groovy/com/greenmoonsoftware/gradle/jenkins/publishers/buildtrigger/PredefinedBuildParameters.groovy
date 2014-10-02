package com.greenmoonsoftware.gradle.jenkins.publishers.buildtrigger

class PredefinedBuildParameters {
    final name
    String properties

    PredefinedBuildParameters(n) {
        name = n
    }

    String getConfig() {
        """<hudson.plugins.parameterizedtrigger.PredefinedBuildParameters>
              <properties>${properties}</properties>
            </hudson.plugins.parameterizedtrigger.PredefinedBuildParameters>"""
    }
}
