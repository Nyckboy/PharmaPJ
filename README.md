## Pharmacy Management System

A simple console-based Pharmacy Management System built with Java and SQLite.

## 📝 Project Overview

This project helps manage a pharmacy by handling medicines, tracking inventory, and providing basic CRUD (Create, Read, Update, Delete) operations.

## 📦 How to Set Up the Project

1. Clone the Repository

Use the following command to clone the project:

git clone https://github.com/your-username/pharmacy-management.git
cd pharmacy-management

2. Add SQLite Library

The project uses the sqlite-jdbc library to connect to the SQLite database.

The required sqlite-jdbc JAR is already included in the lib folder, so no additional installation is needed.

Add the Library to Your IDE:

IntelliJ:

Right-click on the project → "Open Module Settings" → "Libraries" → Click + → Add the sqlite-jdbc JAR.

Eclipse:

Right-click on the project → "Properties" → "Java Build Path" → "Libraries" → "Add External JARs..." → Select the sqlite-jdbc JAR.

## 🚀 How to Run the Project

Compile the Project:

javac -cp lib/sqlite-jdbc-<version>.jar src/**/*.java

Run the Project:

java -cp lib/sqlite-jdbc-<version>.jar:src Main

## 📌 Features

Add new medicines.

View all medicines.

Track stock and expiry dates.

Simple console-based menu.

## ⚙️ Project Structure

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

pharmacy-management

┣ 📂 src

┃ ┣ 📂 models        # Data classes.

┃ ┣ 📂 database      # Database connection.

┃ ┣ 📂 controller    # Business logic.

┃ ┗ 📜 Main.java     # Entry point.

┣ 📂 database        # Contains the pharmacy.db file.

┣ 📂 lib             # Contains the sqlite-jdbc JAR.

┗ 📜 README.md       # Project instructions.


## 📧 Contact

If you have any questions, feel free to reach out at: abasside1234@gmail.com
