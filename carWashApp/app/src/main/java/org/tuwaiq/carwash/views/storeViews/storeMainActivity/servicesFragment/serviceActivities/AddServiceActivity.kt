package org.tuwaiq.carwash.views.storeViews.storeMainActivity.servicesFragment.serviceActivities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.github.dhaval2404.imagepicker.ImagePicker
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import org.tuwaiq.carwash.databinding.ActivityAddServiceBinding
import org.tuwaiq.carwash.model.ServiceModel
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.utils.Globals
import org.tuwaiq.carwash.views.ServiceViewModel
import java.io.ByteArrayOutputStream

class AddServiceActivity : AppCompatActivity() {
    val viewModel: ServiceViewModel by viewModels()
    private lateinit var serviceModel: ServiceModel
    private lateinit var cpb: CircularProgressBar
    private var encodedPic: String? = null
    private lateinit var binding: ActivityAddServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // upload picture
        binding.imageViewChangeServicePictureOnClick.setOnClickListener {
           intentImagePicker()
        }
        // on btn clicked
        binding.buttonAddService.setOnClickListener {
            addService()
        }

        binding.imageViewBackAdd.setOnClickListener {
            finish()
        }
        cpb = binding.circularProgressBarAdd

    }

    private fun intentImagePicker() {
        onActivityResult(0, 0, intent)
        ImagePicker.with(this)
            .crop()    //Crop image(Optional), Check Customization for more option
            .compress(100)//Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            )    //Final image resolution will be less than 1080 x 1080(Optional)
            .start()
    }

    private fun addService() {
        cpb.visibility = View.VISIBLE
        val title = binding.editTextServiceTitle.text.toString()
        val description = binding.editTextServiceDescription.text.toString()
        val price = binding.textInputServicePrice.text.toString().toDouble()
        val duration = binding.textInputServiceDuration.text.toString().toDouble()
        var available = binding.switchServiceAvailable.isChecked
        // get token and store Id
        val xAuthHeader = Globals.sharedPreferences.getString("Token", null)
        val storeId = Globals.sharedPreferences.getString("ID", null)
        serviceModel = ServiceModel(
            null,
            encodedPic,
            title,
            description,
            price,
            duration,
            available,
            null,
            null,
            storeId
        )

        viewModel.addNewService(xAuthHeader!!, serviceModel)
        viewModel.newServiceLiveData.observe(this) {
            Log.d("SERVICE", "ADDED?: $it")
            cpb.visibility = View.GONE
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            // set img view with taken or chosen picture
            binding.imageViewServiceLogo.setImageURI(uri)
            encodedPic = encodePicture(uri)


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun encodePicture(uri: Uri): String {
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)

        // initialize byte stream
        val stream = ByteArrayOutputStream()

        // compress Bitmap
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

        // Initialize byte array
        val bytes: ByteArray = stream.toByteArray()

        // return encoded string
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }
}