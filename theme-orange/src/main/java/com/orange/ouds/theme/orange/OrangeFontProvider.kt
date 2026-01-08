/*
 * Software Name: OUDS Android
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ouds.theme.orange

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import androidx.compose.ui.text.font.FontWeight
import androidx.core.provider.FontsContractCompat
import java.io.File
import java.io.FileOutputStream
import java.net.URL

/**
 * The font provider for the Orange theme.
 */
class OrangeFontProvider : ContentProvider() {

    companion object {

        /** The Orange font provider authority */
        const val AUTHORITY = "com.orange.ouds.theme.orange.fontprovider"

        internal const val QUERY_WEIGHT_PARAMETER = "weight"

        private const val CDN_BASE_URL = "https://mastermedia.dam-broadcast.com"
    }

    private enum class Font(val filename: String, val cdnFilename: String, val fontWeight: FontWeight) {

        HelveticaNeueRoman("helvetica_neue_roman.ttf", "pm_12751_491_491559-ngke9h7d3m-HelveticaNeue-Roman.ttf", FontWeight.Normal),
        HelveticaNeueMedium("helvetica_neue_medium.ttf", "pm_12751_491_491556-bd333uw5x5-HelveticaNeue-Medium.ttf", FontWeight.Medium),
        HelveticaNeueBold("helvetica_neue_bold.ttf", "pm_12751_491_491553-29arstkwm3-HelveticaNeue-Bold.ttf", FontWeight.Bold)
    }

    override fun onCreate(): Boolean = true

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?,
        cancellationSignal: CancellationSignal?
    ): Cursor? {
        return context?.let { context ->
            val arguments = selectionArgs?.firstOrNull()
                ?.split("&")
                ?.map { it.split("=") }
                ?.associate { it[0] to it[1] }
                .orEmpty()
            val fontWeight = arguments[QUERY_WEIGHT_PARAMETER]?.toIntOrNull()?.let { FontWeight(it) }
            var cursor: Cursor? = null
            val font = Font.entries.firstOrNull { it.fontWeight == fontWeight }
            if (font != null) {
                val fontFile = File(context.filesDir, font.filename)
                if (!fontFile.exists()) {
                    try {
                        URL("$CDN_BASE_URL/${font.cdnFilename}").openStream().use { input ->
                            FileOutputStream(fontFile).use { output ->
                                input.copyTo(output)
                            }
                        }
                    } catch (_: Exception) {
                        cursor = MatrixCursor(arrayOf(FontsContractCompat.Columns.RESULT_CODE))
                        cursor.addRow(arrayOf(FontsContractCompat.Columns.RESULT_CODE_FONT_UNAVAILABLE))
                    }
                }

                if (cursor == null) {
                    cursor = MatrixCursor(
                        arrayOf(
                            FontsContractCompat.Columns._ID,
                            FontsContractCompat.Columns.FILE_ID,
                            FontsContractCompat.Columns.WEIGHT,
                            FontsContractCompat.Columns.ITALIC,
                            FontsContractCompat.Columns.RESULT_CODE
                        )
                    )

                    val id = Font.entries.indexOf(font).toLong()
                    cursor.addRow(
                        arrayOf<Any>(
                            id, // _ID
                            id, // FILE_ID
                            font.fontWeight.weight, // WEIGHT
                            0, // ITALIC
                            FontsContractCompat.Columns.RESULT_CODE_OK // RESULT_CODE
                        )
                    )
                }
            }

            return@let cursor
        }
    }

    override fun openFile(uri: Uri, mode: String): ParcelFileDescriptor? {
        return context?.let { context ->
            val font = uri.lastPathSegment?.toIntOrNull()?.let { Font.entries[it] }
            font?.filename?.let { filename ->
                val file = File(context.filesDir, filename)
                if (file.exists()) {
                    ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
                } else {
                    null
                }
            }
        }
    }

    override fun query(uri: Uri, projection: Array<out String?>?, selection: String?, selectionArgs: Array<out String?>?, sortOrder: String?): Cursor? = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String?>?): Int = 0

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String?>?): Int = 0
}
