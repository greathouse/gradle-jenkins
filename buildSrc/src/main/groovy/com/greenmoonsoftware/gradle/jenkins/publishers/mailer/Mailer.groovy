package com.greenmoonsoftware.gradle.jenkins.publishers.mailer

class Mailer {
    final name
    final version = 'mailer@1.10'
    String recipients = ''
    boolean dontNotifyEveryUnstableBuild = false
    boolean sendToIndividuals = false

    Mailer(n) {
        name = n
    }

    String getConfig() {
        """<hudson.tasks.Mailer plugin="${version}">
      <recipients>${recipients}</recipients>
      <dontNotifyEveryUnstableBuild>${dontNotifyEveryUnstableBuild}</dontNotifyEveryUnstableBuild>
      <sendToIndividuals>${sendToIndividuals}</sendToIndividuals>
    </hudson.tasks.Mailer>"""
    }
}
