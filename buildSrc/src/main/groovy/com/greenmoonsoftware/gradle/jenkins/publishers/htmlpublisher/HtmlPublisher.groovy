package com.greenmoonsoftware.gradle.jenkins.publishers.htmlpublisher

class HtmlPublisher {
    final name
    final version = 'htmlpublisher@1.3'
    List<ReportTarget> reportTargets = []

    HtmlPublisher(n) {
        name = n
    }

    def target(Closure c) {
        def reportTarget = new ReportTarget()
        reportTarget.with c
        reportTargets << reportTarget
    }

    def reportTargets(Closure c) {
        c()
    }

    private targetsConfig() {
        def config = new StringBuilder('<reportTargets>\n')
        reportTargets.each {
            config << it.config
        }
        config << '\n</reportTargets>'
        config
    }

    String getConfig() {
        """<htmlpublisher.HtmlPublisher plugin="${version}">
      ${targetsConfig()}
    </htmlpublisher.HtmlPublisher>"""
    }
}
