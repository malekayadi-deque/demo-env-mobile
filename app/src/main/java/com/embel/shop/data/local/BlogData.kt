package com.embel.shop.data.local

import com.embel.shop.data.model.BlogPost

object BlogData {

    val blogs = listOf(
        BlogPost(
            id = 1,
            imgSrc = "images/home/demo9/post1.jpg",
            category = "FASHION",
            author = "Admin",
            date = "April 06, 2023",
            title = "Woman with Good Shoes is Never be Ugly Place",
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc vel tincidunt lacinia, nunc nisl aliquam nisl, vel aliquam nunc nisl vel nisl."
        ),
        BlogPost(
            id = 2,
            imgSrc = "images/home/demo9/post2.jpg",
            category = "STYLE",
            author = "Admin",
            date = "April 06, 2023",
            title = "5 Summer Fashion Trends You Need To Know",
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc vel tincidunt lacinia, nunc nisl aliquam nisl, vel aliquam nunc nisl vel nisl."
        ),
        BlogPost(
            id = 3,
            imgSrc = "images/home/demo9/post3.jpg",
            category = "TRENDS",
            author = "Admin",
            date = "April 06, 2023",
            title = "Fashion Essentials All Women Should Own",
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc vel tincidunt lacinia, nunc nisl aliquam nisl, vel aliquam nunc nisl vel nisl."
        ),
    )

    val blogCategories = listOf("ALL", "COMPANY", "FASHION", "STYLE", "TRENDS", "BEAUTY")
}
