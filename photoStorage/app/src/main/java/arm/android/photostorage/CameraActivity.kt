package arm.android.photostorage

import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.util.Date
import java.util.Locale

class CameraActivity : AppCompatActivity() {
    private lateinit var photoUri: Uri
    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) Toast.makeText(this, "фото сохранено", Toast.LENGTH_SHORT).show()
        else Toast.makeText(this, "фото не сохранено", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val photoFile = createImageFile().also {
            photoUri = FileProvider.getUriForFile(
                this,
                "${packageName}.fileprovider",
                it
            )
        }
        takePictureLauncher.launch(photoUri)
    }

    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        return File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "JPEG_${timestamp}.jpg"
        ).apply { createNewFile() }
    }
}