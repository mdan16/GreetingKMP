import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QiitaItem (
    @SerialName("title")
    val title: String
)