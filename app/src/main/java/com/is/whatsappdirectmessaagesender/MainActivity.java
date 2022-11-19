package com.is.whatsappdirectmessaagesender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
//    EditText phone;
//    EditText message;
    TextInputEditText phone,message;
    Button sendbtn;
    String messagestr, phonestr = "";
    LinearLayout ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryCodePicker = findViewById(R.id.countryCode);
        phone = findViewById(R.id.text);
        message = findViewById(R.id.message);
        ly = findViewById(R.id.RootLayout);

        sendbtn = findViewById(R.id.sendbtn);
        if (isNightMode(MainActivity.this)){
            countryCodePicker.setContentColor(Color.BLACK);
            countryCodePicker.setArrowColor(Color.BLACK);
            ly.setBackgroundColor(Color.WHITE);




        }
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                messagestr = message.getText().toString();
                phonestr = phone.getText().toString();


                if (!messagestr.isEmpty() && !phonestr.isEmpty()) {

                    countryCodePicker.registerCarrierNumberEditText(phone);
                    phonestr = countryCodePicker.getFullNumber();

                    if (!(phonestr == null)) {

                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + phonestr +
                                "&text=" + messagestr));
                        startActivity(i);
                        message.setText("");
                        phone.setText("");


                    } else {

                        Toast.makeText(MainActivity.this, "Whatsapp is not installed", Toast.LENGTH_SHORT).show();

                    }


                } else {

                    Toast.makeText(MainActivity.this, "Please fill in the Phone no. and message it can't be empty", Toast.LENGTH_LONG).show();

                }

            }
        });


    }
    public boolean isNightMode(Context context) {
        int nightModeFlags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }


//    private boolean isWhatappInstalled(){
//
//        PackageManager packageManager = getPackageManager();
//        boolean whatsappInstalled;
//
//        try {
//
//            packageManager.getPackageInfo("com.whatsapp",PackageManager.GET_META_DATA);
//            whatsappInstalled = true;
//
//
//        }catch (PackageManager.NameNotFoundException e){
//
//            whatsappInstalled = false;
//
//        }
//
//        return whatsappInstalled;
//
//    }
}
