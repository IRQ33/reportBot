package org.irq3

import com.google.gson.Gson
import org.irq3.Models.SavedData
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class JsonOperations{
    fun  loadJsonFromResource(fileName: String): SavedData? {
        try {
            val inputStream = this::class.java.getResourceAsStream(fileName)
            if (inputStream == null) {
                println("No files")
                return null
            }

            val reader = InputStreamReader(inputStream)

            val gson = Gson()
            val data = gson.fromJson(reader, SavedData::class.java)
            return data
        } catch (e: Exception) {
            return null
        }
    }
    fun writeJsonToResources(fileName: String, data: SavedData){
        val inputStream = this::class.java.getResource(fileName)
        val file = java.io.File(inputStream!!.path)
        val writer = OutputStreamWriter(FileOutputStream(file))
        Gson().toJson(data,writer)
        writer.close()

    }
}