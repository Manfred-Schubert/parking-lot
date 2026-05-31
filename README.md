# Object-Oriented Parking Management System (Kotlin)

An idiomatic, command-line state engine that simulates real-time parking lot allocations, query indexing, and state mutations using advanced Kotlin scope constructs.

This application was developed as a milestone project within the **Hyperskill / JetBrains Academy** Kotlin Core curriculum.

- **Course Project Page:** https://hyperskill.org/projects/75
- **My Hyperskill Profile:** https://hyperskill.org/profile/629496713

---

## Key Features
- **Scope Functions & Receiver Type DSLs:** Engineered a custom execution block (`withParkingLot`) leveraging functions with receiver types (`ParkingLot.() -> Unit`) to elegantly bridge the command-line manager and the state container with centralized null-safety checks.
- **Defensive Data Validation:** Utilized Kotlin `init` blocks and `require` contracts to guarantee structural data integrity upon object instantiation (preventing illegal malformed string formats inside domain models).
- **Functional Query Indexing:** Maximized Kotlin's Collection API (`mapIndexedNotNull`, `filterNotNull`, `indexOfFirst`) to seamlessly handle multi-criteria lookups (e.g., tracking data arrays dynamically by registration tags or color states) with zero imperative overhead.

## Tech Stack
- Kotlin (JVM)
- Kotlin Collections API

## How to Run
1. Open the project root folder in IntelliJ IDEA.
2. Run the `main()` function inside `Main.kt`.
3. Input commands such as `create [size]`, `park [reg_num] [color]`, `status`, or `spot_by_color [color]` directly into the terminal window.