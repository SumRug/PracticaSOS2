package com.example.practicasos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnLlamar, btnWhat, btnSms, btnEmail, btnNine, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        vincular();
    }

    private void vincular() {
        btnLlamar = findViewById(R.id.btnLlamar);
        btnWhat = findViewById(R.id.btnWhat);
        btnSms = findViewById(R.id.btnSms);
        btnSalir = findViewById(R.id.btnSalir);

        btnLlamar.setOnClickListener(this);
        btnWhat.setOnClickListener(this);
        btnSms.setOnClickListener(this);
        btnSalir.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnSalir){
            finish();
        }
        if(view.getId() == R.id.btnSms){
            Intent sms = new Intent(Intent.ACTION_VIEW,Uri.parse("sms:524612323534"));
            SmsManager smsManager=SmsManager.getDefault();
            startActivity(sms);
            smsManager.sendTextMessage("4612323534",null,"Ayudaaa",null,null);
        }

        if(view.getId() == R.id.btnLlamar){
            Intent Llamar = new Intent();
            Llamar.setAction(Intent.ACTION_CALL);
            Llamar.setData(Uri.parse("tel:4613285146"));
            startActivity(Llamar);

        }



        if(view.getId() == R.id.btnWhat){
            String url = "https://wa.me/5214613285146?text="
                    + Uri.encode("¡EMERGENCIA! Necesito ayuda");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.setPackage("com.whatsapp");
            startActivity(intent);
        }
    }

}