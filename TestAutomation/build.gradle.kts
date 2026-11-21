
plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // UI Automation
    testImplementation("org.seleniumhq.selenium:selenium-java:4.27.0")

    // TestNG
    testImplementation("org.testng:testng:7.11.0")

    // API Automation
    testImplementation("io.rest-assured:rest-assured:5.5.0")

    // Assertions
    testImplementation("org.hamcrest:hamcrest:3.0")

    // Jackson
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
}

tasks.test {
    useTestNG()
}