package com.example.obviousgalleryapp.model

/**
 * Data class for each image item
 */
data class ImageResponseItem(
	val date: String? = null,
	val copyright: String? = null,
	val mediaType: String? = null,
	val hdurl: String? = null,
	val serviceVersion: String? = null,
	val explanation: String? = null,
	val title: String? = null,
	val url: String? = null
)
