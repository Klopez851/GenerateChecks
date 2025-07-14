# GenerateChecks
This JavaFX application generates 4 time-based checkpoints spaced approximately 10 to 20 minutes apart every hour. The checkpoints are calculated using the **local system time**

---

## ✨ Features

- ⏱️ Generates 4 checkpoints every hour
- 📍 Uses local time to determine spacing
---

## ⚠️ Known Issues

- 🕛 **Bug**: If the shift ends **after midnight**, the "time left before shift is over" is calculated incorrectly.
- 📊 **Progress Bar**: The progress bar is currently not functional — it does not accurately reflect the amount of time passed during the shift. This feature is under development.

---

## 🚧 Upcoming Improvements

- Fix midnight shift time calculation bug
- Implement accurate progress tracking over time
- Implement local storage of the number of checks left and resetting it after the shift ends

---

## 🛠️ Requirements
- Java 17.0.6 (JavaFX compatibility)
- Maven 3.x
- JavaFX SDK

---

## 📂 How to Run

1. Clone the repo
2.  Make sure that in project settings -> modules -> sources, your language level is set to 17 and ur using java sdk 23
4. Run the main file (genCheckApplication.java)
