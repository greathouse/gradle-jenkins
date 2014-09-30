package com.greenmoonsoftware.gradle.jenkins.tasks

import com.greenmoonsoftware.gradle.jenkins.ConfigGenerator
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class CreateJobTask extends DefaultTask {
    @TaskAction
    def createJob() {
        def url = 'http://localhost:8080'
        def configXml = ConfigGenerator.generate(project)

        if (existingJob(url)) {
            updateJobPost(url, configXml)
        }
        else {
            createJobPost(url, configXml)
        }

        println "Done"
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
        postData("${url}/createItem?name=${project.name}", configXml)
    }

    private updateJobPost(url, String configXml) {
        postData("http://localhost:8080/job/${project.name}/config.xml", configXml)
    }

    private void postData(full, String configXml) {
        URL u = new URL(full)
        HttpURLConnection conn = (HttpURLConnection) u.openConnection()
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/xml")
        conn.setRequestProperty("Content-Length", String.valueOf(configXml.length()))
        OutputStream os = conn.getOutputStream()
        os.write(configXml.getBytes())
        os.flush()
        os.close()
        def responseCode = conn.responseCode
        if (responseCode != 200) {
            throw new RuntimeException("Create job failed with response code: ${responseCode}")
        }
    }
}
