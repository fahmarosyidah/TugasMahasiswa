package com.example.tugasmahasiswa.ui.halamanTugas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasmahasiswa.R
import com.example.tugasmahasiswa.modelMahasiswa.PenyediaViewModelMahasiswa
import com.example.tugasmahasiswa.modelTugas.DetailTugas
import com.example.tugasmahasiswa.modelTugas.EntryTugasViewModel
import com.example.tugasmahasiswa.modelTugas.UIStateTugas
import com.example.tugasmahasiswa.navigasi.DestinasiNavigasi
import com.example.tugasmahasiswa.navigasi.TugasToAppBar
import kotlinx.coroutines.launch

object DestinasiEntryTugas: DestinasiNavigasi {
    override val route = "item_entry_tugas"
    override val titleRes = R.string.entry_tugas
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryTugasScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryTugasViewModel = viewModel(factory = PenyediaViewModelMahasiswa.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TugasToAppBar(
                title = stringResource(DestinasiEntryTugas.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior
            )
        }
    ){innerPadding ->
        EntryTugasBody(
            uiStateTugas = viewModel.uiStateTugas,
            onTugasValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveTugas()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )

    }
}

@Composable
fun EntryTugasBody(
    uiStateTugas: UIStateTugas,
    onTugasValueChange: (DetailTugas) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ){
        FormInputTugas(
            detailTugas = uiStateTugas.detailTugas,
            onValueChange = onTugasValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateTugas.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.btn_submit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputTugas(
    detailTugas: DetailTugas,
    modifier: Modifier = Modifier,
    onValueChange: (DetailTugas) -> Unit = {},
    enabled: Boolean = true
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ){
        OutlinedTextField(
            value = detailTugas.namaTugas,
            onValueChange = {onValueChange(detailTugas.copy(namaTugas = it))},
            label = { Text(stringResource(R.string.namaTugas)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailTugas.detailTugas,
            onValueChange = {onValueChange(detailTugas.copy(detailTugas = it))},
            label = { Text(stringResource(R.string.detail_tugas)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailTugas.deadline,
            onValueChange = {onValueChange(detailTugas.copy(deadline = it))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(R.string.deadline)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled){
            Text(
                text = stringResource(R.string.required_field),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
        Divider(
            thickness = dimensionResource(R.dimen.padding_small),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
        )
    }
}