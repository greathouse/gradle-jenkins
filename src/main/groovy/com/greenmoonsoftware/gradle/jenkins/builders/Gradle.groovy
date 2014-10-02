package com.greenmoonsoftware.gradle.jenkins.builders

class Gradle {
    final String pluginVersion = 'gradle@1.24'
    String description = ''
    String switches = ''
    String tasks = ''
    String rootBuildScriptDir = ''
    String buildFile = ''
    String gradleName = ''
    boolean makeExecutable = false
    boolean useWrapper = false
    String fromRootBuildScriptDir = ''
    boolean useWorkspaceAsHome = false

    String getConfig() {
        """<hudson.plugins.gradle.Gradle plugin="${pluginVersion}">
      <description>${description}</description>
      <switches>${switches}</switches>
      <tasks>${tasks}</tasks>
      <rootBuildScriptDir>${rootBuildScriptDir}</rootBuildScriptDir>
      <buildFile>${buildFile}</buildFile>
      <gradleName>${gradleName}</gradleName>
      <useWrapper>${useWrapper}</useWrapper>
      <makeExecutable>${makeExecutable}</makeExecutable>
      <fromRootBuildScriptDir>${fromRootBuildScriptDir}</fromRootBuildScriptDir>
      <useWorkspaceAsHome>${useWorkspaceAsHome}</useWorkspaceAsHome>
    </hudson.plugins.gradle.Gradle>"""
    }
}
