package com.example.aithinker;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;

import org.opencv.android.OpenCVLoader;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class App extends Application {

    private static Net net;
    static String ProtoString;
    static String CaffeModelString;
    private static App instance;


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        SharedPreferences settings = getSharedPreferences("PreferencesName", MODE_PRIVATE);

        ProtoString = settings.getString("ProtoString","MobileNetSSD_deploy.prototxt");

        CaffeModelString = settings.getString("CaffeModelString","MobileNetSSD_deploy.caffemodel");

    }

    public static String getProtoString(Context context) {

        AssetManager assetManager = context.getAssets();
        BufferedInputStream inputStream;
        try {
// Read data from assets.
            inputStream = new BufferedInputStream(assetManager.open(ProtoString));
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
// Create copy file in storage.
            File outFile = new File(context.getFilesDir(), ProtoString);
            FileOutputStream os = new FileOutputStream(outFile);
            os.write(data);
            os.close();
// Return a path to file which may be read in common way.
            return outFile.getAbsolutePath();
        } catch (IOException ex) {
            Log.i("TAG", "Failed to upload a file");
        }
        return "";
    }


    public static String getCaffeModelString(Context context) {
        AssetManager assetManager = context.getAssets();
        BufferedInputStream inputStream;
        try {
// Read data from assets.
            inputStream = new BufferedInputStream(assetManager.open(CaffeModelString));
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
// Create copy file in storage.
            File outFile = new File(context.getFilesDir(), CaffeModelString);
            FileOutputStream os = new FileOutputStream(outFile);
            os.write(data);
            os.close();
// Return a path to file which may be read in common way.
            return outFile.getAbsolutePath();
        } catch (IOException ex) {
            Log.i("TAG", "Failed to upload a file");
        }
        return "";
    }

}
