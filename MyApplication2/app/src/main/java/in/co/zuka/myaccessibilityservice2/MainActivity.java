package in.co.zuka.myaccessibilityservice2;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "mCus";
    private TextView root_accessibility_tv;
    private Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root_accessibility_tv = findViewById(R.id.root_accessibility_tv);
        btnSettings = findViewById(R.id.button_settings);

        btnSettings.setOnClickListener((view) -> {
            switch (view.getId()) {
                case R.id.button_settings: {
                    Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    startActivity(intent);
                    break;
                }
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (isAccessibilityEnabled()) {
            btnSettings.setVisibility(View.GONE);
            root_accessibility_tv.setText("Accessibility permissions are granted");
        } else {
            btnSettings.setVisibility(View.VISIBLE);
            root_accessibility_tv.setText("Provide accessibility permissions");
        }
    }

    public boolean isAccessibilityEnabled() {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(this.getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            Log.d(MainActivity.TAG,
                    "Error finding setting, default accessibility to not found: " + e.getMessage());
        }

        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled != 1) {
            return false;
        }
        String settingValue = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
        if (settingValue != null) {
            TextUtils.SimpleStringSplitter splitter = mStringColonSplitter;
            splitter.setString(settingValue);
            while (splitter.hasNext()) {
                String accessabilityService = splitter.next();
                if (accessabilityService.equalsIgnoreCase(
                        "in.co.zuka.myaccessibilityservice2/in.co.zuka.myaccessibilityservice2.MyAccessibilityService")) {
                    return true;
                }
            }
        }
        return false;
    }
}
