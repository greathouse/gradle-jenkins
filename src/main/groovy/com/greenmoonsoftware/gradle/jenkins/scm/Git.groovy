package com.greenmoonsoftware.gradle.jenkins.scm

class Git {
    final pluginVersion = 'git@2.2.2'
    String config = ''
    String url = ''
    String branches = '*/master'
    boolean doGenerateSubmoduleConfigurations = false
    PathRestriction pathRestriction

    def pathRestriction(Closure c) {
        pathRestriction = new PathRestriction()
        pathRestriction.with c
    }

    private extensions() {
        def config = new StringBuilder('<extensions>\n')
        config << include(pathRestriction)
        config << '\n</extensions>'
        config
    }

    private String include(extension) {
        extension ? extension.config : ''
    }

    String getConfig() {
        """<scm class="hudson.plugins.git.GitSCM" plugin="${pluginVersion}">
    <configVersion>2</configVersion>
    <userRemoteConfigs>
      <hudson.plugins.git.UserRemoteConfig>
        <url>${url}</url>
      </hudson.plugins.git.UserRemoteConfig>
    </userRemoteConfigs>
    <branches>
      <hudson.plugins.git.BranchSpec>
        <name>${branches}</name>
      </hudson.plugins.git.BranchSpec>
    </branches>
    <doGenerateSubmoduleConfigurations>${doGenerateSubmoduleConfigurations}</doGenerateSubmoduleConfigurations>
    <submoduleCfg class="list"/>
    ${extensions()}
  </scm>"""
    }
}

