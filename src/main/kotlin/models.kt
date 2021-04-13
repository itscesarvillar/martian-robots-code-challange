data class Point (val x: Int, val y: Int)
enum class Orientation { N, S, E, W }
data class Position (val point: Point, val orientation: Orientation)
enum class Instruction { L, R, F }
data class Input(val upperRightGridPoint: Point, val robotsSequence: List<Pair<Position, List<Instruction>>>)
