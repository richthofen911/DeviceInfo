package callofdroidy.net.deviceinfo;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Resources resources;

    TextView tvInfoScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resources = getResources();
        tvInfoScreen = (TextView) findViewById(R.id.tv_info_screen);

        DisplayMetrics displayMetrics = resources.getDisplayMetrics();

        float density = displayMetrics.density;
        int widthInPx = displayMetrics.widthPixels;

        int statusBarHeightInPx = getStatusBarHeight(resources);
        int navBarHeightInPx = getNavigationBarHeight(resources);

        Display display = getWindowManager().getDefaultDisplay();
        Point realSize = new Point();
        display.getRealSize(realSize);
        int fullHeightInPx = realSize.y;

        Point displaySize = new Point();
        display.getSize(displaySize);
        Log.e(TAG, "onCreate: displaySize height: " + displaySize.y);

        int heightDimenPx = fullHeightInPx - statusBarHeightInPx - navBarHeightInPx;

        String info = TextUtils.concat(
                "density:\t\t\t\t" + density + "\n" +
                        "statusBarInPx:\t\t\t" + statusBarHeightInPx + "\n" +
                        "statusBarInDp:\t\t\t" + statusBarHeightInPx / density + "\n" +
                        "navBarInPx:\t\t\t\t\t" + navBarHeightInPx + "\n" +
                        "navBarInDp:\t\t\t\t\t" + navBarHeightInPx / density + "\n" +
                        "widthInPx:\t\t\t\t\t\t\t" + widthInPx + "\n" +
                        "widthInDp:\t\t\t\t\t\t\t" + widthInPx / density + "\n" +
                        "heightFullPx:\t\t\t\t" + realSize.y + "\n" +
                        "heightFullDp:\t\t\t\t" + realSize.y / density + "\n" +
                        "heightDimenPx:\t\t" + heightDimenPx + "　(full-statusBar-navBar)\n" +
                        "heightDimenDp:\t\t" + heightDimenPx / density + "　(full-statusBar-navBar)\n" +
                        "heightMainPx:\t\t\t" + (heightDimenPx - 48 * density) + "　(toolbar height 48dp)\n" +
                        "heightMainDp:\t\t\t" + (heightDimenPx - 48 * density) / density + "　(toolbar height 48dp)\n"

        ).toString();

        tvInfoScreen.setText(info);


    }

    public int getStatusBarHeight(Resources resources) {
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public int getNavigationBarHeight(Resources resources){
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
