package org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment.step4AppointmentBooked

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.getDateFormat
import android.text.format.DateFormat.getTimeFormat
import android.widget.*
import androidx.annotation.RequiresApi
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.databinding.ActivityAppointmentBookedBinding
import org.tuwaiq.carwash.model.ServiceStore
import org.tuwaiq.carwash.utils.TimeSlotsHelperFunctions
import java.text.DateFormat
import java.util.*

class AppointmentBookedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppointmentBookedBinding
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private lateinit var imgBack: ImageView
    private lateinit var btnDone: Button
    private lateinit var tvAppointments: TextView
    private lateinit var tvRouteMap: TextView
    private lateinit var btnNotifyMe: Button
    private lateinit var serviceStore: ServiceStore
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentBookedBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        serviceStore = intent.getSerializableExtra("serviceToBook") as ServiceStore
//        val timeOfAppointment = TimeSlotsHelperFunctions.getStartTime(serviceStore.)
        initializeViews()

        createNotificationChannel()
        binding.buttonCreateNotification.setOnClickListener {
            scheduleNotification()
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun scheduleNotification() {
        val title = "CarWash"
            val message = "Hey you have an upcoming appointment"
        val intent = Intent(applicationContext,NotifyUser::class.java)
        intent.putExtra(titleExtra, "CarWash")
        intent.putExtra(messageExtra,"Hey you have an upcoming appointment")

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationId,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
        showAlert(time)

    }

    private fun showAlert(time: Long) {
        val date = Date(time)
        val dateFormat = getDateFormat(applicationContext)
        val timeFormat = getTimeFormat(applicationContext)

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.notification_alert_title))
            .setMessage(
                "Notification is scheduled" +
                        "\nAt: " + dateFormat.format(date) + " " + timeFormat.format(time)
            )
            .setPositiveButton("okay"){_, _ ->}
            .show()

    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun getTime(): Long {
        val minutes = timePicker.minute
        val hour = timePicker.hour
        val day = datePicker.dayOfMonth
        val month = datePicker.month
        val year = datePicker.year

        val calender = Calendar.getInstance()
        calender.set(year,month,day,hour,minutes)
        return calender.timeInMillis
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val name = "Notif Channel"
        val desc = "A description of the Channel"
        val importance = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NotificationManager.IMPORTANCE_DEFAULT
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        val channel = NotificationChannel(channelId,name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }

    private fun initializeViews() {
        datePicker = binding.datePicker
        timePicker = binding.timePicker
        imgBack = binding.imageViewBackBtnBooked
        btnDone = binding.buttonDoneBooked
        tvAppointments = binding.textViewAppointmentsBooked
        tvRouteMap = binding.textViewMapRoute
        btnNotifyMe = binding.buttonCreateNotification
    }
}