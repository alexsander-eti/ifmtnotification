/*

Projeto: IFMT Notification
Classe: MyDataBase
Aluno: Alexsander Chaves da Silva
Curso: Tecnologia em Análise e Desenvolvimento de Sistema
Instituição: IFMT Campus Primavera do Leste-MT

 */

package br.eti.alexsander.ifmtnotification;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase {

    //Constantes utilizadas na classe
    private static final String DB_NAME = "Notifications.db";
    private static final String DB_TABLE = "Notifications";
    private static final int DB_VERSION = 1;
    private static final String ID_NTF = "_id";
    private static final String TITLE_NTF = "title_ntf";
    private static final String TEXT_NTF = "text_nft";
    private final Context DB_CONTEXT;
    private dbHelper DB_HELPER;
    private SQLiteDatabase LITE_DB;

    //Getters das configuracoes da classe
    public String getIdNtf() { return ID_NTF; }
    public String getTitleNtf() { return TITLE_NTF; }
    public String getTextNtf() { return TEXT_NTF; }

    //Funcao responsaveis pela criacao do banco de dados
    private final class dbHelper extends SQLiteOpenHelper {

        public dbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase dbIN) {
            String sqlCom = "CREATE TABLE " + DB_TABLE + " ( " + ID_NTF + " integer PRIMARY KEY autoincrement , " + TITLE_NTF + " text , " + TEXT_NTF + " text ) ";
            dbIN.execSQL(sqlCom);
        }

        @Override
        public void onUpgrade(SQLiteDatabase dbIN, int oldVersion, int newVersion) {
            dbIN.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(dbIN);
        }
    }

    //Funcao que estabelece conexao com o banco de dados
    public MyDataBase(Context context) {
        this.DB_CONTEXT = context;
    }

    //Funcao que abre o banco de dados (leitura e gravacao)
    public MyDataBase openDB(int typeOpen) throws SQLException {
        DB_HELPER = new dbHelper(DB_CONTEXT);

        if (typeOpen == 1) LITE_DB = DB_HELPER.getWritableDatabase();
        else LITE_DB = DB_HELPER.getReadableDatabase();

        return this;
    }

    //Funcao que insere registro no banco de dados
    public String insertNtf (String titleNtf, String textNtf) {
        ContentValues contentValues;
        long result;

        openDB(1);
        contentValues = new ContentValues();
        contentValues.put(MyDataBase.TITLE_NTF, titleNtf);
        contentValues.put(MyDataBase.TEXT_NTF, textNtf);
        result = LITE_DB.insert(MyDataBase.DB_TABLE, null, contentValues);
        LITE_DB.close();

        if (result == -1) return "Erro ao inserir dados.";
        else return "Dados inseridos";
    }

    //Funcao que retorna lista de registros do banco de dados
    public Cursor listNtf() {
        Cursor cursor;
        String[] columns = { MyDataBase.ID_NTF, MyDataBase.TITLE_NTF, MyDataBase.TEXT_NTF };

        openDB(0);
        cursor = LITE_DB.query(MyDataBase.DB_TABLE, columns, null, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        LITE_DB.close();

        return cursor;
    }

    //Funcao que deleta todos os registros do banco de dados
    public void deleteNtf() {
        openDB(1);
        LITE_DB.delete(MyDataBase.DB_TABLE, null, null);
        LITE_DB.close();
    }
}