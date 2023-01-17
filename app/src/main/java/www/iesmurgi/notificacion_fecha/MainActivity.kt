package www.iesmurgi.notificacion_fecha

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import javax.xml.datatype.DatatypeConstants
import javax.xml.datatype.DatatypeConstants.MONTHS

class MainActivity : AppCompatActivity() {
    private lateinit var BotonDia: Button
    private lateinit var BotonHora: Button
    private lateinit var BotonNotificacion: Button
    private lateinit var BotonCita: Button
    private lateinit var Dia: TextView
    private  lateinit var Hora: TextView
    private  lateinit var DiaCita: TextView
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BotonDia = findViewById(R.id.dia)
        BotonHora = findViewById(R.id.Hora)
        BotonNotificacion = findViewById(R.id.Notificacion)
        BotonCita=findViewById(R.id.BotonCita)
        Dia = findViewById(R.id.textDate)
        DiaCita = findViewById(R.id.textCita)
        Hora=findViewById(R.id.textHora)


        var cal = Calendar.getInstance()
        var fecha=Calendar.getInstance()
        fecha.add(Calendar.DATE, 14)
        val hora=Calendar.getInstance()
        hora.add(Calendar.HOUR, 1)



        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MM/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                Dia.text = sdf.format(cal.time)
            }

        BotonDia.setOnClickListener{
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        BotonCita.setOnClickListener{
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MM/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                var cmp=cal.compareTo(fecha)
                if(cmp>0) {
                    DiaCita.text = sdf.format(cal.time)
                }else{
                    Toast.makeText(applicationContext, "El dia no puede ser inferior de aqui a dos semanas", Toast.LENGTH_SHORT).show()
                }

            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()

        }

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            var cmp=cal.compareTo(hora)
            if(cmp>0)
                Hora.text = SimpleDateFormat("HH:mm").format(cal.time)
            else
                Toast.makeText(applicationContext, "La hora tiene que ser superior a la actual", Toast.LENGTH_SHORT).show()
        }

        BotonHora.setOnClickListener{
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
    }
}
