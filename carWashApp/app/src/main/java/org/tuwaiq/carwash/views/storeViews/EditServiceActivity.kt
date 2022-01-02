package org.tuwaiq.carwash.views.storeViews

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.databinding.ActivityEditServiceBinding
import org.tuwaiq.carwash.model.ServiceModel
import org.tuwaiq.carwash.util.Globals
import org.tuwaiq.carwash.util.HelperFunctions
import org.tuwaiq.carwash.views.ServiceViewModel
import java.io.ByteArrayOutputStream

class EditServiceActivity : AppCompatActivity() {
    val viewModel: ServiceViewModel by viewModels()
    private lateinit var binding: ActivityEditServiceBinding
    private lateinit var service: ServiceModel
    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var price: TextInputEditText
    private lateinit var duration: TextInputEditText
    private lateinit var available: SwitchMaterial
    private lateinit var imgService: ImageView
    private var encodedPic: String? = null
    private var updatedService: ServiceModel? = null
    // get token
    private val xAuthHeader = Globals.sharedPreferences.getString("Token", null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize views
        title = binding.editTextServiceTitleEdit
        description = binding.editTextServiceDescriptionEdit
        price = binding.textInputServicePriceEdit
        duration = binding.textInputServiceDurationEdit
        available = binding.switchServiceAvailableEdit
        imgService = binding.imageViewServiceLogoEdit

        service = intent.getSerializableExtra("service") as ServiceModel

        // set views with data
        setViewsWithServerInfo()

        // change picture
        binding.imageViewChangeServicePictureOnClickEdit.setOnClickListener {
            onActivityResult(0, 0, intent)
            ImagePicker.with(this)
                .crop()    //Crop image(Optional), Check Customization for more option
                .compress(50)//Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )    //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        // btn updated pressed
        binding.buttonUpdateService.setOnClickListener {
            updateService()
            finish()
        }

        binding.buttonDeleteService.setOnClickListener {
            deleteService()
            finish()
        }


    }

    private fun deleteService() {
        viewModel.deleteService(xAuthHeader!!,service._id!!)
    }

    private fun updateService() {
        val available = available.isChecked
        updatedService = ServiceModel(
            service._id,
            encodedPic,
            title.text.toString(),
            description.text.toString(),
            price.text.toString().toDouble(),
            duration.text.toString().toDouble(),
            available,
            null,
            null,
            service.storeId
        )
        println("$xAuthHeader")
        viewModel.editService(xAuthHeader!!, service._id!!, updatedService!!)
        viewModel.editedServiceLiveData.observe(this) {
            Log.d("UPDATED", "new serv: $it")
        }
    }

    private fun setViewsWithServerInfo() {
        title.setText(service.title)
        description.setText(service.description)
        price.setText(service.price.toString())
        duration.setText(service.durationInMin.toString())
        imgService.setImageBitmap(service.logo?.let {
            encodedPic = it
            HelperFunctions.decodePicFromApi(it)
        })
        available.isChecked = service.available
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            // set img view with taken or chosen picture
            binding.imageViewServiceLogoEdit.setImageURI(uri)
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