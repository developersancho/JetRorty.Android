package com.developersancho.framework.network.sslpinning

import android.annotation.SuppressLint
import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.security.*
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.*

class SSLPinningImpl : SSLPinning {
    private lateinit var sslSocketFactory: SSLSocketFactory
    private lateinit var trustManagers: Array<TrustManager>

    private var certificate: Certificate? = null

    override fun initSSLWithCertificateType(
        context: Context,
        rawResourceId: Int,
        password: String,
        typeOfCertificate: String
    ) {
        val inputStream = generateInputStream(context, rawResourceId)
        val keyStore = generateKeystore(inputStream, password, typeOfCertificate)
        val keyManagers = generateKeyManagers(keyStore, password)
        val trustManagers = generateTrustManager()
        generateSSLSocketFactory(keyManagers, trustManagers)
    }

    override fun initSSLWithCertificate(context: Context, rawResourceId: Int) {
        val inputStream = generateInputStream(context, rawResourceId)
        val keyStore = generateKeystore(inputStream)
        val keyManagers = generateKeyManagers(keyStore, "")
        val trustManagers = generateTrustManager(keyStore)
        generateSSLSocketFactory(keyManagers, trustManagers)
    }

    override fun getTrustManager(): X509TrustManager {
        return trustManagers[0] as X509TrustManager
    }

    override fun getCertificate(): Certificate? {
        return certificate
    }

    override fun getSSLSocketFactory(): SSLSocketFactory {
        return sslSocketFactory
    }

    private fun generateInputStream(context: Context, rawResourcePath: Int): InputStream {
        return context.resources.openRawResource(rawResourcePath)
    }

    private fun generateCertificate(inputStream: InputStream): Certificate? {
        try {
            val certificateFactory = CertificateFactory.getInstance("X.509")
            return certificateFactory.generateCertificate(inputStream)
        } catch (e: CertificateException) {
            e.printStackTrace()
        }

        return null
    }

    private fun generateKeystore(inputStream: InputStream): KeyStore? {
        val keyStore: KeyStore
        try {
            keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            keyStore.load(null, null)
            this.certificate = generateCertificate(inputStream)
            keyStore.setCertificateEntry("ca", certificate)
            return keyStore
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: CertificateException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return null
    }

    private fun generateKeystore(
        inputStream: InputStream,
        password: String,
        typeOfCertificate: String
    ): KeyStore? {
        val keyStore: KeyStore
        try {
            keyStore = KeyStore.getInstance(typeOfCertificate)
            keyStore.load(inputStream, password.toCharArray())
            return keyStore
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: CertificateException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return null
    }


    private fun generateKeyManagers(keyStore: KeyStore?, password: String): Array<KeyManager>? {
        val keyManagerFactory: KeyManagerFactory
        try {
            keyManagerFactory = KeyManagerFactory.getInstance("X509")
            keyManagerFactory.init(keyStore, password.toCharArray())
            return keyManagerFactory.keyManagers
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnrecoverableKeyException) {
            e.printStackTrace()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        }

        return null
    }

    private fun generateTrustManager(keyStore: KeyStore?): Array<TrustManager>? {
        val trustManagerFactory: TrustManagerFactory
        try {
            val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
            trustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm)
            trustManagerFactory.init(keyStore)
            this.trustManagers = trustManagerFactory.trustManagers
            return trustManagers
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        }

        return null
    }

    @SuppressLint("TrustAllX509TrustManager")
    private fun generateTrustManager(): Array<TrustManager> {
        this.trustManagers = arrayOf(@SuppressLint("CustomX509TrustManager")
        object : X509TrustManager {

            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
            }

            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {

                return arrayOf()
            }

        })
        return trustManagers
    }


    private fun generateSSLSocketFactory(
        keyManagers: Array<KeyManager>?,
        trustManagers: Array<TrustManager>?
    ): SSLSocketFactory? {
        val sslSocketFactory: SSLSocketFactory
        try {
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(keyManagers, trustManagers, SecureRandom())
            sslSocketFactory = sslContext.socketFactory
            this.sslSocketFactory = sslSocketFactory
            return sslSocketFactory
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return null
    }
}


//fun provideSSLPining(application: SampleApplication): SSLPinning {
//
//    val sslPinning: SSLPinning = SSLPinningImpl()
//    // init certificate
//    sslPinning.initSSLWithCertificate(application.applicationContext, R.raw.blabla)
//    return sslPinning
//}


//fun provideClient(sslPinning: SSLPinning): OkHttpClient {
//
//    val pin = CertificatePinner.pin(sslPinning.getCertificate())
//
//    val certificatePinner = CertificatePinner.Builder()
//        .add("*.example.net", pin)
//        .build()
//
//    return OkHttpClient.Builder()
//        .certificatePinner(certificatePinner)
//        .build()
//}


//fun provideSSLPining(application: SampleApplication): SSLPinning {
//
//    val sslPinning: SSLPinning = SSLPinningImpl()
//    // init certificate
//
//    sslPinning.initSSLWithertificateType(application.applicationContext, R.raw.bksbks,"password","BKS")
//
//    return sslPinning
//}
//


//fun provideClient(sslPinning: SSLPinning): OkHttpClient {
//
//    return OkHttpClient.Builder()
//        .sslSocketFactory(sslPinning.getSSLSocketFactory(),sslPinning.getTrustManager())
//        .build()
//}