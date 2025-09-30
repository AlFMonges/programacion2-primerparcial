package com.examenparcial1.universidadapp.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.examenparcial1.universidadapp.R;
import com.examenparcial1.universidadapp.databinding.ActivityMainBinding;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar la Toolbar
        setSupportActionBar(binding.toolbar);

        // Obtener el NavController desde el NavHostFragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();

            // Definir los destinos de nivel superior para AppBarConfiguration.
            // Estos son los fragmentos accesibles directamente desde la BottomNavigationView,
            // donde el botón "Up" no debería mostrarse.
            Set<Integer> topLevelDestinations = new HashSet<>();
            topLevelDestinations.add(R.id.estudianteListFragment);
            topLevelDestinations.add(R.id.profesorListFragment);
            topLevelDestinations.add(R.id.cursoListFragment);
            topLevelDestinations.add(R.id.inscripcionListFragment);

            appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations).build();

            // Configurar la Toolbar con NavController y AppBarConfiguration
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

            // Configurar la BottomNavigationView con NavController
            NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
        }
    }

    // Sobrescribir onSupportNavigateUp para manejar la navegación "Up" con el NavController
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
