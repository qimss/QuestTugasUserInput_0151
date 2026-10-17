package com.example.formpendaftaran

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormJobInput(modifier: Modifier = Modifier) {
    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var skillDipilih by remember { mutableStateOf(setOf<String>()) }

    var tampilPilihanTanggal by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var tampilNotifikasi by remember { mutableStateOf(false) }

    val opsiPengalaman = listOf(
        stringResource(R.string.exp_less_than_1),
        stringResource(R.string.exp_1_to_3),
        stringResource(R.string.exp_more_than_3)
    )

    val skill = listOf(
        stringResource(R.string.skill_android),
        stringResource(R.string.skill_uiux),
        stringResource(R.string.skill_web)
    )

    val isFormValid = namaDepan.isNotBlank() && namaBelakang.isNotBlank() &&
            email.isNotBlank() && tanggalLahir.isNotBlank() &&
            experience.isNotBlank() && skillDipilih.isNotEmpty()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.screen_background))
    ) {
        // Banner Gambar Atas
        Image(
            painter = painterResource(R.drawable.starrynight),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.banner_height))
        )

        // Card Utama
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = dimensionResource(R.dimen.card_top_offset)),
            shape = RoundedCornerShape(
                topStart = dimensionResource(R.dimen.card_radius),
                topEnd = dimensionResource(R.dimen.card_radius)
            ),
            colors = CardDefaults.elevatedCardColors(
                containerColor = colorResource(R.color.white)
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = dimensionResource(R.dimen.card_elevation)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(dimensionResource(R.dimen.card_padding)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.form_title),
                    color = colorResource(R.color.purple_title),
                    fontSize = with(LocalDensity.current) {
                        dimensionResource(R.dimen.title_size).toSp()
                    },
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(dimensionResource(R.dimen.spacing_small)))

                Text(
                    text = stringResource(R.string.form_subtitle),
                    color = colorResource(R.color.text_secondary),
                    fontSize = with(LocalDensity.current) {
                        dimensionResource(R.dimen.subtitle_size).toSp()
                    },
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(dimensionResource(R.dimen.spacing_large)))

                // Input Nama
                Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small))) {
                    OutlinedTextField(
                        value = namaDepan,
                        onValueChange = { namaDepan = it },
                        label = { Text(stringResource(R.string.hint_first_name))},
                        shape = RoundedCornerShape(dimensionResource(R.dimen.field_radius)),
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = namaBelakang,
                        onValueChange = { namaBelakang = it },
                        label = { Text(stringResource(R.string.hint_last_name)) },
                        shape = RoundedCornerShape(dimensionResource(R.dimen.field_radius)),
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(Modifier.height(dimensionResource(R.dimen.spacing_medium)))

                // Input Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(stringResource(R.string.hint_email)) },
                    shape = RoundedCornerShape(dimensionResource(R.dimen.field_radius)),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(dimensionResource(R.dimen.spacing_medium)))

                // Row Tanggal Lahir & Dropdown Experience
                Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small))) {
                    OutlinedTextField(
                        value = tanggalLahir,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text(stringResource(R.string.hint_birth_date)) },
                        trailingIcon = {
                            IconButton(onClick = { tampilPilihanTanggal = true }) {
                                Icon(Icons.Default.DateRange, stringResource(R.string.cd_calendar))
                            }
                        },
                        shape = RoundedCornerShape(dimensionResource(R.dimen.field_radius)),
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = experience,
                            onValueChange = { },
                            readOnly = true,
                            label = { Text(stringResource(R.string.hint_experience)) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                            shape = RoundedCornerShape(dimensionResource(R.dimen.field_radius)),
                            singleLine = true,
                            modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            opsiPengalaman.forEach { opsi ->
                                DropdownMenuItem(
                                    text = { Text(opsi) },
                                    onClick = {
                                        experience = opsi
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(dimensionResource(R.dimen.spacing_medium)))

                // Pilihan Skill
                Text(
                    text = stringResource(R.string.label_skill),
                    color = colorResource(R.color.text_primary),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    skill.forEach { item ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = item in skillDipilih,
                                onCheckedChange = { dicentang ->
                                    skillDipilih = if (dicentang) skillDipilih + item
                                    else skillDipilih - item
                                }
                            )
                            Text(item)
                        }
                    }
                }

                Spacer(Modifier.height(dimensionResource(R.dimen.spacing_large)))

                // Tombol Submit
                Button(
                    onClick = { tampilNotifikasi = true },
                    enabled = isFormValid,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.purple_title),
                        disabledContainerColor = colorResource(R.color.button_disabled),
                        disabledContentColor = colorResource(R.color.button_disabled_text)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.button_height))
                ) {
                    Text(
                        text = stringResource(R.string.btn_proceed),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        if (tampilNotifikasi) {
            val durasiNotifikasi = integerResource(R.integer.notif_duration).toLong()

            LaunchedEffect(tampilNotifikasi) {
                delay(durasiNotifikasi)
                tampilNotifikasi = false
            }

            ElevatedCard(
                shape = RoundedCornerShape(dimensionResource(R.dimen.notif_radius)),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = colorResource(R.color.black)
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = dimensionResource(R.dimen.notif_elevation)
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.notif_margin))
            ) {
                Column(
                    modifier = Modifier.padding(dimensionResource(R.dimen.notif_padding))
                ) {
                    Text(
                        text = stringResource(R.string.notif_title),
                        color = colorResource(R.color.purple_title),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(dimensionResource(R.dimen.spacing_small)))
                    Text(
                        text = stringResource(
                            R.string.notif_message,
                            namaDepan,
                            namaBelakang,
                            email,
                            tanggalLahir,
                            experience,
                            skillDipilih.joinToString(stringResource(R.string.skill_separator))
                        ),
                        color = colorResource(R.color.white)
                    )
                }
            }
        }
    }

    // Dialog DatePicker
    if (tampilPilihanTanggal) {
        val datePickerState = rememberDatePickerState()
        val formatTanggal = SimpleDateFormat(
            stringResource(R.string.date_format),
            Locale.getDefault()
        ).apply { timeZone = TimeZone.getTimeZone("UTC") }

        DatePickerDialog(
            onDismissRequest = { tampilPilihanTanggal = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let {
                            tanggalLahir = formatTanggal.format(Date(it))
                        }
                        tampilPilihanTanggal = false
                    }
                ) {
                    Text(stringResource(R.string.btn_ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { tampilPilihanTanggal = false }) {
                    Text(stringResource(R.string.btn_cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}