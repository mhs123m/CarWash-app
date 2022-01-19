package org.tuwaiq.carwash.views.storeViews.storeMainActivity.moreFragment.storeProfileActivity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.textfield.TextInputEditText
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.utils.Globals
import org.tuwaiq.carwash.utils.HelperFunctions
import org.tuwaiq.carwash.utils.LocationHelperFunctions
import org.tuwaiq.carwash.views.storeViews.StoreViewModel
import java.io.ByteArrayOutputStream


class StoreProfileActivity : AppCompatActivity() {
    // call viewModel and views here to access them in functions
    private var loggedInStore: Store? = null
    private lateinit var locationHelperFunctions: LocationHelperFunctions
    private var encodedPic: String? = null
    private val viewModel: StoreViewModel by viewModels()
    private lateinit var imgViewStoreLogo: ImageView
    private lateinit var textInputStoreName: TextInputEditText
    private lateinit var textInputStoreEmail: TextInputEditText
    private lateinit var textInputStorePhone: TextInputEditText
    private lateinit var textInputStoreLocation: TextInputEditText
    private lateinit var backBtn: ImageView
    private lateinit var btnUpdateInfo: Button
    private lateinit var cvPickLocation: CardView
    private lateinit var currentStore: Store
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_info)
        // get instance of location helper
        locationHelperFunctions = LocationHelperFunctions.getInstance()

        // linkViews and set data
        linkViews()
        setViewsWithDataFromServer()

        backBtn.setOnClickListener {
            finish()
        }


        // on img click open imgPicker
        imgViewStoreLogo.setOnClickListener {
            // once this intent to take a picture is done, onActivityResult would be have called, so
            // encodedPic would be set to new taken picture encoded
            onActivityResult(0, 0, intent)
            ImagePicker.with(this)
                .crop()    //Crop image(Optional), Check Customization for more option
                .compress(500)//Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )    //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        // on location card view  click, open map activity
        cvPickLocation.setOnClickListener {
            // get current location to set map view marker as the current.
            val i = Intent(this, StoreMapsActivity::class.java)
            startActivity(i)
        }


        // here we click the btn update
        btnUpdateInfo.setOnClickListener {
            updateInfo()
        }

    }

    private fun linkViews() {
        //link views
        imgViewStoreLogo = findViewById(R.id.imageViewStoreLogo)
        textInputStoreName = findViewById(R.id.textInputStoreName)
        textInputStoreEmail = findViewById(R.id.textInputStoreEmail)
        textInputStorePhone = findViewById(R.id.textInputStorePhone)
        textInputStoreLocation = findViewById(R.id.textInputStoreLocation)
        btnUpdateInfo = findViewById(R.id.buttonStoreUpdateInfo)
        backBtn = findViewById(R.id.imageViewArrowBackStore)
        cvPickLocation = findViewById(R.id.cardViewPickLocation)
    }

    private fun updateInfo() {
        val geometry = locationHelperFunctions.getGeometry()
        // updated info
        currentStore = Store(
            loggedInStore?._id,
            textInputStoreName.text.toString(),
            textInputStoreEmail.text.toString(),
            textInputStorePhone.text.toString(),
            null, encodedPic, geometry
        )
        viewModel.updateStoreInfo(currentStore._id!!, currentStore)
        viewModel.updatedStoreLiveData.observe(this) {
            Log.d("STORE_UPDATE", "btn pressed: $it")
            // set textInput with info of current logged in store
            textInputStoreName.setText(it?.name)
            textInputStoreEmail.setText(it?.email)
            textInputStorePhone.setText(it?.phone)

            it?.geometry?.let { geometry ->
                textInputStoreLocation.setText(geometry.formattedAddress)
            }
        }
    }

    private fun setViewsWithDataFromServer() {
        viewModel.getStoreById(Globals.sharedPreferences.getString("ID", null)!!)
        viewModel.oneStoreLiveData.observe(this) { store ->
            loggedInStore = store
            // set textInput with info of current logged in store
            textInputStoreName.setText(loggedInStore?.name)
            textInputStoreEmail.setText(loggedInStore?.email)
            textInputStorePhone.setText(loggedInStore?.phone)
            loggedInStore?.logo?.let {
                encodedPic = it
                imgViewStoreLogo.setImageBitmap(HelperFunctions.decodePicFromApi(encodedPic!!))
            }
            loggedInStore?.geometry?.let {
                textInputStoreLocation.setText(it.formattedAddress)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            // set img view with taken or chosen picture
            imgViewStoreLogo.setImageURI(uri)
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