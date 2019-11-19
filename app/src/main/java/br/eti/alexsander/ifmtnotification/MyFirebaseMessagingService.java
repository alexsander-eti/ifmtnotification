/*

Projeto: IFMT Notification
Classe: MyFireBaseMessagingService
Aluno: Alexsander Chaves da Silva
Orientador: Raphael Aparecido de Melo Barboza
Curso: Tecnologia em Análise e Desenvolvimento de Sistema
Instituição: IFMT Campus Primavera do Leste-MT

 */

package br.eti.alexsander.ifmtnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    //Configuracao da identificacao os canais
    final String CHANNEL1 = "atletismo";
    final String CHANNEL2 = "basquete";
    final String CHANNEL3 = "futebol";
    final String CHANNEL4 = "futsal";
    final String CHANNEL5 = "handball";
    final String CHANNEL6 = "judo";
    final String CHANNEL7 = "natacao";
    final String CHANNEL8 = "tenis_de_mesa";
    final String CHANNEL9 = "volei";
    final String CHANNEL10 = "volei_de_praia";
    final String CHANNEL11 = "xadrez";

    //Cria variavel para manipular banco de dados
    final MyDataBase dbNtf = new MyDataBase(this);

    //Funcao chamada ao receber notificacao do servidor
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //Chama funcao que grava registro no banco de dados
        dbNtf.insertNtf(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        //Armazena lista de registros na variavel cursor
        Cursor cursor = dbNtf.listNtf();
        //Armazena a contagem de registro da lista
        int id = cursor.getCount();

        //Executa se notificacao nao for nula
        if (remoteMessage.getNotification() != null) {
            Intent intent = new Intent(this, Main2Activity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            Uri sound_nft = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            String channelId = remoteMessage.getNotification().getChannelId();

            //Configura notificacao para exibicao
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
            builder.setSmallIcon(R.mipmap.ic_logo);
            builder.setTicker(remoteMessage.getNotification().getTicker());
            builder.setContentTitle(remoteMessage.getNotification().getTitle());
            builder.setContentText(remoteMessage.getNotification().getBody());
            builder.setAutoCancel(true);
            builder.setSound(sound_nft);
            builder.setContentIntent(pendingIntent);

            //Exibe notificacao Android 8 ou superior
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT));
                notificationManager.notify(id, builder.build());
            }

            //Exibe notificacao Android < 8
            else {
                NotificationManager notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notifyManager.notify(id, builder.build());
            }
        }
    }

    //Executa na instalacao do app
    @Override
    public void onNewToken(String token) {
        //Cria canais se Android 8 ou superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL1, getString(R.string.channel1), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL2, getString(R.string.channel2), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL3, getString(R.string.channel3), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL4, getString(R.string.channel4), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL5, getString(R.string.channel5), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL6, getString(R.string.channel6), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL7, getString(R.string.channel7), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL8, getString(R.string.channel8), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL9, getString(R.string.channel9), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL10, getString(R.string.channel10), NotificationManager.IMPORTANCE_DEFAULT));
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL11, getString(R.string.channel11), NotificationManager.IMPORTANCE_DEFAULT));
        }
    }
}