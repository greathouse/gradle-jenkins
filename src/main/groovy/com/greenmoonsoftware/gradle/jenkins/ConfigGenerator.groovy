package com.greenmoonsoftware.gradle.jenkins

class ConfigGenerator {
    private static String description(project) {
        project.jenkins.config.description
    }

    private static String keepDependencies(project) {
        project.jenkins.config.keepDependencies
    }

    private static String properties(project) {
        (project.jenkins.config.props?.config) ?: ''
    }

    private static String canRoam(project) {
        project.jenkins.config.canRoam
    }

    private static String disabled(project) {
        project.jenkins.config.disabled
    }

    private static String blockBuildWhenDownstreamBuilding(project) {
        project.jenkins.config.blockBuildWhenDownstreamBuilding
    }

    private static String blockBuildWhenUpstreamBuilding(project) {
        project.jenkins.config.blockBuildWhenUpstreamBuilding
    }

    private static String scm(project) {
        (project.jenkins.config.scm?.config) ?: ''
    }

    private static String triggers(project) {
        (project.jenkins.config.triggers?.config) ?: ''
    }

    private static String concurrentBuild(project) {
        (project.jenkins.config.concurrentBuild) ?: ''
    }

    private static String builders(project) {
        (project.jenkins.config.builders?.config) ?: ''
    }

    private static String publishers(project) {
        (project.jenkins.config.publishers?.config) ?: ''
    }

    public static String generate(project) {
        """\
<project>
  <actions/>
  <description>${description(project)}</description>
  <keepDependencies>${keepDependencies(project)}</keepDependencies>
  <properties>
    ${properties(project)}
  </properties>
  ${scm(project)}
  <canRoam>${canRoam(project)}</canRoam>
  <disabled>${disabled(project)}</disabled>
  <blockBuildWhenDownstreamBuilding>${blockBuildWhenDownstreamBuilding(project)}</blockBuildWhenDownstreamBuilding>
  <blockBuildWhenUpstreamBuilding>${blockBuildWhenUpstreamBuilding(project)}</blockBuildWhenUpstreamBuilding>
  <triggers>
    ${triggers(project)}
  </triggers>
  <concurrentBuild>${concurrentBuild(project)}</concurrentBuild>
  <builders>
    ${builders(project)}
  </builders>
  <publishers>
    ${publishers(project)}
  </publishers>
  <buildWrappers/>
</project>
"""
    }
}
