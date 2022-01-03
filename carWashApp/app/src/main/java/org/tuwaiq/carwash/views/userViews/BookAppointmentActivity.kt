package org.tuwaiq.carwash.views.userViews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.ServiceModel

class BookAppointmentActivity : AppCompatActivity() {
    lateinit var service: ServiceModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)

        service = intent.getSerializableExtra("serviceToBook") as ServiceModel
    }
}