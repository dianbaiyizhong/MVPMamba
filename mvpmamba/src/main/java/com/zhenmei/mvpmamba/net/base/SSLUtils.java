package com.zhenmei.mvpmamba.net.base;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.zhenmei.mvpmamba.app.ConfigKeys;
import com.zhenmei.mvpmamba.app.ManBaNetBuilder;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class SSLUtils {
    private final static String TAG = "mamba";

    private static KeyStore getKeyStore(String fileName) {
        KeyStore keyStore = null;
        try {

            AssetManager assetManager = ((Context) ManBaNetBuilder.getConfiguration(ConfigKeys.APPLICATION_CONTEXT)).getAssets();
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream caInput = assetManager.open(fileName);
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
                Log.d(TAG, "ca=" + ((X509Certificate) ca).getSubjectDN());
            } finally {
                caInput.close();
            }

            String keyStoreType = KeyStore.getDefaultType();
            keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);
        } catch (Exception e) {
            Log.d(TAG, "Error during getting keystore");

        }
        return keyStore;
    }


    public static SSLContext getSslContextForCertificateFile(String fileName) {
        try {
            KeyStore keyStore = getKeyStore(fileName);
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext;
        } catch (Exception e) {
            String msg = "Error during creating SslContext for certificate from assets";
            Log.d(TAG, msg);
            throw new RuntimeException(msg);
        }
    }
}
