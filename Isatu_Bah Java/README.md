# Isatu Bah Basic Interactive GUI

## Project summary
A Java Swing desktop GUI built around the exact rubric components: `JFrame`, `JPanel`, `JLabel`, and `ImageIcon`. The app uses a pink/lavender visual palette, a circular IB icon, a constructor, and inheritance through `BaseWindow`.

## Requirements
- JDK 17 or later
- Maven 3.8+ (for the standard Maven build) or an IDE that supports Java projects

## Build and run
From this project folder:

```bash
mvn package
```

Run the main class `sl.edu.prog209.SwingAssignment` from IntelliJ IDEA or VS Code. The application window is 800 × 800, titled `Java Swing Assignment`, and closes via the window close control.

## Features
- **Change Text:** prompts for a new label value; cancel keeps the existing value and blank input is rejected.
- **Change Background:** toggles between Rose Twilight and Blush Daylight backgrounds.
- **Theme Colour:** uses Swing's `JColorChooser` to change the accent border/status colour.
- **ImageIcon:** loads `src/main/resources/assets/ib-application-icon.jpg`; a simple generated fallback is used if the resource is missing.

## Smoke test
Compile and run the test class in a graphical session. On Linux, with Xvfb installed:

```bash
mkdir -p target/classes target/test-classes
javac -d target/classes src/main/java/sl/edu/prog209/*.java
cp -R src/main/resources/* target/classes/
javac -cp target/classes -d target/test-classes src/test/java/sl/edu/prog209/SwingAssignmentSmokeTest.java
xvfb-run -a java -cp target/classes:target/test-classes sl.edu.prog209.SwingAssignmentSmokeTest
```
