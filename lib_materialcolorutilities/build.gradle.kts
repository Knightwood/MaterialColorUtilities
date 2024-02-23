plugins {
    id("java-library")
    id("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
//    withJavadocJar()
}
dependencies {
    implementation("androidx.annotation:annotation:1.7.1")
    implementation("com.google.errorprone:error_prone_annotations:2.24.0")
}
publishing {
    publications {
        create<MavenPublication>("release") {
                groupId = "com.github.knightwood"
                artifactId = "materialcolorutilities"
                version = rootProject.ext["version"].toString()
            from(components.findByName("java"))
        }
    }
}
