package com.example.tugasmahasiswa.ui.halamanTugas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasmahasiswa.R
import com.example.tugasmahasiswa.data.Tugas
import com.example.tugasmahasiswa.modelMahasiswa.PenyediaViewModelMahasiswa
import com.example.tugasmahasiswa.modelTugas.HomeTugasViewModel
import com.example.tugasmahasiswa.navigasi.DestinasiNavigasi
import com.example.tugasmahasiswa.navigasi.TugasToAppBar

object DestinasiHomeTugas: DestinasiNavigasi {
    override val route = "home_tugas"
    override val titleRes = R.string.home_tugas
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTugasScreen(
    navigateToTugasEntry: () -> Unit,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onDetailClick: (Int) -> Unit = {},
    viewModelTugas: HomeTugasViewModel = viewModel(factory = PenyediaViewModelMahasiswa.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TugasToAppBar(
                title = stringResource(DestinasiHomeTugas.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToTugasEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.entry_tugas)
                )
            }
        }
    ){innerPadding ->
        val uiStateTugas by viewModelTugas.homeTugasUiState.collectAsState()
        BodyHomeTugas(
            itemTugas = uiStateTugas.listTugas,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onTugasClick = onDetailClick
        )
    }
}

@Composable
fun BodyHomeTugas(
    itemTugas: List<Tugas>,
    modifier: Modifier = Modifier,
    onTugasClick: (Int) -> Unit = {}
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemTugas.isEmpty()){
            Text(
                text = stringResource(R.string.deskripsi_no_item),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListTugas(
                itemTugas = itemTugas,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = { onTugasClick(it.id) }
            )
        }
    }
}

@Composable
fun ListTugas(
    itemTugas : List<Tugas>,
    modifier: Modifier = Modifier,
    onItemClick: (Tugas) -> Unit
){
    LazyColumn(modifier = Modifier){
        items(items = itemTugas, key = {it.id}){person ->
            DataTugas(
                tugas = person,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(person) }
            )
        }
    }
}

@Composable
fun DataTugas(
    tugas: Tugas,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = tugas.namaTugas,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
                Text(
                    text = tugas.deadline,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = tugas.detailTugas,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}