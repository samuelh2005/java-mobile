plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(libs.guava)
    implementation(libs.netty.all)
    implementation(libs.slf4j.api)
    implementation(libs.logback.classic)
    implementation(project(":gsup-protocol"))
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "me.samuelh2005.java_mobile.msc.App"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
