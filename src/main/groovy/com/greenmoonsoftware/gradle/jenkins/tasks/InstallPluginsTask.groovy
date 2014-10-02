package com.greenmoonsoftware.gradle.jenkins.tasks

import com.greenmoonsoftware.gradle.jenkins.ConfigGenerator
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class InstallPluginsTask extends DefaultTask {
    @TaskAction
    def installPlugins() {
        def xml = ConfigGenerator.generate(project)
        TaskHelper.postData("${project.jenkins.url}/pluginManager/installNecessaryPlugins", xml)
    }
}
