package com.example.praktikum6.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.praktikum6.model.Mahasiswa
import com.example.praktikum6.model.RencanaStudi
import com.example.praktikum6.ui.view.screan.MahasiswaFormView
import com.example.praktikum6.ui.view.screan.RencanaStudyView
import com.example.praktikum6.ui.view.screan.SplashScreenView
import com.example.praktikum6.ui.view.screan.TampilScreenView
import com.example.praktikum6.ui.view.viewmodel.MahasiswaViewModel
import com.example.praktikum6.ui.view.viewmodel.RencanaStudyViewModel

enum class Halaman{
    Splash,
    Mahasiswa,
    Matakuliah,
    Tampil
}
@Composable
fun MahasiswaApp(
    modifier: Modifier = Modifier,
    mahasiswaViewModel: MahasiswaViewModel = viewModel(),
    krsViewModel: RencanaStudyViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    val mahasiswaUiState = mahasiswaViewModel.mahasiswaUiState.collectAsState().value
    val krsUiState = krsViewModel.krsStateUi.collectAsState().value
    NavHost(
        navController = navController,
        startDestination = Halaman.Splash.name,
        modifier = Modifier.padding()
    ) {
        composable(route = Halaman.Splash.name){
            SplashScreenView (onMulaiButton ={
                navController.navigate(
                    Halaman.Mahasiswa.name)
            })

        }
        composable(route = Halaman.Mahasiswa.name){
            MahasiswaFormView(
                onSimpantButtonClicked = {
                    mahasiswaViewModel.saveDataMahasiswa(it)
                    navController.navigate(Halaman.Matakuliah.name)
                },
                onBackButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Halaman.Matakuliah.name){
            RencanaStudyView(
                mahasiswa = mahasiswaUiState,
                onSubmitButtonClicked = { krsViewModel.saveDataKRS(it)
                                        navController.navigate(Halaman.Tampil.name)},
                onBackButtonClicked = { navController.popBackStack()}

            )
        }
        composable(route = Halaman.Tampil.name) {
            TampilScreenView(
                mahasiswa = mahasiswaUiState,
                rencanaStudi = krsUiState,
                onBackButton = {
                    navController.navigate(Halaman.Splash.name) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}