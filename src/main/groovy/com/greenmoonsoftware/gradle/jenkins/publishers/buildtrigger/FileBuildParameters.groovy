package com.greenmoonsoftware.gradle.jenkins.publishers.buildtrigger

class FileBuildParameters {
    final name
    String propertiesFile = ''
    boolean failTriggerOnMissing = false
    boolean useMatrixChild = false
    boolean onlyExactRuns = false

    FileBuildParameters(n) {
        name = n
    }

    String getConfig() {
        """<hudson.plugins.parameterizedtrigger.FileBuildParameters>
              <propertiesFile>${propertiesFile}</propertiesFile>
              <failTriggerOnMissing>${failTriggerOnMissing}</failTriggerOnMissing>
              <useMatrixChild>${useMatrixChild}</useMatrixChild>
              <onlyExactRuns>${onlyExactRuns}</onlyExactRuns>
            </hudson.plugins.parameterizedtrigger.FileBuildParameters>"""
    }
}
