package com.greenmoonsoftware.gradle.jenkins.publishers.htmlpublisher

class ReportTarget {
    final name
    String reportName = ''
    String reportDir = ''
    String reportFiles = ''
    boolean keepAll = false
    boolean allowMissing = false
    String wrapperName = 'htmlpublisher-wrapper.html'

    ReportTarget(n) {
        name = n
    }

    String getConfig() {
        """<htmlpublisher.HtmlPublisherTarget>
          <reportName>${reportName}</reportName>
          <reportDir>${reportDir}</reportDir>
          <reportFiles>${reportFiles}</reportFiles>
          <keepAll>${keepAll}</keepAll>
          <allowMissing>${allowMissing}</allowMissing>
          <wrapperName>${wrapperName}</wrapperName>
        </htmlpublisher.HtmlPublisherTarget>"""
    }
}
