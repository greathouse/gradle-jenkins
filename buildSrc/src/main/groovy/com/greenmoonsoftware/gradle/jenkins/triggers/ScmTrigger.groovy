package com.greenmoonsoftware.gradle.jenkins.triggers

class ScmTrigger {
    String spec = ''
    boolean ignorePostCommitHooks = false

    String getConfig() {
        """<hudson.triggers.SCMTrigger>
      <spec>${spec}</spec>
      <ignorePostCommitHooks>${ignorePostCommitHooks}</ignorePostCommitHooks>
    </hudson.triggers.SCMTrigger>"""
    }
}
