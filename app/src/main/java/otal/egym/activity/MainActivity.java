package otal.egym.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import otal.egym.R;

public class MainActivity extends AppCompatActivity {
    public static final String USER_ID_EXTRA = "otal.egym.id";
    public static final String GENDER_EXTRA = "otal.egym.gender";
    public static final String FULLNAME_EXTRA = "otal.egym.fullname";
    public static final String STREET_EXTRA = "otal.egym.street";
    public static final String LOCATION_EXTRA = "otal.egym.location";
    public static final String LARGE_PIC_EXTRA = "otal.egym.largePic";
    public static final String USERNAME_EXTRA = "otal.egym.username";
    public static final String PHONE_EXTRA = "otal.egym.phone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}