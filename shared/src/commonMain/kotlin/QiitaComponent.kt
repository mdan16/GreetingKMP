import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class QiitaComponent {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    val TOKEN = ""
    fun getItems(): Flow<List<QiitaItem>> = flow {
        val items = httpClient.get("https://qiita.com/api/v2/items", block = {
            headers {
                append("Authorization", "Bearer $TOKEN")
            }
        }).body<List<QiitaItem>>()
        emit(items)
    }
}