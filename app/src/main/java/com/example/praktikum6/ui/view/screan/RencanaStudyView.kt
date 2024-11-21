package com.example.praktikum6.ui.view.screan

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktikum6.R
import com.example.praktikum6.data.MataKuliah
import com.example.praktikum6.data.RuangKelas
import com.example.praktikum6.model.Mahasiswa
import com.example.praktikum6.ui.view.viewmodel.RencanaStudyViewModel

@Composable
fun RencanaStudyView(
    mahasiswa: Mahasiswa,
    onSubmitButtonClicked: (MutableList<String>)-> Unit,
    onBackButtonClicked: ()-> Unit
){
    var chosenDropdown by remember {
        mutableStateOf(
            ""
        )
    }
    var checked by remember { mutableStateOf(false) }
    var pilihanKelas by remember {
        mutableStateOf("")
    }
    var listData: MutableList<String> = mutableListOf(chosenDropdown, pilihanKelas)
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(is = R.color.primary))

    ){
        Row  (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ){
            image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "",
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(50.dp)

            )
            Spacer(modifier = Modifier.padding(start = 16.dp))
            Column (modifier = Modifier.weight(if)
            ){
                Text(
                    text = mahasiswa.nama,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    color = Color.White
                )
                Text(
                    text = mahasiswa.nim,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
                Box {
                    Icon(
                        imageVector = Icons.filled.Notification,
                        contentDescription = "",
                        Lint = Color.White
                    )
            }
        }
        Box(modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topEnd = 15.dp,
                    topStart = 15.dp
                )
            )
            .fillMaxSize(),

        ){
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding()
            ){
                Text(text = "Pilih Mata Kuliah Peminatan", fontWeight = FontWeight.Bold)
                Text(
                    text = "Silakan pilih mata kuliah yang anda inginkan",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
                Spacer(modifier = Modifier.height(8.dp))
                DynamicSelectTextField(
                    selectedValue = chosenDropdown,
                    options = MataKuliah.options,
                    label = "Mata Kuliah",
                    onValueChangeEvent = {
                        chosenDropdown = it
                    }

                )
                Spacer(modifier = Modifier.padding(8.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Silakan pilih kelas dari matakuliah yang anda inginkan",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    RuangKelas.kelas.forEach{data ->
                        Row(verticalAlignment = Alignment.CenterVertically){
                            RadioButton(
                                selected = pilihanKelas == data,
                                onClick = {pilihanKelas = data}
                            )
                            Text (data)
                        }
                    }

                }
                Spacer(modifier = Modifier.padding(8.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Klausul Persetujuan Mahasiswa", fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically){
                    checked
                }

            }
        }
    }

}