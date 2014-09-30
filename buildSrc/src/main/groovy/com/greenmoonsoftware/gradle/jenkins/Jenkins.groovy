package com.greenmoonsoftware.gradle.jenkins

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.greenmoonsoftware.gradle.jenkins.publishers.Publisher

class Jenkins implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.configure(project) {
            extensions.create("jenkins", JenkinsProjectExtension)
        }

        project.task('jenkins') << {
            writeFile(project)
        }
    }

    private void writeFile(project) {
        def outputDir = "${project.buildDir}/jenkins"
        new File(outputDir).mkdirs()
        def outputFilename = "${outputDir}/config.xml"
        def xml = generate(project)
        if (project.jenkins.console) {
            println xml
        }
        new File(outputFilename).withWriter { w ->
            w.write(xml)
        }
        project.logger.lifecycle "Jenkins config.xml written to ${outputFilename}"
    }

    private String description(project) {
        project.jenkins.template.description
    }

    private String keepDependencies(project) {
        project.jenkins.template.keepDependencies
    }

    private String properties(project) {
        (project.jenkins.template.props?.config) ?: ''
    }

    private String canRoam(project) {
        project.jenkins.template.canRoam
    }

    private String disabled(project) {
        project.jenkins.template.disabled
    }

    private String blockBuildWhenDownstreamBuilding(project) {
        project.jenkins.template.blockBuildWhenDownstreamBuilding
    }

    private String blockBuildWhenUpstreamBuilding(project) {
        project.jenkins.template.blockBuildWhenUpstreamBuilding
    }

    private String scm(project) {
        (project.jenkins.template.scm?.config) ?: ''
    }

    private String triggers(project) {
        (project.jenkins.template.triggers?.config) ?: ''
    }

    private String concurrentBuild(project) {
        (project.jenkins.template.concurrentBuild) ?: ''
    }

    private String builders(project) {
        (project.jenkins.template.builders?.config) ?: ''
    }

    private String publishers(project) {
        (project.jenkins.template.publishers?.config) ?: ''
    }

    private String generate(project) {
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
