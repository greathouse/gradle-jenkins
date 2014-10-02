package com.greenmoonsoftware.gradle.jenkins.scm

class PathRestriction {
    final name
    String includedRegions = ''
    String excludedRegions = ''

    PathRestriction(n) {
        name = n
    }

    String getConfig() {
        """<hudson.plugins.git.extensions.impl.PathRestriction>
        <includedRegions>${includedRegions}</includedRegions>
        <excludedRegions>${excludedRegions}</excludedRegions>
      </hudson.plugins.git.extensions.impl.PathRestriction>"""
    }
}
