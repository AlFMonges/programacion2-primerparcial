package com.examenparcial1.universidadapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
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

        setSupportActionBar(binding.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();

            Set<Integer> topLevelDestinations = new HashSet<>();
            topLevelDestinations.add(R.id.estudianteListFragment);
            topLevelDestinations.add(R.id.profesorListFragment);
            topLevelDestinations.add(R.id.cursoListFragment);
            topLevelDestinations.add(R.id.inscripcionListFragment);

            appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations).build();

            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(binding.bottomNavigation, navController);

            navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                // Usamos post() para asegurarnos de que nuestro código se ejecute
                // después del listener interno de NavigationUI.
                binding.bottomNavigation.post(() -> {
                    int currentDestinationId = destination.getId();

                    boolean isCursoSelectionMode = (currentDestinationId == R.id.cursoListFragment)
                            && (arguments != null && arguments.getBoolean("isSelectionMode"));

                    if (currentDestinationId == R.id.inscripcionFormFragment || isCursoSelectionMode) {
                        binding.bottomNavigation.getMenu().findItem(R.id.inscripcionListFragment).setChecked(true);
                    }
                });
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_exit) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
