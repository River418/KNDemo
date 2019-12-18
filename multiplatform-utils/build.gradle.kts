import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.android.library")//引入android编译插件
    id("kotlin-multiplatform")//引入kn编译插件
    id("org.jetbrains.kotlin.native.cocoapods")
}

//此处配置Android公共库相关的能力，包括代码路径等
android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.2")
    defaultConfig {
        minSdkVersion(19)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    sourceSets {
        getByName("main").manifest.srcFile(project.projectDir.path + "/src/androidMain/AndroidManifest.xml")
        getByName("main").res.srcDirs(project.projectDir.path + "/src/androidMain/res/")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    dependencies{
        testImplementation("junit:junit:4.12")
        testImplementation("org.jetbrains.kotlin:kotlin-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
        androidTestImplementation("junit:junit:4.12")
        androidTestImplementation("org.jetbrains.kotlin:kotlin-test")
        androidTestImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    }
}

//kotlin-multiplatform插件的配置块
kotlin{

    // 根据 Xcode 环境变量选择 iOS 目标平台
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("iOS") {
//        binaries {
//            framework {
//                //配置iOS中的项目库名称,后续通过Framework/Pod引入时会用到
//                baseName = "multiplatform-logic"
//            }
//        }
    }

    //增加version，保证podspec正常生成​
    version="0.1-beta"

    cocoapods{
        summary="multiplatform-utils"
        homepage="https://github.com/River418/KNDemo/multiplatform-utils"
        pod("MMKV")
    }

    android()//关联Android编译

    //common目录为KN跨平台公共代码路径，及Android和iOS项目公用代码
    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
    }

    //为Android项目添加库依赖
    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("com.tencent:mmkv:1.0.23")
    }

    //为iOS项目添加库依赖，此处暂时留空。后面我们会用到
    sourceSets["iOSMain"].dependencies {

    }

    //common和iOS分别生命测试代码目录及添加相应依赖
    sourceSets["commonTest"].dependencies{
        implementation("org.jetbrains.kotlin:kotlin-test-common")
        implementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
    }
    sourceSets["iOSTest"].dependencies{
        implementation("org.jetbrains.kotlin:kotlin-test")
        implementation("org.jetbrains.kotlin:kotlin-test-junit")
    }

}

//创建一个task，用来将公共库打包成framework输出给iOS项目使用
val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    /// 根据 Xcode 构建设置的环境变量
    /// 为 iOS framework
    /// 选择正确的配置
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets
        .getByName<KotlinNativeTarget>("iOS")
        .binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)

    /// 生成一个有用的 ./gradlew 包装器并嵌入到 Java 路径
    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText("#!/bin/bash\n"
                + "export 'JAVA_HOME=${System.getProperty("java.home")}'\n"
                + "cd '${rootProject.rootDir}'\n"
                + "./gradlew \$@\n")
        gradlew.setExecutable(true)
    }
}

//为packForXcode这个task增加依赖关系
tasks.getByName("build").dependsOn(packForXcode)
