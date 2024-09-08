package com.carbelim.dashboard;

import android.os.Build;
import android.webkit.WebView;
import com.getcapacitor.BridgeActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends BridgeActivity {

    private static final int STORAGE_PERMISSION_CODE = 101;

    @Override
    public void onStart() {
        super.onStart();

        // Disable the overscroll effect
        WebView webView = (WebView) findViewById(R.id.webview);
        if (webView != null) {
            webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        }

        // Request permission for storage if API level is 23 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission(Manifest.permission.READ_MEDIA_IMAGES, STORAGE_PERMISSION_CODE);
        }
    }

    // Method to check if the permission is granted, and request if it's not
    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { permission }, requestCode);
        }
    }

    // Handle the result of the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
            } else {
                // Permission denied
            }
        }
    }
}
