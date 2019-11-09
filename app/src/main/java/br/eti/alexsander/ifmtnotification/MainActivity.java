/*

Projeto: IFMT Notification
Classe: MainActivity
Aluno: Alexsander Chaves da Silva
Curso: Tecnologia em Análise e Desenvolvimento de Sistema
Instituição: IFMT Campus Primavera do Leste-MT

 */

package br.eti.alexsander.ifmtnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Configuracao dos endereços externos
    private final String AD_ATHLETICS = "https://docs.google.com/spreadsheets/d/1r4LIWd4CUGVBglizBKrX8R3IvqjmSYwUtAh5pGe6kJU/edit#gid=0";
    private final String AD_BASKETBALL = "https://docs.google.com/spreadsheets/d/1r4LIWd4CUGVBglizBKrX8R3IvqjmSYwUtAh5pGe6kJU/edit#gid=0";
    private final String AD_SOCCER = "https://docs.google.com/spreadsheets/d/1Sbonq5aoa4miG7Uc6_FLiNYQ9R9WbgcpGzGbKaYSbpQ/edit#gid=504261037";
    private final String AD_FUTSAL ="https://docs.google.com/spreadsheets/d/1Y65R7mgNoTrX7hIoMtJITwip7rnPIq-ZN41uAVCib8A/edit#gid=553360524";
    private final String AD_HANDBALL = "https://docs.google.com/spreadsheets/d/1TvS7iOkRUSvUhC1pqucSiLvUOz7MmLWTFDRHVCw0IEM/edit#gid=249954660";
    private final String AD_JUDO = "https://docs.google.com/spreadsheets/d/1-nQjZSL7aq-m7fSAjebbuTmihbJCP2T1ebmXH60DGag/edit#gid=0";
    private final String AD_SWIMMING = "https://docs.google.com/spreadsheets/d/10I5whTBhCVRgt_SSDjGkqMWHR5CJN4GLuYlIEr_oHXk/edit#gid=0";
    private final String AD_TABLE_TENNIS = "https://docs.google.com/spreadsheets/d/1G_6jBVQzy24i3wUQTPWgW1NO6ZJROqCKbz3JjtMj2S4/edit#gid=786902418";
    private final String AD_VOLLEYBALL = "https://docs.google.com/spreadsheets/d/1c3IuQO76Kwv9P0ZxqYq9sHS3d_mBh2bQ42E3h5r3sPg/edit#gid=587481080";
    private final String AD_BEACH_VOLLEYBALL = "https://docs.google.com/spreadsheets/d/19d-4WEWrknBfJse6G8q_Dx9798nDMWKVq2C24p6gaVc/edit#gid=1107181858";
    private final String AD_CHESS = "https://docs.google.com/spreadsheets/d/1EWgJMK-4ruMJc-w1SkL25R8_7L9FqUI6mRLerRyh61o/edit#gid=2018614920";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Chamada de funcao que carrega os botoes
        loadButtons();
        //chamada de funcao que cria os canais de notificacao
        createChannels();

        //Configuracao de botao flutuante que abre segunda tela
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
            }
        });
    }

    //Funcao que carrega e configura botoes na tela
    public void loadButtons () {
        //Lista de botoes da tela principal
        Button btAthletics = (Button) findViewById(R.id.bt_athletics);
        Button btBasketball = (Button) findViewById(R.id.bt_basketball);
        Button btSoccer = (Button) findViewById(R.id.bt_soccer);
        Button btFutsal = (Button) findViewById(R.id.bt_futsal);
        Button btHandball = (Button) findViewById(R.id.bt_handball);
        Button btJudo = (Button) findViewById(R.id.bt_judo);
        Button btSwimming = (Button) findViewById(R.id.bt_swimming);
        Button btTableTennis = (Button) findViewById(R.id.bt_table_tennis);
        Button btVolleyball = (Button) findViewById(R.id.bt_volleyball);
        Button btBeachVolleyball = (Button) findViewById(R.id.bt_beach_volleyball);
        Button btChess = (Button) findViewById(R.id.bt_chess);

        //Lista de funcoes executadas ao clicar nos botoes
        btAthletics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AD_ATHLETICS));
                startActivity(intent);
            }
        });

        btBasketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AD_BASKETBALL));
                startActivity(intent);
            }
        });

        btSoccer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AD_SOCCER));
                startActivity(intent);
            }
        });

        btFutsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AD_FUTSAL));
                startActivity(intent);
            }
        });

        btHandball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AD_HANDBALL));
                startActivity(intent);
            }
        });

        btJudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AD_JUDO));
                startActivity(intent);
            }
        });

        btSwimming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AD_SWIMMING));
                startActivity(intent);
            }
        });

        btTableTennis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AD_TABLE_TENNIS));
                startActivity(intent);
            }
        });

        btVolleyball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AD_VOLLEYBALL));
                startActivity(intent);
            }
        });

        btBeachVolleyball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AD_BEACH_VOLLEYBALL));
                startActivity(intent);
            }
        });

        btChess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AD_CHESS));
                startActivity(intent);
            }
        });
    }

    //Funcao que cria canais de notificacao Android 8 e superior
    public void createChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel("1", getString(R.string.channel1), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel("2", getString(R.string.channel2), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel("3", getString(R.string.channel3), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel("4", getString(R.string.channel4), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel("5", getString(R.string.channel5), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel("6", getString(R.string.channel6), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel("7", getString(R.string.channel7), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel("8", getString(R.string.channel8), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel("9", getString(R.string.channel9), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel("10", getString(R.string.channel10), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel("11", getString(R.string.channel11), NotificationManager.IMPORTANCE_DEFAULT));
        }
    }
}