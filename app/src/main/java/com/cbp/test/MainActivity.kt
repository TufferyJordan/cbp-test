package com.cbp.test

import android.Manifest
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener
import com.karumi.dexter.listener.single.CompositePermissionListener
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var broadcastReceiver: NavigationBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_activity_toolbar)

        val navController = main_activity_nav_fragment.findNavController()
        val configurationBuilder = AppBarConfiguration.Builder(navController.graph)
        main_activity_toolbar.setupWithNavController(navController, configurationBuilder.build())
        broadcastReceiver = NavigationBroadcastReceiver(navController, this)
    }

    override fun onStart() {
        super.onStart()
        broadcastReceiver.registerReceiver()
    }

    override fun onStop() {
        broadcastReceiver.unregisterReceiver()
        super.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = main_activity_nav_fragment.findNavController()

        val baseListener = object : BasePermissionListener() {
            override fun onPermissionGranted(permissionGrantedResponse: PermissionGrantedResponse) {
                NavigationUI.onNavDestinationSelected(item, navController)
            }
        }

        val dialogPermissionListener: PermissionListener = DialogOnDeniedPermissionListener.Builder
            .withContext(this)
            .withTitle(R.string.camera_permission_rational_title)
            .withMessage(getString(R.string.camera_permission_rational_description))
            .withButtonText(getString(R.string.camera_permission_rational_ok))
            .build()

        Dexter.withContext(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(
                CompositePermissionListener(
                    baseListener,
                    dialogPermissionListener
                )
            )
            .check()
        return super.onOptionsItemSelected(item)
    }
}
