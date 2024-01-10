package com.example.tugasmahasiswa.modelTugas

//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.createSavedStateHandle
//import androidx.lifecycle.viewmodel.CreationExtras
//import androidx.lifecycle.viewmodel.initializer
//import androidx.lifecycle.viewmodel.viewModelFactory
//import com.example.tugasmahasiswa.AplikasiTugasMahasiswa
//import com.example.tugasmahasiswa.modelMahasiswa.aplikasiTugasMahasiswa
//
//object PenyediaViewModelTugas {
//    val Factory = viewModelFactory {
//        initializer {
//            HomeTugasViewModel(
//                createSavedStateHandle(),
//                aplikasiTugasMahasiswa().container.repositoryTugas)
//        }
//        initializer {
//            EntryTugasViewModel(aplikasiTugasMahasiswa().container.repositoryTugas)
//        }
//        initializer {
//            DetailTugasViewModel(
//                createSavedStateHandle(),
//                aplikasiTugasMahasiswa().container.repositoryTugas
//            )
//        }
//        initializer {
//            EditTugasViewModel(
//                createSavedStateHandle(),
//                aplikasiTugasMahasiswa().container.repositoryTugas
//            )
//        }
//    }
//}

//fun CreationExtras.aplikasiTugasMahasiswa(): AplikasiTugasMahasiswa =
//    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiTugasMahasiswa)