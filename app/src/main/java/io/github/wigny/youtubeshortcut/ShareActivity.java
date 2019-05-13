package io.github.wigny.youtubeshortcut;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent receivedIntent = getIntent();
        String receivedAction = receivedIntent.getAction();

        if(receivedAction != null && receivedAction.equals(Intent.ACTION_SEND)) {
            String receivedText = receivedIntent.getStringExtra(Intent.EXTRA_TEXT);
            if (receivedText != null) {
                launchYoutube(getApplicationContext(), receivedText);
            }
            finish();
        }
        super.onCreate(savedInstanceState);
    }

    public static void launchYoutube(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String ytPkg = "com.google.android.apps.youtube.mango";
        if (isAppInstalled(ytPkg, context)) {
            intent.setData(Uri.parse(url));
            intent.setPackage(ytPkg);
        } else
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + ytPkg));

        context.startActivity(intent);
    }

    public static boolean isAppInstalled(String packageName, Context context) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}
