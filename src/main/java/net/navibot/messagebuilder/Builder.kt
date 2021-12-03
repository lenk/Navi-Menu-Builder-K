package net.navibot.messagebuilder

interface Builder {

    fun insertBullet(title: String, bold: Boolean): ResponseBuilder

    fun insertBullet(title: String): ResponseBuilder

    fun insertBullet(bullet: String, title: String): ResponseBuilder

    fun insertDescription(text: String, tabs: Int): ResponseBuilder

    fun setDescription(text: String, tabs: Int): ResponseBuilder

    fun tab(amount: Int): String
}