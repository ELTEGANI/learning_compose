package com.example.test.data

data class Items(
    var name:String,
    var price:String
){
   companion object{
       var sortByName = Comparator<Items>{item1,item2 -> item1.name.compareTo(item2.name) }
   }
}
