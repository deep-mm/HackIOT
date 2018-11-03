package dupd.com.smartbag.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedData {

    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    public Gson gson;
    public Context context;

    public SharedData(Context c) {
        this.context = c;
        pref = c.getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        gson = new Gson();
    }
}
