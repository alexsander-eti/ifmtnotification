/*

Projeto: IFMT Notification
Classe: Main2Activity
Aluno: Alexsander Chaves da Silva
Orientador: Raphael Aparecido de Melo Barboza
Curso: Tecnologia em Análise e Desenvolvimento de Sistema
Instituição: IFMT Campus Primavera do Leste-MT

*/

package br.eti.alexsander.ifmtnotification;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Carrega banco de dados e lista de notificacoes
        final MyDataBase dbNtf = new MyDataBase(this);
        Cursor cursor = dbNtf.listNtf();

        //Organiza e monta notificacoes na ListView
        String[] nomeCampos = new String[] { dbNtf.getTitleNtf(), dbNtf.getTextNtf() };
        int[] idViews = new int[] { R.id.tv_list_title, R.id.tv_list_text };
        ListView listView = (ListView) findViewById(R.id.lv_ntf);
        SimpleCursorAdapter simpleCA = new SimpleCursorAdapter(this, R.layout.model_list, cursor, nomeCampos, idViews, 0);
        listView.setAdapter(simpleCA);
    }

    //Carrega o menu na tela
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Lista de funcoes do menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final MyDataBase dbNtf = new MyDataBase(this);
        switch (item.getItemId()) {
            case R.id.action_delete:
                dbNtf.deleteNtf();
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                break;
            /*Opcao do menu desativada
            case R.id.action_setting:
                startActivityForResult(new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS), 0);
                break;*/
            default:
                //Nao faz nada
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}