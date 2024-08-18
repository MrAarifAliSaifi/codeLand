package com.example.codeland.ui

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.example.codeland.R
import com.example.codeland.databinding.GalleryActivityBinding
import com.example.codeland.ui.base.BaseActivity
import com.example.codeland.ui.viewmodels.GalleryActivityVM
import java.io.OutputStream

class GalleryActivity : BaseActivity<GalleryActivityVM, GalleryActivityBinding>() {
    companion object {
        const val TAG = "GalleryActivity"
        fun getStartIntent(context: Context) = Intent(context, GalleryActivity::class.java)
    }

    override fun provideViewBinding(): GalleryActivityBinding =
        GalleryActivityBinding.inflate(layoutInflater)

    override fun provideLayoutId(): Int = R.layout.gallery_activity

    override fun providePageName(): String = TAG

    override fun getViewModelClass(): Class<GalleryActivityVM> {
        return GalleryActivityVM::class.java
    }

    override fun setupView(savedInstanceState: Bundle?) {
        binding.btnUpload.setOnClickListener {
            showUploadOptions()
        }
        binding.btnView.setOnClickListener {
            viewModel.selectedImage.observe(this, Observer { bitmap ->
                binding.uploadIcon.setImageBitmap(bitmap)
                Toast.makeText(this, "This is the thing which we have to think about the scenario of the fault.", Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun showUploadOptions() {
        val options = arrayOf<CharSequence>("Camera", "Gallery")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select an option")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> openCamera()
                1 -> openGallery()
            }
        }
        builder.show()
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryActivityResultLauncher.launch(intent)
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraActivityResultLauncher.launch(intent)
    }

    private val galleryActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val selectedImageUri: Uri? = result.data?.data
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                viewModel.setSelectedImage(bitmap)
                Toast.makeText(this, getString(R.string.message_image_uploaded), Toast.LENGTH_SHORT).show()
            }
        }

    private val cameraActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val imageBitmap = result.data?.extras?.get("data") as Bitmap
                viewModel.setSelectedImage(imageBitmap)
                saveImageToGallery(imageBitmap)
                Toast.makeText(this, getString(R.string.message_image_saved_to_Gallery), Toast.LENGTH_SHORT).show()
            }
        }

    private fun saveImageToGallery(bitmap: Bitmap) {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "IMG_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp")
        }

        val uri: Uri? = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        uri?.let {
            val outputStream: OutputStream? = contentResolver.openOutputStream(it)
            outputStream?.use { stream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            }
        }
    }
}
