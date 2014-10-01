package com.greenmoonsoftware.gradle.jenkins.tasks

import com.greenmoonsoftware.gradle.jenkins.ConfigGenerator
import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class CreateJobTask extends DefaultTask {
    @TaskAction
    def createJob() {
        def configXml = ConfigGenerator.generate(project)

        checkForPlugins(configXml)

        if (existingJob()) {
            updateJobPost(configXml)
        }
        else {
            createJobPost(configXml)
        }

        println "Done"
    }

    private void checkForPlugins(String configXml) {
        if (missingPlugins(configXml)) {
            println "Some plugins are missing. Please execute the '${project.name}:jenkinsInstallPlugins' task"
            throw new RuntimeException('Missing plugins')
        }
    }

    private missingPlugins(configXml) {
        def full = "${project.jenkins.url}/pluginManager/prevalidateConfig"
        TaskHelper.postData(full, configXml) {
            def missingPlugins = new JsonSlurper().parse(it)
            return missingPlugins
        }
    }

    private existingJob() {
        URL u = new URL("${project.jenkins.url}/job/${project.jenkins.jobName}/config.xml")
        HttpURLConnection huc =  (HttpURLConnection)  u.openConnection()
        huc.setRequestMethod("GET")
        huc.connect()
        def responseCode = huc.responseCode
        responseCode == 200
    }

    private createJobPost(String configXml) {
        TaskHelper.postData("${project.jenkins.url}/createItem?name=${project.jenkins.jobName}", configXml)
    }

    private updateJobPost(String configXml) {
        TaskHelper.postData("${project.jenkins.url}/job/${project.jenkins.jobName}/config.xml", configXml)
    }
}
