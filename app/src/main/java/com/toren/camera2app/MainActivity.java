package com.toren.camera2app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_CODE = 100;
    Button backBtn;
    Button frontBtn;
    int cameraId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backBtn = findViewById(R.id.backCameraButton);
        frontBtn = findViewById(R.id.frontCameraButton);

        backBtn.setOnClickListener(button -> {
            cameraId = 0;
            checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
        });

        frontBtn.setOnClickListener(button ->{
            cameraId = 1;
            checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
        });



    }


    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[] { permission }, requestCode);
        }
        else {
            if (cameraId==0) {
                System.out.println(00);
                openBackCamera();
            } else {
                System.out.println(11);
                openFrontCamera();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {

            // Checking whether user granted the permission or not.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (cameraId==0) {
                    openBackCamera();
                } else {
                    openFrontCamera();
                }

                Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show(); // Showing the toast message
            }
            else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();  // Showing the toast message
            }
        }
    }

    private void openFrontCamera() {
        System.out.println(000);
            Intent intent = new Intent(this, CameraActivity.class);
            intent.putExtra("cameraId", 1);
            startActivity(intent);
        }

    private void openBackCamera() {
        System.out.println(111);
            Intent intent = new Intent(this, CameraActivity.class);
            intent.putExtra("cameraId", 0);
            startActivity(intent);
        }
    }
