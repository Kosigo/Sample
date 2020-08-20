import kotlin.reflect.full.memberProperties

private const val FEATURE_PREFIX = ":feature_"

@Suppress("unused")
object ModuleDependency {
    const val APP = ":app"
    const val LIBRARY_BASE = ":libraries:library_base"
    const val LIBRARY_SETTINGS = ":libraries:settings"
    const val LIBRARY_RESTAURANTS = ":libraries:restaurants"
    const val LIBRARY_BESTSELLERS = ":libraries:bestsellers"
    const val LIBRARY_REVIEWS = ":libraries:reviews"

    fun getAllModules() = ModuleDependency::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()

    fun getDynamicFeatureModules() = getAllModules()
        .filter { it.startsWith(FEATURE_PREFIX) }
        .toSet()
}
