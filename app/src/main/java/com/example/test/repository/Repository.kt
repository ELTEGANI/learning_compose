package com.example.test.repository

import android.content.Context
import com.example.test.data.Items
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Repository @Inject constructor(@ApplicationContext var context: Context) {


    fun retrieveJsonFromFile():List<Items>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val listType = Types.newParameterizedType(List::class.java,Items::class.java)
        val adapter: JsonAdapter<List<Items>> = moshi.adapter(listType)
        val jsonFile = "data.json"
        val carsJson = context.assets.open(jsonFile).bufferedReader().use{
            it.readText()
        }
        return adapter.fromJson(carsJson)
    }
}