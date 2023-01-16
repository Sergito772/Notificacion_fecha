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
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants
import javax.xml.datatype.DatatypeConstants.MONTHS

class MainActivity : AppCompatActivity() {
    private lateinit var BotonDia: Button
    private lateinit var BotonHora: Button
    private lateinit var BotonNotificacion: Button
    private lateinit var Dia: TextView
    private  lateinit var Hora: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BotonDia = findViewById(R.id.dia)
        BotonHora = findViewById(R.id.Hora)
        BotonNotificacion = findViewById(R.id.Notificacion)
        Dia = findViewById(R.id.textDate)
        Hora=findViewById(R.id.textHora)


        var cal = Calendar.getInstance()

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

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            Hora.text = SimpleDateFormat("HH:mm").format(cal.time)
        }

        BotonHora.setOnClickListener{
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
    }
}
