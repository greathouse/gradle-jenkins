package com.greenmoonsoftware.gradle.jenkins.tasks

class TaskHelper {
    public static void postData(String url, String configXml) {
        URL u = new URL(url)
        HttpURLConnection conn = (HttpURLConnection) u.openConnection()
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/xml")
        conn.setRequestProperty("Content-Length", String.valueOf(configXml.length()))
        OutputStream os = conn.outputStream
        os.write(configXml.bytes)
        os.flush()
        os.close()
        def responseCode = conn.responseCode
        println "Response Code: ${responseCode}"
        if (responseCode != 200) {
            throw new RuntimeException("POST ${url} failed: Response Code: ${responseCode}")
        }
    }
}
