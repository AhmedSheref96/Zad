package com.el3sas.zad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.el3asas.zad.domain.models.DepartmentModel
import com.el3asas.zad.systemdesign.theme.ZadTheme
import com.el3sas.zad.ui.departmentTeachers.DepartmentTeachersRoute
import com.el3sas.zad.ui.departmentsHome.DepartmentRoutes
import com.el3sas.zad.ui.departmentsHome.DepartmentsAction
import com.el3sas.zad.ui.departmentsHome.DepartmentsRoute
import com.el3sas.zad.ui.departmentsHome.rememberDepartmentsCoordinator
import com.el3sas.zad.utils.navType
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZadTheme {
                Scaffold(
                    modifier =
                        Modifier
                            .fillMaxSize(),
                ) { innerPadding ->
                    val navController = rememberNavController()
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        startDestination = DepartmentRoutes.DepartmentsList,
        navController = navController,
    ) {
        composable<DepartmentRoutes.DepartmentTeachers>(
            typeMap =
                mapOf(
                    typeOf<DepartmentModel>() to navType<DepartmentModel>(serializer = DepartmentModel.serializer()),
                ),
        ) {
            DepartmentTeachersRoute(
                modifier = modifier,
            )
        }

        composable<DepartmentRoutes.DepartmentsList> {
            DepartmentsRoute(
                modifier = modifier,
                coordinator =
                    rememberDepartmentsCoordinator(
                        viewModel = hiltViewModel(),
                        action = {
                            when (it) {
                                is DepartmentsAction.OnDepartmentCardClicked -> {
                                    navController.navigate(DepartmentRoutes.DepartmentTeachers(it.departmentModel))
                                }
                            }
                        },
                    ),
            )
        }
    }
}
