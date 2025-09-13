package com.example.medikit.ml
import android.content.Context
import android.graphics.Bitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class LesionClassifier(context: Context) {

    private val interpreter: Interpreter
    private val classes = listOf(
        "alergias", "cortaduras", "esguinces", "fracturas",
        "hematomas", "no_lesiones", "quemaduras", "raspaduras"
    )

    init {
        interpreter = Interpreter(loadModelFile(context))
    }

    private fun loadModelFile(context: Context): MappedByteBuffer {
        val assetFileDescriptor = context.assets.openFd("wound_model.tflite")
        val inputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    suspend fun classify(bitmap: Bitmap): Result = withContext(Dispatchers.Default) {
        val input = preprocess(bitmap)
        val output = Array(1) { FloatArray(classes.size) }

        interpreter.run(input, output)

        val confidences = output[0]
        val maxIndex = confidences.indices.maxByOrNull { confidences[it] } ?: -1
        val label = classes[maxIndex]
        val confidence = confidences[maxIndex]

        val severity = determineSeverity(label, confidence)
        val recommendations = generateRecommendations(label)

        Result(label, (confidence * 100).toInt(), severity, recommendations)
    }

    private fun preprocess(bitmap: Bitmap): ByteBuffer {
        val resized = Bitmap.createScaledBitmap(bitmap, 224, 224, true)
        val buffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3)
        buffer.order(ByteOrder.nativeOrder())

        val pixels = IntArray(224 * 224)
        resized.getPixels(pixels, 0, 224, 0, 0, 224, 224)

        for (pixel in pixels) {
            buffer.putFloat(((pixel shr 16 and 0xFF) / 255f))
            buffer.putFloat(((pixel shr 8 and 0xFF) / 255f))
            buffer.putFloat(((pixel and 0xFF) / 255f))
        }

        return buffer
    }

    private fun determineSeverity(label: String, confidence: Float): String {
        return when (label) {
            "fracturas", "quemaduras" -> "high"
            "esguinces", "hematomas", "cortaduras" -> "medium"
            "alergias", "raspaduras" -> "low"
            "no_lesiones" -> "low"
            else -> "low"
        }
    }

    private fun generateRecommendations(label: String): List<String> {
        return when (label) {
            "fracturas" -> listOf("Inmoviliza la zona", "Busca atención médica de inmediato")
            "quemaduras" -> listOf("Enfría la zona con agua", "No revientes ampollas", "Consulta a un médico")
            "cortaduras" -> listOf("Limpia la herida", "Aplica antiséptico", "Cubre con un vendaje estéril")
            "esguinces" -> listOf("Aplica hielo", "Mantén reposo", "Eleva la zona afectada")
            "hematomas" -> listOf("Aplica frío local", "Descansa", "Consulta si hay dolor persistente")
            "alergias" -> listOf("Lava con agua", "Evita el contacto con alérgenos", "Consulta si empeora")
            "raspaduras" -> listOf("Lava con agua y jabón", "Aplica una crema antibiótica", "Cubre si es necesario")
            else -> listOf("No se detecta una lesión grave")
        }
    }

    data class Result(
        val label: String,
        val confidence: Int,
        val severity: String,
        val recommendations: List<String>
    )
}
