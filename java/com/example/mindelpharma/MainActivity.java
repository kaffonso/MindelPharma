package com.example.mindelpharma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.time.LocalDate;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView tv_all, tv_qr, tv_service, tv_close, tv_nameService, tv_addressService, tv_nameClose, tv_addressClose;
    ImageButton ib_all,  ib_qr;
    ImageView iv_service, iv_close;
    SliderView sliderView;

    Calendar c = Calendar.getInstance();

    int [] images = {R.drawable.fotohigiene, R.drawable.fotojovem, R.drawable.fotoleao, R.drawable.fotomindelo,
            R.drawable.fotonena, R.drawable.fotohigiene};

    public LocationManager lm;
    public LocationListener ll;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textviews
        tv_all = findViewById(R.id.tv_all);
        tv_qr = findViewById(R.id.tv_qr);
        tv_service = findViewById(R.id.tv_service);
        tv_close = findViewById(R.id.tv_close);
        tv_nameService = findViewById(R.id.tv_nameService);
        tv_addressService = findViewById(R.id.tv_addressService);
        tv_nameClose = findViewById(R.id.tv_nameClose);
        tv_addressClose = findViewById(R.id.tv_addressClose);

        //sliderview
        sliderView = findViewById(R.id.image_Slider);
        SliderAdapter sliderAdapter = new SliderAdapter(images);

        //sliderview settings
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP    );
        sliderView.setSliderAnimationDuration(4000);
        sliderView.startAutoCycle();

        //buttons
        ib_all = findViewById(R.id.ib_all);
        ib_qr = findViewById(R.id.ib_qr);

        //imageviews
        iv_service = findViewById(R.id.iv_service);
        iv_close = findViewById(R.id.iv_close);

        ib_qr.setOnClickListener(view -> {

            IntentIntegrator intentIntegrator = new IntentIntegrator(
                    MainActivity.this
            );

            intentIntegrator.setPrompt("Pressione volume up para ativar o flash");
            intentIntegrator.setBeepEnabled(false);
            intentIntegrator.setOrientationLocked(true);
            intentIntegrator.setCaptureActivity(Capture.class);
            intentIntegrator.initiateScan();
        });

        lm = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        ll = new nhaLocationListener();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);
        }

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,ll);

        service();
    }

    private class nhaLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location location) {

            if (location.getLatitude() >= 16.87975 && location.getLatitude() <= 16.87985 && location.getLongitude() == -24.98588){
                tv_nameClose.setText("Farmácia Avenida");
                tv_addressClose.setText("Monte Sossego - Avenida de Holanda");
                iv_close.setImageResource(R.mipmap.avenida);

            } else if (location.getLatitude() >= 16.88340 && location.getLatitude() <= 16.88350 && location.getLongitude() == -24.98543) {
                tv_nameClose.setText("Farmácia Jovem");
                tv_addressClose.setText("Avenida 12 de Setembro");
                iv_close.setImageResource(R.mipmap.jovem);
                return;

            } else if (location.getLatitude() >= 16.88632 && location.getLatitude() <= 16.88642 && location.getLongitude() == -24.98763) {
                tv_nameClose.setText("Farmácia Néna Lda.");
                tv_addressClose.setText("Rua António A. Gonçalves");
                iv_close.setImageResource(R.mipmap.nena);
                return;

            } else if (location.getLatitude() >= 16.88639 && location.getLatitude() <= 16.88649 && location.getLongitude() == -24.98746) {
                tv_nameClose.setText("Farmácia Higiene Lda.");
                tv_addressClose.setText("Rua Libertadores África");
                iv_close.setImageResource(R.mipmap.higiene);
                return;

            } else if (location.getLatitude() >= 16.88680 && location.getLatitude() <= 16.88690 && location.getLongitude() == -24.98828) {
                tv_nameClose.setText("Farmácia do Leão");
                tv_addressClose.setText("Rua António A. Gonçalves");
                iv_close.setImageResource(R.mipmap.leao);
                return;

            } else if (location.getLatitude() >= 16.89051 && location.getLatitude() <= 16.89061 && location.getLongitude() == -24.98645) {
                tv_nameClose.setText("Farmácia Mindelo");
                tv_addressClose.setText("Avenida Baltazar Lopes");
                iv_close.setImageResource(R.mipmap.mindelo);
                return;

            } else {
                tv_nameClose.setText("Nenhuma Farmácia Perto");
                tv_addressClose.setText("Sem Dados");
                iv_close.setImageResource(R.drawable.shapewhite);
                return;
            }
        }

        @Override
        public void onFlushComplete(int requestCode) {

        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
            tv_nameClose.setText("Nenhuma Farmácia Perto");
            tv_addressClose.setText("Sem Dados");
            iv_close.setImageResource(R.drawable.shapewhite);
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data ) {

        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(intentResult.getContents() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Resultado");
            builder.setMessage(intentResult.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();

        }else {
            Toast.makeText(getApplicationContext(),"Nada foi escaneado",Toast.LENGTH_SHORT).show();
        }
    }

    public void gotoAll(View view) {
        Intent page = new Intent(this,All.class);
        startActivity(page);
    }

    public void service(){
        if ((c.get(Calendar.DAY_OF_WEEK)) == 1){
            tv_nameService.setText("Farmácia Jovem");
            tv_addressService.setText("Avenida 12 de Setembro");
            iv_service.setImageResource(R.mipmap.jovem);

        } else if ((c.get(Calendar.DAY_OF_WEEK)) == 2){
            tv_nameService.setText("Farmácia Mindelo");
            tv_addressService.setText("Avenida Baltazar Lopes");
            iv_service.setImageResource(R.mipmap.mindelo);

        } else if ((c.get(Calendar.DAY_OF_WEEK)) == 3){
            tv_nameService.setText("Farmácia Avenida");
            tv_addressService.setText("Monte Sossego - Avenida de Holanda");
            iv_service.setImageResource(R.mipmap.avenida);

        } else if ((c.get(Calendar.DAY_OF_WEEK)) == 4){
            tv_nameService.setText("Farmácia Leão");
            tv_addressService.setText("Rua António A. Gonçalves");
            iv_service.setImageResource(R.mipmap.leao);

        } else if ((c.get(Calendar.DAY_OF_WEEK)) == 5){
            tv_nameService.setText("Farmácia Néna");
            tv_addressService.setText("Rua António A. Gonçalves");
            iv_service.setImageResource(R.mipmap.nena);

        } else if ((c.get(Calendar.DAY_OF_WEEK)) == 6){
            tv_nameService.setText("Farmácia Higiene Lda.");
            tv_addressService.setText("Rua Libertadores África");
            iv_service.setImageResource(R.mipmap.higiene);

        } else if ((c.get(Calendar.DAY_OF_WEEK)) == 7){
            tv_nameService.setText("Farmácia Jovem");
            tv_addressService.setText("Monte Sossego - Avenida de Holanda");
            iv_service.setImageResource(R.mipmap.jovem);
        }
    }
}



