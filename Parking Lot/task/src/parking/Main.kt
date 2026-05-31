package parking

data class Car(val registrationNumber: String, val color: String) {
    init {
        require(!registrationNumber.contains(" "))
    }

    override fun toString(): String = "$registrationNumber $color"
}

class ParkingLot(size: Int) {
    val spots = MutableList<Car?>(size) { null }

    fun parkCar(car: Car): Int? {
        for ((index, parkedCar) in spots.withIndex()) {
            if (parkedCar == null) {
                spots[index] = car
                println("${car.color} car parked in spot ${index + 1}.")
                return index
            }
        }

        println("Sorry, the parking lot is full.")

        return null
    }

    fun leave(index: Int): Car? {
        val car = spots.getOrNull(index)
        if (car != null) {
            spots[index] = null
            println("Spot ${index + 1} is free.")
            return car
        } else {
            println("There is no car in spot ${index + 1}.")
            return null
        }
    }

    fun printStatus() {
        if (spots.all { it == null }) {
            println("Parking lot is empty.")
        } else {
            spots.forEachIndexed { index, car ->
                if (car != null) {
                    println("${index + 1} $car")
                }
            }
        }
    }

    @Suppress("FunctionName")
    fun reg_by_color(color: String) {
        val carsOfColor = spots
            .filterNotNull()
            .filter  { it.color.equals(color, true) }

        if (carsOfColor.isNotEmpty()) {
            val registrations = carsOfColor.map { it.registrationNumber }
            println(registrations.joinToString(", "))
        } else {
            println("No cars with color $color were found.")
        }
    }

    @Suppress("FunctionName")
    fun spot_by_color(color: String) {
        val spotsOfColor = spots
            .mapIndexedNotNull{ index, car ->
                if (car != null && color.equals(car.color, true)) {
                    index + 1
                } else {
                    null
                }
            }

        if (spotsOfColor.isNotEmpty()) {
            println(spotsOfColor.joinToString(", "))
        } else {
            println("No cars with color $color were found.")
        }
    }

    @Suppress("FunctionName")
    fun spot_by_reg(registrationNumber: String) {
        val index = spots.indexOfFirst { it != null && it.registrationNumber == registrationNumber }

        if (index != -1) {
            println(index + 1)
        } else {
            println("No cars with registration number $registrationNumber were found.")
        }
    }
}

class ParkingLotManager {

    private var parkingLot: ParkingLot? = null

    fun create(spotsCount: Int) {
        // check(parkingLot == null) { "Parking lot is already created." }

        parkingLot = ParkingLot(spotsCount)

        println("Created a parking lot with $spotsCount spots.")
    }

    fun withParkingLot(block: ParkingLot.() -> Unit) {
        val parkingLot = parkingLot

        if (parkingLot != null) {
            parkingLot.block()
        } else {
            println("Sorry, a parking lot has not been created.")
        }
    }

    fun run() {
        while (true) {
            val input = readln()
            if (input.isBlank()) continue

            val  tokens = input.split(" ")

            if (tokens.isEmpty()) continue

            val command = tokens[0]

            when (command) {
                "create" -> {
                    if (tokens.size != 2) continue

                    val spotsCount = tokens[1].toInt()

                    create(spotsCount)
                }
                "park" -> {
                    if (tokens.size != 3) continue

                    val registrationNumber = tokens[1]
                    val color = tokens[2]

                    withParkingLot {
                        parkCar(Car(registrationNumber, color))
                    }
                }

                "leave" -> {
                    if (tokens.size != 2) continue

                    val spotIndex = tokens[1].toInt() - 1

                    withParkingLot {
                        leave(spotIndex)
                    }
                }

                "status" -> {
                    withParkingLot {
                        printStatus()
                    }
                }

                "reg_by_color" -> {
                    if (tokens.size != 2) continue

                    val color = tokens[1]

                    withParkingLot {
                        reg_by_color(color)
                    }
                }

                "spot_by_color" -> {
                    if (tokens.size != 2) continue

                    val color = tokens[1]

                    withParkingLot {
                        spot_by_color(color)
                    }
                }

                "spot_by_reg" -> {
                    if (tokens.size != 2) continue

                    val registrationNumber = tokens[1]

                    withParkingLot {
                        spot_by_reg(registrationNumber)
                    }
                }

                "exit" -> break

                else -> continue
            }
        }
    }
}

fun main() {
    ParkingLotManager().run()
}
