package arm.android.photostorage

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.Manifest
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var cameraPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var openCameraLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        cameraPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) startCameraActivity()
            else Toast.makeText(this, "нужно разрешение на съемку", Toast.LENGTH_SHORT).show()
        }

        openCameraLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {  }


        findViewById<Button>(R.id.btn_open_camera).setOnClickListener {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                startCameraActivity()
            } else {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }


        findViewById<Button>(R.id.btn_open_gallery).setOnClickListener {
            startActivity(Intent(this, GalleryActivity::class.java))
        }
    }

    private fun startCameraActivity() {
        openCameraLauncher.launch(Intent(this, CameraActivity::class.java))
    }
}
