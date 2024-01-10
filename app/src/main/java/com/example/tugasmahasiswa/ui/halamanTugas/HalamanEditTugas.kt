package com.example.tugasmahasiswa.ui.halamanTugas

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasmahasiswa.R
import com.example.tugasmahasiswa.modelMahasiswa.PenyediaViewModelMahasiswa
import com.example.tugasmahasiswa.modelTugas.EditTugasViewModel
import com.example.tugasmahasiswa.navigasi.DestinasiNavigasi
import com.example.tugasmahasiswa.navigasi.TugasToAppBar
import kotlinx.coroutines.launch

object EditTugasDestination : DestinasiNavigasi {
    override val route = "item_edit_tugas"
    override val titleRes = R.string.edit_tugas
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditTugasScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditTugasViewModel = viewModel(factory = PenyediaViewModelMahasiswa.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TugasToAppBar(
                title = stringResource(EditTugasDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryTugasBody(
            uiStateTugas = viewModel.tugasUiState,
            onTugasValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateTugas()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}