object Configs {
    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0
    private const val versionQualifier = "alpha1"

    private fun generateVersionCode(): Int {
        return versionMajor * 10000 + versionMinor * 100 + versionPatch
    }

    private fun generateVersionName(): String {
        return "$versionMajor.$versionMinor.${versionPatch}"
    }

    const val Id = "com.developersancho.jetrorty"
    val VersionCode = generateVersionCode()
    val VersionName = generateVersionName()
    const val MinSdk = 23
    const val TargetSdk = 32
    const val CompileSdk = 32
    const val AndroidJunitRunner = "androidx.test.runner.AndroidJUnitRunner"
    val FreeCompilerArgs = listOf(
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.FlowPreview",
        "-Xopt-in=kotlin.Experimental",
        "-Xjvm-default=all"
    )

    object Release {
        const val BaseUrl = "https://rickandmortyapi.com/api/"
        const val DbName = "RortyDb"
    }

    object Debug {
        const val BaseUrl = "https://rickandmortyapi.com/api/"
        const val DbName = "RortyDb"
    }
}