package com.embel.shop.data.local

import com.embel.shop.data.model.Testimonial

object TestimonialData {

    val testimonials = listOf(
        Testimonial(
            id = 1,
            text = "I am so happy with the quality of the furniture I bought from Embel. The craftsmanship is exceptional and the delivery was fast.",
            author = "Sarah Johnson",
            role = "Interior Designer"
        ),
        Testimonial(
            id = 2,
            text = "The customer service at Embel is outstanding. They helped me choose the perfect pieces for my new apartment. Highly recommend!",
            author = "Michael Chen",
            role = "Homeowner"
        ),
        Testimonial(
            id = 3,
            text = "Beautiful designs and great value for money. Embel has become my go-to store for all home furnishing needs.",
            author = "Emma Williams",
            role = "Blogger"
        ),
        Testimonial(
            id = 4,
            text = "I've been a loyal customer for years. The quality and style of Embel products never disappoint. Love their new collection!",
            author = "David Brown",
            role = "Architect"
        ),
    )
}
