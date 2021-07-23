package gr.sfhmmy.java.workshop;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import gr.sfhmmy.java.workshop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding layoutAdapter;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                .get(MainActivityViewModel.class);

        layoutAdapter = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    public void showGreeting(View view) {
        int age = Integer.parseInt(layoutAdapter.ageEditText.getText().toString());
        layoutAdapter.greetingOutputTextView.setText(viewModel.calculatingGreeting(age, layoutAdapter.nameEditText.getText().toString()));
    }
}