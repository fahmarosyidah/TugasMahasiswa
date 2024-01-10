package com.example.tugasmahasiswa.ui.halamanMahasiswa

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasmahasiswa.R
import com.example.tugasmahasiswa.modelMahasiswa.EditMahasiswaViewModel
import com.example.tugasmahasiswa.modelMahasiswa.PenyediaViewModelMahasiswa
import com.example.tugasmahasiswa.navigasi.DestinasiNavigasi
import com.example.tugasmahasiswa.navigasi.MahasiswaToAppBar
import kotlinx.coroutines.launch

object ItemEditMahasiswaDestination : DestinasiNavigasi {
    override val route = "item_edit_mahasiswa"
    override val titleRes = R.string.edit_mahasiswa
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditMahasiswaScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditMahasiswaViewModel = viewModel(factory = PenyediaViewModelMahasiswa.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            MahasiswaToAppBar(
                title = stringResource(ItemEditMahasiswaDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryMahasiswaBody(
            uiStateMahasiswa = viewModel.mahasiswaUiState,
            onMahasiswaValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateMahasiswa()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}