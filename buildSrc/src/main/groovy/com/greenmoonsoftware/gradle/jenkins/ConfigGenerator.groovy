package com.greenmoonsoftware.gradle.jenkins

class ConfigGenerator {
    private static String description(project) {
        project.jenkins.template.description
    }

    private static String keepDependencies(project) {
        project.jenkins.template.keepDependencies
    }

    private static String properties(project) {
        (project.jenkins.template.props?.config) ?: ''
    }

    private static String canRoam(project) {
        project.jenkins.template.canRoam
    }

    private static String disabled(project) {
        project.jenkins.template.disabled
    }

    private static String blockBuildWhenDownstreamBuilding(project) {
        project.jenkins.template.blockBuildWhenDownstreamBuilding
    }

    private static String blockBuildWhenUpstreamBuilding(project) {
        project.jenkins.template.blockBuildWhenUpstreamBuilding
    }

    private static String scm(project) {
        (project.jenkins.template.scm?.config) ?: ''
    }

    private static String triggers(project) {
        (project.jenkins.template.triggers?.config) ?: ''
    }

    private static String concurrentBuild(project) {
        (project.jenkins.template.concurrentBuild) ?: ''
    }

    private static String builders(project) {
        (project.jenkins.template.builders?.config) ?: ''
    }

    private static String publishers(project) {
        (project.jenkins.template.publishers?.config) ?: ''
    }

    public static String generate(project) {
        """
<?xml version='1.0' encoding='UTF-8'?>
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
