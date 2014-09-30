package com.greenmoonsoftware.gradle.jenkins

import com.greenmoonsoftware.gradle.jenkins.tasks.CreateJobTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class Jenkins implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.configure(project) {
            extensions.create("jenkins", JenkinsProjectExtension)
        }

        project.task('jenkins-create', type: CreateJobTask)

        project.task('jenkins') << {
            writeFile(project)
        }
    }

    private void writeFile(project) {
        def outputDir = "${project.buildDir}/jenkins"
        new File(outputDir).mkdirs()
        def outputFilename = "${outputDir}/config.xml"
        def xml = ConfigGenerator.generate(project)
        if (project.jenkins.console) {
            println xml
        }
        new File(outputFilename).withWriter { w ->
            w.write(xml)
        }
        project.logger.lifecycle "Jenkins config.xml written to ${outputFilename}"
    }
}
