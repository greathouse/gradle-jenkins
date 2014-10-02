package com.greenmoonsoftware.gradle.jenkins.properties.pipeline

import groovy.xml.XmlUtil

class Pipeline {
    final version = 'delivery-pipeline-plugin@0.8.2'
    String taskName
    String stageName

    String getConfig() {
        """<se.diabol.jenkins.pipeline.PipelineProperty plugin="${version}">
      <taskName>${XmlUtil.escapeXml(taskName)}</taskName>
      <stageName>${XmlUtil.escapeXml(stageName)}</stageName>
    </se.diabol.jenkins.pipeline.PipelineProperty>"""
    }
}
