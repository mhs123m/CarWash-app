package org.tuwaiq.carwash.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.views.userViews.userLoginAndRegister.UserSignInActivity
import androidx.core.content.ContextCompat.startActivity
import org.tuwaiq.carwash.views.splashScreen.SplashActivity
import java.util.*


class MoreFragmentHelper {

    companion object {


        // share app
        fun shareApp(context: Context) {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT, context.getString(R.string.share_app) +
                            "(AppStore app id)"
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }

        // contact us
        fun sendFeedBack(context: Context) {
            val mailIntent = Intent(Intent.ACTION_SENDTO)
            mailIntent.data = Uri.parse("mailto:")
            mailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("support@shiny.com"))
            mailIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.Shiny_support))
            context.startActivity(mailIntent)
        }

        // function for dark mode
        fun darkKnightRises(context: Context) {


            AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.darkMode))
                .setIcon(AppCompatResources.getDrawable(context, R.drawable.more_dark_mode_icon))
                .setMessage(context.getString(R.string.dark_mode_message))
                .setNeutralButton(context.getString(R.string.action_cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setNegativeButton(context.getString(R.string.day_mode)) { dialog, _ ->
                    // make them light
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    dialog.dismiss()
                }
                .setPositiveButton(context.getString(R.string.night_mode)) { dialog, _ ->
                    dialog.dismiss()
                    // make theme of app dark
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                .create()
                .show()


        }

        // function to switch language
        fun changeLanguage(activity: Activity,context: Context){
            AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.language))
                .setIcon(AppCompatResources.getDrawable(context, R.drawable.more_language_icon))
                .setMessage(context.getString(R.string.language_message))
                .setNeutralButton(context.getString(R.string.action_cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setNegativeButton(context.getString(R.string.arabic)) { _, _ ->
                    // arabic
                    setLocale(context,"ar")
                    activity.finish()
                }
                .setPositiveButton(context.getString(R.string.english)) { _, _ ->
                    // english
                    setLocale(context,"en")
                    activity.finish()
                }
                .create()
                .show()
        }


        // log out
        fun signOut(context: Context) {
            AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.logOut))
                .setIcon(AppCompatResources.getDrawable(context, R.drawable.more_log_out_icon))
                .setMessage(context.getString(R.string.log_out_message))
                .setNegativeButton(context.getString(R.string.action_cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(context.getString(R.string.logOut)) { _, _ ->
                    logOut(context)

                }
                .create()
                .show()

        }

        private fun logOut(context: Context) {
            val prefClear = Globals.sharedPreferences.edit()
            prefClear.clear().apply()
            val i = Intent(context, UserSignInActivity::class.java)
            i.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
                        or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        or Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
            context.startActivity(i)
        }

        private fun setLocale(context: Context,localeName: String) {

            val locale = Locale(localeName)
            val res = context.resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.setLocale(locale)
            res.updateConfiguration(conf,dm)
            val intent = Intent(context, SplashActivity::class.java)
            intent.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
                        or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        or Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
            context.startActivity(intent)
        }
    }

}