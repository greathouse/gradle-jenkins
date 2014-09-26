package com.greenmoonsoftware.gradle.jenkins.publishers.buildtrigger

class BuildTrigger {
    final version = 'parameterized-trigger@2.25'
    final name
    PredefinedBuildParameters predefinedBuildParameters
    FileBuildParameters fileBuildParameters
    String projects = ''
    String condition = 'SUCCESS'
    boolean triggerWithNoParameters = false

    BuildTrigger(n) {
        name = n
    }

    def predefinedParameters(Closure c) {
        predefinedBuildParameters = new PredefinedBuildParameters()
        predefinedBuildParameters.with c
    }

    def fileParameters(Closure c) {
        fileBuildParameters = new FileBuildParameters()
        fileBuildParameters.with c
    }

    String getConfig() {
        """<hudson.plugins.parameterizedtrigger.BuildTrigger plugin="${version}">
      <configs>
        <hudson.plugins.parameterizedtrigger.BuildTriggerConfig>
          <configs>
            ${predefinedBuildParameters?.config ?: ''}
            ${fileBuildParameters?.config ?: ''}
          </configs>
          <projects>${projects}</projects>
          <condition>${condition}</condition>
          <triggerWithNoParameters>${triggerWithNoParameters}</triggerWithNoParameters>
        </hudson.plugins.parameterizedtrigger.BuildTriggerConfig>
      </configs>
    </hudson.plugins.parameterizedtrigger.BuildTrigger>"""
    }
}
