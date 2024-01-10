package com.example.tugasmahasiswa.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "tblMahasiswa")
data class Mahasiswa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val nim: String,
    val semester: String
)

@Entity(
    tableName = "tblTugas"
//    foreignKeys = [
//        ForeignKey(
//            entity = Mahasiswa::class,
//            parentColumns = ["id"],
//            childColumns = ["mahasiswaId"],
//            onDelete = ForeignKey.CASCADE, // Opsional: atur perilaku penghapusan jika mahasiswa dihapus
//            onUpdate = ForeignKey.CASCADE
//        )
//    ],
)
data class Tugas(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mahasiswaId: Int,
    val namaTugas: String,
    val detailTugas: String,
    val deadline: String
)
