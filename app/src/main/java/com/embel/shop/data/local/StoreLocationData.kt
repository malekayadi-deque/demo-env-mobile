package com.embel.shop.data.local

import com.embel.shop.data.model.StoreLocation

object StoreLocationData {

    val storeLocations = listOf(
        StoreLocation(
            id = 1,
            city = "London",
            address = "16 Pellatt Grove, Wood Green, London N22 5PL",
            country = "United Kingdom",
            phone = "(+44) 078 7533 6836",
            hours = "Mon - Sat: 8:00am - 5:00pm, Sun: 11:00am - 4:00pm",
            email = "london@embel.com",
            lat = 51.599,
            lng = -0.108
        ),
        StoreLocation(
            id = 2,
            city = "Istanbul",
            address = "Beyoglu, Istiklal Cad., Istanbul 34430",
            country = "Turkey",
            phone = "(+90) 541 322 1093",
            hours = "Mon - Sat: 9:00am - 6:00pm, Sun: 10:00am - 5:00pm",
            email = "istanbul@embel.com",
            lat = 41.034,
            lng = 28.977
        ),
        StoreLocation(
            id = 3,
            city = "New York",
            address = "200 Park Avenue, New York, NY 10166",
            country = "United States",
            phone = "(+1) 212 883 8075",
            hours = "Mon - Fri: 8:00am - 6:00pm, Sat-Sun: 10:00am - 4:00pm",
            email = "newyork@embel.com",
            lat = 40.754,
            lng = -73.976
        ),
    )
}
