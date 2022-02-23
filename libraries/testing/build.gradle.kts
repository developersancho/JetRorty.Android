plugins {
    id("commons.android-library")
}

dependencies {
    implementation(Deps.Network.Moshi)
    implementation(Deps.Network.Retrofit)
    implementation(Deps.Network.RetrofitMoshi)
    implementation(Deps.Network.OkhttpInterceptor)

    api(Deps.Network.OkhttpMock)
    api(Deps.Test.Coroutine)
    api(Deps.Test.Robolectric)
    api(Deps.Test.Assertj)
    api(Deps.Test.TestCore)
    api(Deps.Test.TestRunner)
    api(Deps.Test.TestRules)
    api(Deps.Test.Hamcrest)
    api(Deps.Test.Json)
    api(Deps.Test.Turbine)
    api(Deps.Test.Truth)
    api(Deps.Test.Mockk)
}