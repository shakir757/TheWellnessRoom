package com.shakir.thewellnessroom.check

class ScanCheckInteractor {

    val dictionary: MutableList<String> = arrayListOf()
    // t=20201213T194500 & s=54.00 & fn=9289440300637432 & i=17173 & fp=4107152669 & n=1

    fun makeDataDictionary(rawData: String): List<String> {
        val components = rawData.split("&")
        for (i in components){
            val keyValue = i.split("=")
            dictionary.add(keyValue[1])
        }

        return dictionary
    }

    fun makeDocDateTime(): String{
        return if (dictionary[0].length > 13){
            dictionary[0].take(13)
        } else {
            dictionary[0]
        }
    }
}