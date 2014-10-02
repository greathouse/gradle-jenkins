package com.greenmoonsoftware.gradle.jenkins.publishers.junit

class JUnitResultArchiver {
    String testResults
    boolean keepLongStdio = false

    String getConfig() {
        """<hudson.tasks.junit.JUnitResultArchiver>
      <testResults>${testResults}</testResults>
      <keepLongStdio>${keepLongStdio}</keepLongStdio>
      <testDataPublishers/>
    </hudson.tasks.junit.JUnitResultArchiver>"""
    }
}
