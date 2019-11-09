/*

Projeto: IFMT Notification
Classe: MyFireBaseMessagingService
Aluno: Alexsander Chaves da Silva
Curso: Tecnologia em Análise e Desenvolvimento de Sistema
Instituição: IFMT Campus Primavera do Leste-MT

 */

package br.eti.alexsander.ifmtnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

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
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            Uri sound_nft = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            //Configura notificacao para exibicao
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "001");
            builder.setSmallIcon( R.mipmap.ic_logo);
            builder.setTicker(remoteMessage.getNotification().getTicker());
            builder.setContentTitle(remoteMessage.getNotification().getTitle());
            builder.setContentText(remoteMessage.getNotification().getBody());
            builder.setAutoCancel(true);
            builder.setSound(sound_nft);
            builder.setContentIntent(pendingIntent);

            //Exibe notificacao Android 8 ou superior
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId  = "001";
                String channelName = remoteMessage.getNotification().getChannelId();
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(new NotificationChannel(channelId, channelName,
                        NotificationManager.IMPORTANCE_DEFAULT));
                notificationManager.notify(1, builder.build());
            }
            //Exibe notificacao Android < 8
            else {
                NotificationManager notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notifyManager.notify(id, builder.build());
            }
        }
    }

    //Funcao que emite notificao quando app em segundo plano Android 8 ou superior
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //Configura notificacao para exibicao
        NotificationCompat.Builder ntf_builder = new NotificationCompat.Builder(this, "001");
        ntf_builder.setSmallIcon(R.mipmap.ic_logo);
        ntf_builder.setContentTitle("Mensagem");
        ntf_builder.setContentText(messageBody);
        ntf_builder.setAutoCancel(true);
        ntf_builder.setSound(defaultSoundUri);
        ntf_builder.setContentIntent(pendingIntent);

        //Chama funcao que grava registro no banco de dados
        dbNtf.insertNtf("Mensagem", messageBody);
        //Armazena lista de registros na variavel cursor
        Cursor cursor = dbNtf.listNtf();
        //Armazena a contagem de registro da lista
        int id = cursor.getCount();

        //Exibe notificacao
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, ntf_builder.build());
    }

}