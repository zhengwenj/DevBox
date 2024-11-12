import org.apache.tools.ant.filters.EscapeUnicode
import org.jetbrains.changelog.Changelog
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

plugins {
    id("java")
    // 应用 Kotlin 插件
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    // 应用 IntelliJ 插件
    id("org.jetbrains.intellij") version "1.17.4"
    id("org.jetbrains.changelog") version "2.2.1"
}


fun properties(key: String) = providers.gradleProperty(key)
fun environment(key: String) = providers.environmentVariable(key)

fun dateValue(pattern: String): String =
    LocalDate.now(ZoneId.of("Asia/Shanghai")).format(DateTimeFormatter.ofPattern(pattern))

val autoSnapshotVersionEnv: Provider<Boolean> = environment("AUTO_SNAPSHOT_VERSION").map(String::toBoolean).orElse(true)
val snapshotVersionPart: Provider<String> = properties("autoSnapshotVersion")
    .map(String::toBoolean)
    .orElse(false)
    .zip(autoSnapshotVersionEnv, Boolean::and)
    .map { if (it) "SNAPSHOT.${dateValue("yyMMdd")}" else "" }
val preReleaseVersion: Provider<String> = properties("pluginPreReleaseVersion")
    .flatMap { prv -> prv.takeIf(String::isNotBlank)?.let { provider { it } } ?: snapshotVersionPart }
val preReleaseVersionPart: Provider<String> = preReleaseVersion.map { prv ->
    prv.takeIf(String::isNotBlank)?.let { "-$it" } ?: ""
}
val buildMetadataPart: Provider<String> = properties("pluginBuildMetadata")
    .map { part -> part.takeIf(String::isNotBlank)?.let { "+$it" } ?: "" }
val pluginVersion: Provider<String> = properties("pluginMajorVersion").zip(preReleaseVersionPart, String::plus)
val fullPluginVersion: Provider<String> = pluginVersion.zip(buildMetadataPart, String::plus)
val publishChannel: Provider<String> = preReleaseVersion.map { preReleaseVersion: String ->
    preReleaseVersion.takeIf(String::isNotEmpty)?.split(".")?.firstOrNull()?.lowercase() ?: "default"
}

extra["pluginVersion"] = pluginVersion.get()
extra["pluginPreReleaseVersion"] = preReleaseVersion.get()
extra["fullPluginVersion"] = fullPluginVersion.get()
extra["publishChannel"] = publishChannel.get()

group = properties("pluginGroup").get()
version = fullPluginVersion.get()

repositories {
    maven {
        url = uri("https://maven.aliyun.com/nexus/content/groups/public/")
    }
    mavenCentral()
}

dependencies {
    implementation("cn.hutool:hutool-all:5.8.32")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.2.6")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
}
changelog {
    header = provider { "${version.get()} (${dateValue("yyyy/MM/dd")})" }
    groups.empty()
    repositoryUrl = properties("pluginRepositoryUrl")
}
tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
    // 将第三方依赖打包到 JAR 中
    jar {
        // 将运行时类路径中的所有依赖添加到 JAR
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    }
    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("242.*")
        pluginDescription = projectDir.resolve("DESCRIPTION.md").readText()
        val changelog = project.changelog

        // 更新日志
        changeNotes = pluginVersion.map { pluginVersion ->
            with(changelog) {
                renderItem(
                    (getOrNull(pluginVersion) ?: getUnreleased())
                        .withHeader(false)
                        .withEmptySections(false),
                    Changelog.OutputType.HTML
                )
            }
        }
    }

    signPlugin {
        // 设置证书链
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        // 设置私钥
        privateKey.set(System.getenv("PRIVATE_KEY"))
        // 设置私钥密码
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(System.getenv("PUBLISH_TOKEN"))
        channels = publishChannel.map { listOf(it) }
    }

    processResources {
        filesMatching("**/*.properties") {
            filter(EscapeUnicode::class)
        }
    }

}

