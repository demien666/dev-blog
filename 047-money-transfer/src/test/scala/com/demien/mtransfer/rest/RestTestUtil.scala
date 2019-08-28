package com.demien.mtransfer.rest

import java.io.{BufferedReader, InputStreamReader}
import java.net.{HttpURLConnection, URL}
import java.nio.charset.StandardCharsets

object RestTestUtil {

  class RequestResult(val status: Int, val body: String)


  def sendRequest(method: String, path: String, urlParameters: String): RequestResult = {
    val url = new URL(path)
    val conn = url.openConnection.asInstanceOf[HttpURLConnection]
    conn.setDoOutput(true)
    conn.setInstanceFollowRedirects(false)
    conn.setRequestMethod(method)
    if (urlParameters != null) {
      val postData = urlParameters.getBytes(StandardCharsets.UTF_8)
      val postDataLength = postData.length
      conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
      conn.setRequestProperty("charset", "utf-8")
      conn.setRequestProperty("Content-Length", Integer.toString(postDataLength))
      conn.setUseCaches(false)
      conn.getOutputStream.write(postData)
    }
    val in = new BufferedReader(new InputStreamReader(conn.getInputStream, "UTF-8"))
    val sb = new StringBuilder
    var c = 0

    do {
      c = in.read
      sb.append(c.toChar)
    } while (c > 0)

    in.close()


    val responseBody = sb.toString.replaceAll("[^\\x00-\\x7F]", "")
    val responseCode = conn.getResponseCode
    new RequestResult(responseCode, responseBody)
  }

  def sendRequest(method: String, path: String): RequestResult = sendRequest(method, path, null)

}
