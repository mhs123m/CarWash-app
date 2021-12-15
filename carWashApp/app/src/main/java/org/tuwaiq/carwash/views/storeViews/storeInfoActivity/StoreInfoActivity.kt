package org.tuwaiq.carwash.views.storeViews.storeInfoActivity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.textfield.TextInputEditText
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.views.storeViews.StoreViewModel
import java.io.ByteArrayOutputStream


class StoreInfoActivity : AppCompatActivity() {
    // call viewModel and views here to access them in functions
    private val viewModel: StoreViewModel by viewModels()
    private lateinit var imgViewStoreLogo: ImageView
    private lateinit var textInputStoreName: TextInputEditText
    private lateinit var textInputStoreEmail: TextInputEditText
    private lateinit var textInputStorePhone: TextInputEditText
    private lateinit var textInputStoreLocation: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_info)

        //link views
        imgViewStoreLogo = findViewById(R.id.imageViewStoreLogo)
        textInputStoreName = findViewById(R.id.textInputStoreName)
        textInputStoreEmail = findViewById(R.id.textInputStoreEmail)
        textInputStorePhone = findViewById(R.id.textInputStorePhone)
        textInputStoreLocation = findViewById(R.id.textInputStoreLocation)

        // on img click open imgPicker
        imgViewStoreLogo.setOnClickListener {
            ImagePicker.with(this)
                .crop()    //Crop image(Optional), Check Customization for more option
                .compress(50)//Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )    //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            // Use Uri object instead of File to avoid storage permissions
            imgViewStoreLogo.setImageURI(uri)
            encodePicAndUploadToApi(uri)


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    fun encodePicAndUploadToApi(uri: Uri) {
        val bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri)

        // initialize byte stream
        val stream = ByteArrayOutputStream()

        // compress Bitmap
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

        // Initialize byte array
        val bytes: ByteArray = stream.toByteArray()
        // get base64 encoded string
        val encodedImage = Base64.encodeToString(bytes, Base64.DEFAULT)
        // set encoded text on textview
        println(encodedImage)
        // update info

//        viewModel.updateStoreInfo()
//        viewmod.updateImg(currentUser.id, User(
//                currentUser.avatar, currentUser.email, currentUser.id,
//                encodedImage, currentUser.name
//            )
//        )
    }
}