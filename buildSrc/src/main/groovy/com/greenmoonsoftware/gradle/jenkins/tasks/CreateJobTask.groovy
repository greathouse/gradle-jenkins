package com.greenmoonsoftware.gradle.jenkins.tasks

import com.greenmoonsoftware.gradle.jenkins.ConfigGenerator
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class CreateJobTask extends DefaultTask {
    @TaskAction
    def createJob() {
        def url = 'http://localhost:8080'
        //http://localhost:8080/createItem
        def configXml = ConfigGenerator.generate(project)
        String type = "application/xml";
        URL u = new URL("${url}/createItem?name=${project.name}");
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty( "Content-Type", type );
        conn.setRequestProperty( "Content-Length", String.valueOf(configXml.length()));
        OutputStream os = conn.getOutputStream();
        os.write(configXml.getBytes());
        os.flush()
        os.close()
        def responseCode = conn.responseCode

        println "Done: ${responseCode}"
    }
}
