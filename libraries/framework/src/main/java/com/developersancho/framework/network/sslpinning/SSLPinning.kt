package com.developersancho.framework.network.sslpinning

import android.content.Context
import androidx.annotation.RawRes
import java.security.cert.Certificate
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

interface SSLPinning {
    fun initSSLWithCertificateType(
        context: Context,
        @RawRes rawResourceId: Int,
        password: String,
        typeOfCertificate: String
    )

    fun initSSLWithCertificate(context: Context, @RawRes rawResourceId: Int)

    fun getTrustManager(): X509TrustManager

    fun getCertificate(): Certificate?

    fun getSSLSocketFactory(): SSLSocketFactory
}