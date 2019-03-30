import Kingdom.*

abstract class ListElement(val name: String, val kingdom: Kingdom, val firstVisit: Boolean) {
    var crossedOff: Boolean = false
        private set

    fun toggleStrike() {
        crossedOff = !crossedOff
    }

    override fun toString(): String {
        var name: String = this.name

        if (crossedOff) {
            name = "<html><strike>$name</strike></html>"
        }
        return name
    }

    /**
     * This stores sorting functions (Comparators)
     */
    companion object {
        fun compareByVisit(m1: ListElement, m2: ListElement): Int {
            val comparands = arrayOf(m1, m2)
            val comparandBucket = intArrayOf(0, 0)
            for (i in comparands.indices) {
                val m = comparands[i]

                if (m.firstVisit) {
                    comparandBucket[i] = when (m.kingdom) {
                        Cascade -> 0
                        Sand -> 1
                        Lake -> 2
                        Wooded -> 3
                        Cloud -> 4
                        Lost -> 5
                        Metro -> 6
                        Snow -> 7
                        Seaside -> 8
                        Luncheon -> 9
                        Ruined -> 10
                        Bowser -> 11
                        Moon -> 12
                        Mushroom -> 13
                        else -> throw IllegalStateException()
                    }
                } else {
                    comparandBucket[i] = when (m.kingdom) {
                        Snow -> 14
                        Cascade -> 15
                        Bowser -> 16
                        Seaside -> 17
                        Lake -> 18
                        Sand -> 19
                        Metro -> 20
                        Wooded -> 21
                        Luncheon -> 22
                        Cap -> 23
                        Cloud -> 24
                        Lost -> 25
                        Ruined -> 26
                        Moon -> 27
                        DarkSide -> 28
                        Achievements -> 29
                        else -> throw IllegalStateException()
                    }
                }
            }

            if (comparandBucket[0] < comparandBucket[1])
                return -1
            if (comparandBucket[0] > comparandBucket[1])
                return 1
            return if (Lists.elements.indexOf(m1) < Lists.elements.indexOf(m2)) -1 else 1
        }

        fun compareByList(first: ListElement, second: ListElement): Int {
            return Integer.compare(Lists.elements.indexOf(first), Lists.elements.indexOf(second))
        }
    }
}

class Moon(
    name: String,
    kingdom: Kingdom,
    fV: Boolean,
    val worldState: String = "init",
    val probability: Double = 1.0,
    val prereqs: List<String> = emptyList(),
    /** if this moon is an achievement, this list will be [tag, level, countRequired] */
    val tags: List<Tags> = emptyList(),
    val captures: List<Captures> = emptyList()
) : ListElement(name, kingdom, fV) {

    override fun toString(): String {
        var name: String = this.name + tags[2]

        if (crossedOff) {
            name = "<html><strike>$name</strike></html>"
        }
        return name
    }
}

class NecessaryAction(name: String, kingdom: Kingdom, fV: Boolean, val triggers: Array<String>) :
    ListElement(name, kingdom, fV)

class Separator(kingdom: Kingdom, fV: Boolean) : ListElement("", kingdom, fV) {
    override fun toString(): String {
        val out: String = kingdom.toString() + if (firstVisit) 1 else 2
        val circumfixSize: Int = (20 - out.length) / 2

        return "-".repeat(circumfixSize) + out + "-".repeat(circumfixSize)
    }
}
