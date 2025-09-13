package com.example.medikit.viewmodels
import android.app.Application
import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.medikit.ml.LesionClassifier
import kotlinx.coroutines.launch

class WoundViewModel(application: Application) : AndroidViewModel(application) {

    private val classifier = LesionClassifier(application.applicationContext)

    var imageBitmap = mutableStateOf<Bitmap?>(null)
    var woundType = mutableStateOf("")
    var confidence = mutableStateOf(0)
    var severity = mutableStateOf("")
    var recommendations = mutableStateOf<List<String>>(emptyList())

    fun analyzeImage(bitmap: Bitmap, onResultReady: () -> Unit) {
        viewModelScope.launch {
            val result = classifier.classify(bitmap)
            imageBitmap.value = bitmap
            woundType.value = result.label
            confidence.value = result.confidence
            severity.value = result.severity
            recommendations.value = result.recommendations
            onResultReady()
        }
    }

    fun reset() {
        imageBitmap.value = null
        woundType.value = ""
        confidence.value = 0
        severity.value = ""
        recommendations.value = emptyList()
    }
}