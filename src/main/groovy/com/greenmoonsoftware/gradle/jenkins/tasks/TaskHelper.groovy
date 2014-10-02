package com.greenmoonsoftware.gradle.jenkins.tasks

class TaskHelper {
    public static postData(String url, String configXml, Closure withData = {}) {
        def u = new URL(url)
        def conn = (HttpURLConnection) u.openConnection()
        conn.doOutput = true
        conn.requestMethod = 'POST'
        conn.setRequestProperty("Content-Type", "application/xml")
        conn.setRequestProperty("Content-Length", String.valueOf(configXml.length()))
        def os = conn.outputStream
        os.write(configXml.bytes)
        os.flush()
        os.close()
        def responseCode = conn.responseCode
        if (responseCode != 200) {
            throw new RuntimeException("POST ${url} failed: Response Code: ${responseCode}")
        }
        return withData(conn.inputStream)
    }
}
