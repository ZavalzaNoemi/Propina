package zavalza.noemi.calculadorapropina

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var propina = 0.0
        val calcular = findViewById<Button>(R.id.calcular)
        calcular.setOnClickListener {
            val costo = findViewById<EditText>(R.id.costo_servicio).text.toString().toDoubleOrNull()
            if (costo == null) {
                propina = 0.0
                findViewById<TextView>(R.id.propina).text = "Propina: $propina"
                return@setOnClickListener
            }
            val porcentaje = when (findViewById<RadioGroup>(R.id.opciones).checkedRadioButtonId) {
                R.id.veinte -> 0.20
                R.id.dieciocho -> 0.18
                else -> 0.15
            }
            propina = costo * porcentaje
            if (findViewById<Switch>(R.id.redondear).isChecked) {
                propina = kotlin.math.ceil(propina)
            }
            findViewById<TextView>(R.id.propina).text = "Propina: $propina"
        }




    }
}