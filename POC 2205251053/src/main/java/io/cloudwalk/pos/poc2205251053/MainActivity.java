package io.cloudwalk.pos.poc2205251053;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {
    private static final String
            TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        try {
            PackageManager manager = getPackageManager();
            Intent         intent  = new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);

            Log.d(TAG, "onCreate::intent [" + intent + "]");

            List<ResolveInfo> sniffer =
                    manager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            Toast.makeText(MainActivity.this, sniffer.get(0).loadLabel(manager), Toast.LENGTH_SHORT).show();
        } catch (Exception exception) {
            Log.e(TAG, Log.getStackTraceString(exception));

            Toast.makeText(MainActivity.this, "Exception...\n(check ADB LOGCAT)", Toast.LENGTH_SHORT).show();
        }

        finishAndRemoveTask();
    }

    /*

    2022-05-25: below there is a version which - prior to starting targeted activity - registers
    itself with the SunmiPayKernel (results are the same)...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        SunmiPayKernel.getInstance().initPaySDK(this, new SunmiPayKernel.ConnectCallback() {
            @Override
            public void onConnectPaySDK() {
                Log.d(TAG, "onConnectPaySDK");

                try {
                    SunmiPayKernel.getInstance() // 2022-05-25: just in case, but it shouldn't be
                            .mBasicOptV2         // needed for this
                            .allowDynamicPermission(getPackageName());

                    PackageManager manager = getPackageManager();
                    Intent         intent  = new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);

                    Log.d(TAG, "onCreate::intent [" + intent + "]");

                    List<ResolveInfo> sniffer =
                            manager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                    Toast.makeText(MainActivity.this, sniffer.get(0).loadLabel(manager), Toast.LENGTH_SHORT).show();
                } catch (Exception exception) {
                    Log.e(TAG, Log.getStackTraceString(exception));

                    Toast.makeText(MainActivity.this, "Exception...\n(check ADB LOGCAT)", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onDisconnectPaySDK() {
                Log.d(TAG, "onDisconnectPaySDK");
            }
        });

        finishAndRemoveTask();
    }
     */
}
