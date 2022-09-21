package com.obvious.galleryapp

import android.content.Context
import android.widget.Toast
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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


    }
}