package com.greenmoonsoftware.gradle.jenkins.tasks

import com.greenmoonsoftware.gradle.jenkins.ConfigGenerator
import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class CreateJobTask extends DefaultTask {
    @TaskAction
    def createJob() {
        def url = 'http://localhost:8080'
        def configXml = ConfigGenerator.generate(project)

        if (missingPlugins(url, configXml)) {
           println "Some plugins are missing. Please execute the '${project.name}:jenkinsInstallPlugins' task"
            throw new RuntimeException('Missing plugins')
        }

        if (existingJob(url)) {
            updateJobPost(url, configXml)
        }
        else {
            createJobPost(url, configXml)
        }

        println "Done"
    }

    private missingPlugins(url, configXml) {
        def full = "${url}/pluginManager/prevalidateConfig"
        TaskHelper.postData(full, configXml) {
            def missingPlugins = new JsonSlurper().parse(it)
            return missingPlugins
        }
    }

    private existingJob(url) {
        URL u = new URL("${url}/job/${project.name}/config.xml")
        HttpURLConnection huc =  (HttpURLConnection)  u.openConnection()
        huc.setRequestMethod("GET")
        huc.connect()
        def responseCode = huc.responseCode
        responseCode == 200
    }

    private createJobPost(url, String configXml) {
        TaskHelper.postData("${url}/createItem?name=${project.name}", configXml)
    }

    private updateJobPost(url, String configXml) {
        TaskHelper.postData("http://localhost:8080/job/${project.name}/config.xml", configXml)
    }
}
