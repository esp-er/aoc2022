package patriker.day20

import patriker.utils.*

import java.util.LinkedList


fun main(){
    val testInput = readInput("Day20_test")
    val input = readInput("Day20_input")

    solvePart1(testInput)
    println(solvePart1(input))
}


fun solvePart1(input: List<String>): Int{

    val encodedCoords = input.mapIndexed{idx, v -> idx to v.toInt()}

    val coords = LinkedList<Pair<Int,Int>>()

    encodedCoords.forEachIndexed(){ idx, p ->
        coords.add(p)
    }


    encodedCoords.forEach{
        var currIndex = coords.indexOf(it)
        var start = currIndex
        var end  = currIndex + coords[currIndex].second

        if (end < 0)
          end = Math.floorMod(end, coords.size - 1)

        if (end >= (coords.size - 1) )
          end = end % (coords.size - 1)


        if(coords[currIndex].second != 0) {
            if(end > start)
                coords.add(end + 1, it)
            else
                coords.add(end,it)
            val r =
                if(end > start)
                    coords.removeAt(start)
                else
                    coords.removeAt(start+1)
            check(r == it)
        }

    }

    var zeroidx = 0
    coords.forEachIndexed{ idx, p ->
        if(p.second ==0)
            zeroidx = idx
    }

    val a =  (zeroidx + 1000) % coords.size
    val b =  (zeroidx + 2000) % coords.size
    val c =  (zeroidx + 3000) % coords.size
    return coords[a].second + coords[b].second +  coords[c].second

}
