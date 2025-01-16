import kotlinx.serialization.Serializable

@Serializable
sealed class APIResult<out T> {
    @Serializable
    data class Success<out T>(val data: T) : APIResult<T>()

    @Serializable
    data class Error(
        val message: String,
        val code: Int? = null // Include HTTP status code if applicable
    ) : APIResult<Nothing>()
}
