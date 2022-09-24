package com.obvious.galleryapp

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*


@InstallIn(SingletonComponent::class)
@Module
class Utils {

    companion object {

        /**
         * method to get Image data from assets folder into a Data class
         */
        fun getJsonFromAssets(context: Context, fileName: String): String {
            var jsonString = ""
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            jsonString = String(buffer)

            return jsonString
        }

        /**
         * method to show toast message
         */
        fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        /**
         * method to parse the date in required format
         */
        @SuppressLint("SimpleDateFormat")
        fun getParsedDate(input: String): String {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val formatter =
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val date = LocalDate.parse(input, formatter)
                    val monthName = date.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                    return date.dayOfMonth.toString() + " " + monthName + " " + date.year.toString()
                } else {
                    val parser = SimpleDateFormat("yyyy-MM-dd")
                    val formatter = SimpleDateFormat("dd MMM yyyy")
                    return formatter.format(parser.parse(input))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                return ""
            }
        }

        /**
         * method to get placeholder for image loading
         */
        fun getGlidePlaceHolder(context: Context): CircularProgressDrawable {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            return circularProgressDrawable
        }

        /**
         * method to check if internet is available
         */
        fun isOnline(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
            return false
        }

    }
}