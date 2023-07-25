package com.example.backend.services

import org.slf4j.LoggerFactory
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.File
import java.io.IOException
import java.util.UUID

class FileUploadService {
    val logger = LoggerFactory.getLogger(javaClass)
    private val UPLOAD_DIR = "images"

    fun uploadImage(file: MultipartFile) : String {
        if (file.isEmpty) return "upload file"

        return try {
            // create folder
            val uploadDir = File(UPLOAD_DIR)
            if (!uploadDir.exists()) uploadDir.mkdir()

            // save file to images folder
            val destFileName = "${uploadDir.absolutePath}${File.separator}${UUID.randomUUID()}${file.originalFilename}"
            val destFile = File(destFileName)
            file.transferTo(destFile)
            val fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/$UPLOAD_DIR/${destFile.name}").toUriString()

            logger.error("image uploaded ${fileUri}")

            "true $fileUri"
        } catch (e: IOException) {
            e.printStackTrace()
            "false ${e.message}"
        }
    }

    fun getFileExtension(fileName: String): String {
        val lastDotPosition: Int = fileName.lastIndexOf('.')
        return fileName.substring(lastDotPosition)
    }
}











