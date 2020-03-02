package com.example.myfridgehome.dto

import android.app.Notification
import android.app.Notification.MessagingStyle.Message
import android.util.JsonWriter
import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.io.StringWriter



//this class collects the user input and converts it into a writable string format.
abstract class SetStoredFoods(var name: String, var type: String, var quantity : Int, var measurement : String)
{

}
