package com.example.myfridgehome.dto

import android.app.Notification
import android.app.Notification.MessagingStyle.Message
import android.util.JsonWriter
import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.io.StringWriter


//this class collects the user input and converts it into a writable string format.
abstract class SetStoredFoods(
    var name: String,
    var type: String,
    var quantity: Int,
    var measurement: String
) {
    //collects input from the user and assigns to local values.
    val nm = name
    val ty = type
    val qt = quantity
    val mt = measurement
    val filename: String = "../assets/storedIngredients.json"

    @Throws(IOException::class)
    open fun writeJsonStream(
        out: OutputStream?,
        messages: List<Message>
    ) {
        val writer =
            JsonWriter(OutputStreamWriter(out, "UTF-8"))  //Typing Error
        writer.setIndent("  ")
        writeMessagesArray(writer, messages)
        writer.close()
    }

    @Throws(IOException::class)
    open fun writeMessagesArray(
        writer: JsonWriter,
        messages: List<Message>
    ) {
        writer.beginArray()
        for (message in messages) {
            writeMessage(writer, message)
        }
        writer.endArray()
    }

    @Throws(IOException::class)
    open fun writeMessage(writer: JsonWriter, message: Notification.MessagingStyle.Message) {
        writer.beginObject()
        writer.name("id").value(message.toString())
        writer.name("name").value(message.toString())

        writeFoodItem(writer, message.toString())
        writer.endObject()
    }

    @Throws(IOException::class)
    open fun writeFoodItem(writer: JsonWriter, food: String) {
        writer.beginObject()
        writer.endObject()
    }

    @Throws(IOException::class)
    open fun writeDoublesArray(
        writer: JsonWriter,
        doubles: List<Double?>
    ) {
        writer.beginArray()
        for (value in doubles) {
            writer.value(value)
        }
        writer.endArray()
    }


    fun sendToJson() {
        var item: String = toString()
        var writer: JsonWriter
        val output = StringWriter()
    }

    //    fun writeMessage(js: JsonWriter, message : String){
//
//    }
    override fun toString(): String {
        //adds user inputted information to a string that is labelled for each item.
        return "Category [name: ${this.nm}, type: ${this.ty}, quantity: ${this.qt}], measurement: ${this.mt}"
    }
}
