package com.embel.shop.data.local

import com.embel.shop.data.model.FooterLink
import com.embel.shop.data.model.SocialLink

object FooterData {

    val footerLinks1 = listOf(
        FooterLink("About Us", "about"),
        FooterLink("Careers", "about"),
        FooterLink("Affiliates", "about"),
        FooterLink("Blog", "blog"),
        FooterLink("Contact Us", "contact"),
    )

    val footerLinks2 = listOf(
        FooterLink("New Arrivals", "shop"),
        FooterLink("Accessories", "shop"),
        FooterLink("Men", "shop"),
        FooterLink("Women", "shop"),
        FooterLink("Shop All", "shop"),
    )

    val footerLinks3 = listOf(
        FooterLink("Customer Service", "contact"),
        FooterLink("My Account", "account_dashboard"),
        FooterLink("Store Locator", "store_location"),
        FooterLink("Legal & Privacy", "terms"),
        FooterLink("Contact", "contact"),
        FooterLink("Gift Card", "shop"),
    )

    val socialLinks = listOf(
        SocialLink("Facebook", "https://facebook.com", "facebook"),
        SocialLink("Twitter", "https://twitter.com", "twitter"),
        SocialLink("Instagram", "https://instagram.com", "instagram"),
        SocialLink("YouTube", "https://youtube.com", "youtube"),
        SocialLink("Pinterest", "https://pinterest.com", "pinterest"),
    )

    val languageOptions = listOf("English", "German", "French", "Swedish")
    val currencyOptions = listOf("$ USD", "£ GBP", "€ EURO")
}
