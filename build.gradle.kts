import org.gradle.kotlin.dsl.compileJava
import org.gradle.kotlin.dsl.invoke

@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/IDEA-262280

plugins {
    id("java-library")
    id("maven-publish")
    id("signing")
    alias(libs.plugins.lombok)
}

tasks.jar {
    enabled = false
}

subprojects {

    apply {
        plugin("java-library")
        plugin("maven-publish")
        plugin("signing")
        plugin(rootProject.libs.plugins.lombok.get().pluginId)
    }

    group = "org.powernukkitx.protocol"

    tasks {
        compileJava {
            options.encoding = Charsets.UTF_8.name();
            options.compilerArgs.add("-parameters")
        }
        test {
            useJUnitPlatform()
        }
    }

    dependencies {
        compileOnly(rootProject.libs.checker.qual)
    }

    java {
        //withJavadocJar()
        withSourcesJar()
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
                pom {
                    packaging = "jar"
                    url.set("https://github.com/CloudburstMC/Protocol")

                    scm {
                        connection.set("scm:git:git://github.com/PowerNukkitX/Protocol.git")
                        developerConnection.set("scm:git:ssh://github.com/PowerNukkitX/Protocol.git")
                        url.set("https://github.com/PowerNukkitX/Protocol")
                    }

                    licenses {
                        license {
                            name.set("The Apache Software License, Version 2.0")
                            url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    developers {
                        developer {
                            name.set("CloudburstMC Team")
                            organization.set("CloudburstMC")
                            organizationUrl.set("https://github.com/CloudburstMC")
                        }
                    }
                }
            }
        }
    
        repositories {
            maven {
                name = "pnx"
                url = uri("https://repo.powernukkitx.org/releases")
                credentials {
                    username = providers.gradleProperty("pnxUsername")
                        .orElse(providers.environmentVariable("PNX_REPO_USERNAME"))
                        .orNull
                    password = providers.gradleProperty("pnxPassword")
                        .orElse(providers.environmentVariable("PNX_REPO_PASSWORD"))
                        .orNull
                }
            }
        }
    }

    signing {
        if (System.getenv("PGP_SECRET") != null && System.getenv("PGP_PASSPHRASE") != null) {
            useInMemoryPgpKeys(System.getenv("PGP_SECRET"), System.getenv("PGP_PASSPHRASE"))
            sign(publishing.publications["maven"])
        }
    }
}
