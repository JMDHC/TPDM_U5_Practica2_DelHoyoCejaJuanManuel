package mx.edu.ittepic.tpdm_u5_practica2_delhoyocejajuanmanuel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    ConexionBase base;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        base=new ConexionBase(context, "Base1", null, 1);
        insertar_datos();
        Bundle extras=intent.getExtras();
        Object[] pdus = (Object[]) extras.get("pdus");
        SmsMessage mensaje=SmsMessage.createFromPdu((byte[])pdus[0]);
        //Toast.makeText(context, "Telefono origen: "+mensaje.getOriginatingAddress() + "Contenido: "+mensaje.getMessageBody(),Toast.LENGTH_LONG).show();
        if(mensaje.getMessageBody().startsWith("COLONIA")){
            if(mensaje.getMessageBody().split("-").length==2){
                String m =mensaje.getMessageBody().split("-")[1];
                Toast.makeText(context, "Colonia para CP "+m+": "+buscarPorCP(m),Toast.LENGTH_LONG).show();
                enviarSMS(mensaje.getOriginatingAddress(),"Colonia para CP "+m+": "+buscarPorCP(m),context);
            }
        }else if(mensaje.getMessageBody().startsWith("CP")){
            if(mensaje.getMessageBody().split("-").length==2){
                String m =mensaje.getMessageBody().split("-")[1];
                Toast.makeText(context, "CP para colonia "+m+": "+ buscarPorColonia(m),Toast.LENGTH_LONG).show();
                enviarSMS(mensaje.getOriginatingAddress(),"CP para colonia "+m+": "+buscarPorColonia(m),context);
            }
        } else{
            Toast.makeText(context, "El mensaje enviado no coincide con las caracteristicas solicitadas",Toast.LENGTH_LONG).show();
            enviarSMS(mensaje.getOriginatingAddress(),"El mensaje enviado no coincide con las caracteristicas solicitadas",context);
        }
    }

    public String buscarPorCP(String cp){
        try{
            SQLiteDatabase base = this.base.getReadableDatabase();
            String[] claves = {cp};
            Cursor c = base.rawQuery("SELECT * FROM Colonias WHERE CP = ?",claves);
            System.out.println(c.getCount());
            if(c.moveToFirst()){
                return(c.getString(1));
            } else {
                return("No se encontraron coincidencias");
            }
        } catch (SQLiteException e){
            return (e.getMessage());
        }
    }

    public String buscarPorColonia(String col){
        try{
            SQLiteDatabase base = this.base.getReadableDatabase();
            String[] claves = {col};
            Cursor c = base.rawQuery("SELECT * FROM Colonias WHERE Colonia = ?",claves);
            if(c.moveToFirst()){
                return(c.getString(0));
            } else {
                return("No se encontraron coincidencias");
            }
        } catch (SQLiteException e){
            return (e.getMessage());
        }
    }

    private void enviarSMS(String t, String m, Context c) {
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(t,null,m,null,null);
        }catch (Exception e){
            Toast.makeText(c, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    void insertar_datos(){
        SQLiteDatabase db = this.base.getWritableDatabase();
        db.execSQL("INSERT INTO Colonias VALUES('63170','12 de Diciembre')");
        db.execSQL("INSERT INTO Colonias VALUES('63190','15 de Mayo')");
        db.execSQL("INSERT INTO Colonias VALUES('63177','18 de Agosto')");
        db.execSQL("INSERT INTO Colonias VALUES('63500','18 de Marzo')");
        db.execSQL("INSERT INTO Colonias VALUES('63175','2 de Agosto')");
        db.execSQL("INSERT INTO Colonias VALUES('63100','20 de Noviembre')");
        db.execSQL("INSERT INTO Colonias VALUES('63081','Acayapan')");
        db.execSQL("INSERT INTO Colonias VALUES('63021','Adolfo López Mateos')");
        db.execSQL("INSERT INTO Colonias VALUES('63062','Alaska')");
        db.execSQL("INSERT INTO Colonias VALUES('63010','Amado Nervo')");
        db.execSQL("INSERT INTO Colonias VALUES('63173','Aramara')");
        db.execSQL("INSERT INTO Colonias VALUES('63129','Arboledas')");
        db.execSQL("INSERT INTO Colonias VALUES('63129','Aves del Paraíso')");
        db.execSQL("INSERT INTO Colonias VALUES('63190','Aviación')");
        db.execSQL("INSERT INTO Colonias VALUES('63501','Bellavista')");
        db.execSQL("INSERT INTO Colonias VALUES('63194','Bonaterra')");
        db.execSQL("INSERT INTO Colonias VALUES('63023','Buenos Aires')");
        db.execSQL("INSERT INTO Colonias VALUES('63035','Bugambilias')");
        db.execSQL("INSERT INTO Colonias VALUES('63500','Caja de Agua')");
        db.execSQL("INSERT INTO Colonias VALUES('63508','Camichín de Jauja')");
        db.execSQL("INSERT INTO Colonias VALUES('63196','Caminera')");
        db.execSQL("INSERT INTO Colonias VALUES('63173','Cantera del Nayar')");
        db.execSQL("INSERT INTO Colonias VALUES('63196','Castilla')");
        db.execSQL("INSERT INTO Colonias VALUES('63030','Chapultepec')");
        db.execSQL("INSERT INTO Colonias VALUES('63157','Ciudad del Valle')");
        db.execSQL("INSERT INTO Colonias VALUES('63173','Ciudad Industrial')");
        db.execSQL("INSERT INTO Colonias VALUES('63114','Colinas del Nayar')");
        db.execSQL("INSERT INTO Colonias VALUES('63180','Colinas del Rey')");
        db.execSQL("INSERT INTO Colonias VALUES('63173','Colinas del Valle')");
        db.execSQL("INSERT INTO Colonias VALUES('63175','Comerciantes')");
        db.execSQL("INSERT INTO Colonias VALUES('63195','Conasupo')");
        db.execSQL("INSERT INTO Colonias VALUES('63114','Ecologistas')");
        db.execSQL("INSERT INTO Colonias VALUES('63170','Dr. Lucas Vallarta')");
        db.execSQL("INSERT INTO Colonias VALUES('63175','Dr. Cuesta Barrios')");
        db.execSQL("INSERT INTO Colonias VALUES('63507','El Aguacate')");
        db.execSQL("INSERT INTO Colonias VALUES('63194','El Armadillo')");
        db.execSQL("INSERT INTO Colonias VALUES('63082','El Faisán')");
        db.execSQL("INSERT INTO Colonias VALUES('63507','El Izote')");
        db.execSQL("INSERT INTO Colonias VALUES('63510','El Jicote')");
        db.execSQL("INSERT INTO Colonias VALUES('63038','El Mirador INFONAVIT')");
        db.execSQL("INSERT INTO Colonias VALUES('63038','El Paraíso')");
        db.execSQL("INSERT INTO Colonias VALUES('63164','El Pedregal')");
        db.execSQL("INSERT INTO Colonias VALUES('63505','El Pichón')");
        db.execSQL("INSERT INTO Colonias VALUES('63509','El Refugio')");
        db.execSQL("INSERT INTO Colonias VALUES('63060','El Rodeo')");
        db.execSQL("INSERT INTO Colonias VALUES('63135','El Tecolote')");
        db.execSQL("INSERT INTO Colonias VALUES('63160','Electricistas')");
        db.execSQL("INSERT INTO Colonias VALUES('63070','Emiliano Zapata')");
        db.execSQL("INSERT INTO Colonias VALUES('63038','Emilio M. González')");
        db.execSQL("INSERT INTO Colonias VALUES('63109','Estadios')");
        db.execSQL("INSERT INTO Colonias VALUES('63177','Esteban Baca Calderón')");
        db.execSQL("INSERT INTO Colonias VALUES('63185','Félix Peña')");
        db.execSQL("INSERT INTO Colonias VALUES('63174','Flores Magón')");
        db.execSQL("INSERT INTO Colonias VALUES('63500','Francisco I Madero Centro')");
        db.execSQL("INSERT INTO Colonias VALUES('63019','Francisco Villa')");
        db.execSQL("INSERT INTO Colonias VALUES('63184','Gardenias')");
        db.execSQL("INSERT INTO Colonias VALUES('63174','Genaro Vázquez')");
        db.execSQL("INSERT INTO Colonias VALUES('63175','Gobernadores')");
        db.execSQL("INSERT INTO Colonias VALUES('63176','Gustavo Díaz Ordaz')");
        db.execSQL("INSERT INTO Colonias VALUES('63080','Heriberto Casas')");
        db.execSQL("INSERT INTO Colonias VALUES('63197','Hermosa Provincia')");
        db.execSQL("INSERT INTO Colonias VALUES('63022','INDECO')");
        db.execSQL("INSERT INTO Colonias VALUES('63136','Independencia')");
        db.execSQL("INSERT INTO Colonias VALUES('63183','Insurgentes')");
        db.execSQL("INSERT INTO Colonias VALUES('63195','Jacarandas')");
        db.execSQL("INSERT INTO Colonias VALUES('63168','Jardines de La Cruz')");
        db.execSQL("INSERT INTO Colonias VALUES('63173','Jazmines')");
        db.execSQL("INSERT INTO Colonias VALUES('63506','La Cantera')");
        db.execSQL("INSERT INTO Colonias VALUES('63195','La Joya')");
        db.execSQL("INSERT INTO Colonias VALUES('63037','La Loma')");
        db.execSQL("INSERT INTO Colonias VALUES('63175','Lagos del Country')");
        db.execSQL("INSERT INTO Colonias VALUES('63170','Las Aves')");
        db.execSQL("INSERT INTO Colonias VALUES('63117','Las Brisas')");
        db.execSQL("INSERT INTO Colonias VALUES('63500','Lázaro Cárdenas')");
        db.execSQL("INSERT INTO Colonias VALUES('63110','Lindavista')");
        db.execSQL("INSERT INTO Colonias VALUES('63019','Loma Hermosa')");
        db.execSQL("INSERT INTO Colonias VALUES('63061','Lomas Altas')");
        db.execSQL("INSERT INTO Colonias VALUES('63062','Lomas Bonitas')");
        db.execSQL("INSERT INTO Colonias VALUES('63037','Lomas de La Cruz')");
        db.execSQL("INSERT INTO Colonias VALUES('63197','Los Fresnos')");
        db.execSQL("INSERT INTO Colonias VALUES('63197','Los Sauces')");
        db.execSQL("INSERT INTO Colonias VALUES('63040','Magisterial')");
        db.execSQL("INSERT INTO Colonias VALUES('63150','Menchaca')");
        db.execSQL("INSERT INTO Colonias VALUES('63193','Miguel Hidalgo')");
        db.execSQL("INSERT INTO Colonias VALUES('63180','Moctezuma')");
        db.execSQL("INSERT INTO Colonias VALUES('63050','Mololoa')");
        db.execSQL("INSERT INTO Colonias VALUES('63160','Morelos')");
        db.execSQL("INSERT INTO Colonias VALUES('63173','Nayarabastos')");
        db.execSQL("INSERT INTO Colonias VALUES('63023','Ojo de Agua')");
        db.execSQL("INSERT INTO Colonias VALUES('63175','Oriental')");
        db.execSQL("INSERT INTO Colonias VALUES('63173','Parque Ecológico')");
        db.execSQL("INSERT INTO Colonias VALUES('63174','Prieto Crispín')");
        db.execSQL("INSERT INTO Colonias VALUES('63039','Puerta de La Laguna')");
        db.execSQL("INSERT INTO Colonias VALUES('63038','Reforma')");
        db.execSQL("INSERT INTO Colonias VALUES('63173','Revolución')");
        db.execSQL("INSERT INTO Colonias VALUES('63129','Rey Nayar')");
        db.execSQL("INSERT INTO Colonias VALUES('63511','San Cayetano')");
        db.execSQL("INSERT INTO Colonias VALUES('63089','Santa Cecilia')");
        db.execSQL("INSERT INTO Colonias VALUES('63088','Santa Fe')");
        db.execSQL("INSERT INTO Colonias VALUES('63020','Santa Teresita')");
        db.execSQL("INSERT INTO Colonias VALUES('63023','Senderos del Monte')");
        db.execSQL("INSERT INTO Colonias VALUES('63114','Solidaridad INFONAVIT')");
        db.execSQL("INSERT INTO Colonias VALUES('63115','SPAUAN')");
        db.execSQL("INSERT INTO Colonias VALUES('63000','Tepic Centro')");
        db.execSQL("INSERT INTO Colonias VALUES('63178','Tierra y Libertad')");
        db.execSQL("INSERT INTO Colonias VALUES('63155','Universidad Autónoma de Nayarit')");
        db.execSQL("INSERT INTO Colonias VALUES('63035','Valle de La Cruz')");
        db.execSQL("INSERT INTO Colonias VALUES('63195','Valle de Matatipac')");
        db.execSQL("INSERT INTO Colonias VALUES('63174','Venceremos')");
        db.execSQL("INSERT INTO Colonias VALUES('63507','Venustiano Carranza')");
        db.execSQL("INSERT INTO Colonias VALUES('63173','Villas del Roble')");
        db.execSQL("INSERT INTO Colonias VALUES('63173','Vistas de La Cantera')");
        db.execSQL("INSERT INTO Colonias VALUES('63037','Zapopan')");
        db.execSQL("INSERT INTO Colonias VALUES('63174','Zitacua')");
    }
}
