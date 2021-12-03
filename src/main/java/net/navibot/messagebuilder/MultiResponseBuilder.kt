package net.navibot.messagebuilder

class MultiResponseBuilder(private val title: String, private val bullet: String, private val maxBullet: Int) :
    Builder {

    private val list : ArrayList<ResponseBuilder> = ArrayList()
    private var current = ResponseBuilder(title, bullet)
    private var count = 0

    override fun insertBullet(title: String): ResponseBuilder {
        return insertBullet(title, false)
    }

    override fun insertBullet(title: String, bold: Boolean): ResponseBuilder {
        if (++count >= maxBullet) {
            list.add(current)
            reset()
        }

        return current.insertBullet(title, bold)
    }

    override fun insertBullet(bullet: String, title: String): ResponseBuilder {
        if (++count >= maxBullet) {
            list.add(current)
            reset()
        }

        return current.insertBullet(bullet, title)
    }

    override fun insertDescription(text: String, tabs: Int): ResponseBuilder = current.insertDescription(text, tabs)

    override fun setDescription(text: String, tabs: Int): ResponseBuilder = current.insertDescription(text, tabs)

    override fun tab(amount: Int): String = current.tab(amount)

    fun get(): ArrayList<ResponseBuilder> {
        if (current.toString().lines().size >= 2) {
            list.add(current)
            reset()
        }

        return list
    }

    private fun reset() {
        current = ResponseBuilder(this.title, bullet)
        count = 0
    }
}