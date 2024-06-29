
package com.qualentum.sprint4.presentation.common

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.qualentum.sprint4.R
import com.qualentum.sprint4.presentation.extensions.navigateToSettings
import javax.inject.Singleton

//@Singleton
//class Mytoolbar: MaterialToolbar() {
//}

/**
 * CREAR UNA CLASE COMO LOS VIEW HOLDER DE LOS RECYCLER VIEW PARA LA TOOLBAR
 *
 * CREAR UN METODO EN EL QUE SE INSTANCIE Y SE CONFIGURE LA TOOLBAR DESDE EL MAIN
 *
 * CONFIGURAR UN METODO QUE SE PUEDA LLAMAR DESDE CUALQUIER CLASE QUE CAMBIE EL TITULO
 *
 * CONFIGURAR LA TOOLBAR PARA QUE SEA EXPANDIBLE (Y DECORARLA)
 *
 *
 */

@Singleton
class ToolbarManager(private val context: Context, private val toolbar: MaterialToolbar, navController: NavController) {

    init {
        toolbar.setupWithNavController(navController)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settingsFragment -> {
                    navController.navigateToSettings()
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    fun setTitle(title: String){
        toolbar.setTitle(title)
    }

}