# GenerateChecks

GenerateChecks is a small JavaFX application that generates four time-based checkpoints throughout each hour. The checkpoints are spaced approximately 10–20 minutes apart and are calculated using the local system time.

This started as a small utility to solve a practical problem I was running into at work, while also giving me an opportunity to practice working with JavaFX and Java's time-based APIs.

# Features

- Generates 4 checkpoints for each hour

- Spaces checkpoints approximately 10–20 minutes apart

- Uses the local system time to calculate the checkpoints

- Provides a simple JavaFX interface for viewing the generated checkpoints

# How It Works

The application uses the current local system time to determine when the next checkpoints should occur. The goal is to spread the four checkpoints throughout the hour rather than having them occur at fixed times.

The application relies on JavaFX's event-driven model rather than manually creating threads. The Timeline instances handle the repeated checks and UI updates at regular intervals.

# Future Improvements

I'd like to continue improving the application by adding:

- Local storage for tracking the number of checks remaining

- Local storage for upcoming checkpoints

- Automatically resetting the check count when a shift ends

- Additional improvements to the user interface and overall usability

# Built With

- Java — application logic

- JavaFX 24.0.1 — user interface

- Maven 4.x — project and dependency management

# How to Run

- Download the latest .zip file from the Releases section of the repository.

- Extract the files to your desired location.

- Run the .exe file, which should be named GenerateChecksApp.exe.

The application should launch and generate checkpoints based on your local system time.
