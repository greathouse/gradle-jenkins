package com.greenmoonsoftware.gradle.jenkins.publishers

class ArtifactArchiver {
    final String name
    String artifacts
    boolean allowEmptyArchive = false
    boolean onlyIfSuccessful = false
    boolean fingerprint = false
    boolean defaultExcludes = true

    ArtifactArchiver(String n) {
        name = n
    }

    String getConfig() {
        """<hudson.tasks.ArtifactArchiver>
      <artifacts>${artifacts}</artifacts>
      <allowEmptyArchive>${allowEmptyArchive}</allowEmptyArchive>
      <onlyIfSuccessful>${onlyIfSuccessful}</onlyIfSuccessful>
      <fingerprint>${fingerprint}</fingerprint>
      <defaultExcludes>${defaultExcludes}</defaultExcludes>
    </hudson.tasks.ArtifactArchiver>"""
    }
}
