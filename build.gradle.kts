plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.61"
    `java-library`
}

repositories {
    jcenter()
}


//IMPORTANT - junit4 is EXCLUDED from project
configurations.compileClasspath.get().exclude(group = "junit", module = "junit")
configurations.runtimeClasspath.get().exclude(group = "junit", module = "junit")
configurations.testCompileClasspath.get().exclude(group = "junit", module = "junit")
configurations.testRuntimeClasspath.get().exclude(group = "junit", module = "junit")

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

// VERSION 5.5.2 - successfully execute test
//    val junitVersion = "5.5.2"
//    val junitPlatformLauncher = "1.5.2"

// VERSION 5.6.0 - DO NOT EXECUTE TEST!
    val junitVersion = "5.6.0"
    val junitPlatformLauncher = "1.6.0"


    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api", version = junitVersion)
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = junitVersion)
    testImplementation(group = "org.junit.platform", name = "junit-platform-launcher", version = junitPlatformLauncher)
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-params", version = junitVersion)

    // in one of our modules this dependency was transitive dependency of
    // testImplementation(group = "org.springframework.boot", name = "spring-boot-starter-test", version = Version.springBoot)
    testImplementation("org.junit.vintage:junit-vintage-engine:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
