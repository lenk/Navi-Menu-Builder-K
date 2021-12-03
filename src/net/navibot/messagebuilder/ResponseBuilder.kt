package net.navibot.messagebuilder

class ResponseBuilder(title: String, private val bulletPoint: String) : Builder {
    private val builder: StringBuilder = StringBuilder("$title\n\n")

    private val bold = "𝗮 𝗯 𝗰 𝗱 𝗲 𝗳 𝗴 𝗵 𝗶 𝗷 𝗸 𝗹 𝗺 𝗻 𝗼 𝗽 𝗾 𝗿 𝘀 𝘁 𝘂 𝘃 𝘄 𝘅 𝘆 𝘇 𝗔 𝗕 𝗖 𝗗 𝗘 𝗙 𝗚 𝗛 𝗜 𝗝 𝗞 𝗟 𝗠 𝗡 𝗢 𝗣 𝗤 𝗥 𝗦 𝗧 𝗨 𝗩 𝗪 𝗫 𝗬 𝗭".split(" ")
    private val alpha = (('a'..'z') + ('A' .. 'Z'))
    private val maxCharactersPerLine = 33

    /**
     * inset a bullet point with [title] that's bold using the custom [bulletPoint] provided,
     * if you don't want a bold bullet point, use [insertBullet]
     */
    override fun insertBullet(title: String, bold: Boolean): ResponseBuilder {

        builder.append("$bulletPoint ").append(
            if (bold)
                (title.map { c -> this.bold.getOrNull(alpha.indexOf(c)) ?: c })
            else
                title
        ).appendLine()

        return this
    }

    /**
     * insert a bullet point with the [title] using the custom [bulletPoint] provided,
     * if you want to create a bold bullet point, use [insertBullet]
     */
    override fun insertBullet(title: String): ResponseBuilder {
        builder.append("$bulletPoint $title").appendLine()
        return this
    }

    /**
     * insert a bullet point with a different bullet point from default
     */
    override fun insertBullet(bullet: String, title: String): ResponseBuilder {
        builder.append("$bullet $title").appendLine()
        return this
    }

    /**
     * insert [text] description and format it based on the provided [tabs],
     * if you don't want to format the description use [setDescription]
     */
    override fun insertDescription(text: String, tabs: Int): ResponseBuilder {
        val words = text.split(" ")
        val tab = tab(tabs)

        val formatted = StringBuilder()
        val line = StringBuilder(tab)

        for (index in (0..words.lastIndex)) {
            val word = words[index] // get current word

            if (index == 0) { // check if it's just the start of the iteration
                line.append("$word ") // append the first word
                continue
            }

            // check if the line length has passed the maximum or if the line + next word length surpass the max character
            // if it surpasses, append the current line and reset for the next word
            if (line.length >= maxCharactersPerLine || line.length + word.length >= maxCharactersPerLine) {
                formatted.append(line).appendLine() // append line to formatted message
                line.setLength(0) // reset line builder
                line.append(tab) // append necessary tabbing / padding
            }

            // append the next word
            line.append("$word ")
        }

        formatted.append(line).appendLine().appendLine()
        builder.append(formatted)
        return this
    }

    /**
     * set description based on [text] with [tabs] tabbing without formatting it,
     * if you'd like to format the message use [insertDescription]
     */
    override fun setDescription(text: String, tabs: Int): ResponseBuilder {
        builder.append(tab(tabs)).append(text).appendLine()
        return this
    }

    /**
     * create a specified [amount] of tabs
     */
    override fun tab(amount: Int): String {
        return " ".repeat(amount)
    }

    /**
     * build the [ResponseBuilder] to a readable/formatted string
     */
    override fun toString(): String {
        return builder.toString().trim { it <= ' ' }
    }
}