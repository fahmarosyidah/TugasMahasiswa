package com.example.tugasmahasiswa.modelMahasiswa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tugasmahasiswa.data.Mahasiswa
import com.example.tugasmahasiswa.repository.RepositoryMahasiswa

class EntryMahasiswaViewModel(private val repositoryMahasiswa: RepositoryMahasiswa) : ViewModel() {
    var uiStateMahasiswa by mutableStateOf(UIStateMahasiswa())
        private set

    private fun validasiInput(uiState: DetailMahasiswa = uiStateMahasiswa.detailMahasiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && nim.isNotBlank() && semester.isNotBlank()
        }
    }

    fun updateUiState(detailMahasiswa: DetailMahasiswa) {
        uiStateMahasiswa = UIStateMahasiswa(
            detailMahasiswa = detailMahasiswa,
            isEntryValid = validasiInput(detailMahasiswa)
        )
    }

    suspend fun saveMahasiswa() {
        if (validasiInput()) {
            repositoryMahasiswa.insertMahasiswa(uiStateMahasiswa.detailMahasiswa.toMahasiswa())
        }
    }
}

/**
 * Mewakili Status Ui untuk Mahasiswa
 */
data class UIStateMahasiswa(
    val detailMahasiswa: DetailMahasiswa = DetailMahasiswa(),
    val isEntryValid: Boolean = false
)

data class DetailMahasiswa(
    val id: Int = 0,
    val nama: String = "",
    val nim: String = "",
    val semester: String = ""
)

/* Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya */
fun DetailMahasiswa.toMahasiswa(): Mahasiswa = Mahasiswa(
    id = id,
    nama = nama,
    nim = nim,
    semester = semester
)

fun Mahasiswa.toUiStateMahasiswa(isEntryValid: Boolean = false): UIStateMahasiswa = UIStateMahasiswa(
    detailMahasiswa = this.toDetailMahasiswa(),
    isEntryValid = isEntryValid
)

fun Mahasiswa.toDetailMahasiswa(): DetailMahasiswa = DetailMahasiswa(
    id = id,
    nama = nama,
    nim = nim,
    semester = semester
)