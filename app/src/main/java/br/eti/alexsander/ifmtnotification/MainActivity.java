/*

Projeto: IFMT Notification
Classe: MainActivity
Aluno: Alexsander Chaves da Silva
Orientador: Raphael Aparecido de Melo Barboza
Curso: Tecnologia em Análise e Desenvolvimento de Sistema
Instituição: IFMT Campus Primavera do Leste-MT

*/

package br.eti.alexsander.ifmtnotification;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

    //Configuracao do nome dos dados enviados na notificacao
    private final String DATA_TITLE = "titulo";
    private final String DATA_TEXT = "texto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Comandos executados se app aberto atraves da notificacao
        Intent intent2 = getIntent();
        Bundle bundle = intent2.getExtras();
        if (bundle != null) {
            if (bundle.getString(DATA_TITLE) != null) {

                final MyDataBase dbNtf = new MyDataBase(this);
                dbNtf.insertNtf(bundle.getString(DATA_TITLE), bundle.getString(DATA_TEXT));
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        }


        //Chamada de funcao que carrega os botoes para links externos
        loadButtons();

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
}