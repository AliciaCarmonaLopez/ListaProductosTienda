package dsa.upc.edu.listaproductostienda;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import dsa.upc.edu.listaproductostienda.github.Contributor;
import dsa.upc.edu.listaproductostienda.github.GitHub;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public String username;
    public String repo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void OnClickListar(View v){

            GitHub gitHubService = GitHub.retrofit.create(GitHub.class);
            Call<List<Contributor>> call = gitHubService.contributors("AliciaCarmonaLopez", "aprender-github");

            call.enqueue(new Callback<List<Contributor>>() {
                @Override
                public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                    // set the results to the adapter
                    //adapter.setData(response.body());
                    Log.d("XXXX", ""+response.body());

                    //if(mySwipeRefreshLayout!=null) mySwipeRefreshLayout.setRefreshing(false);
                }
                @Override
                public void onFailure(Call<List<Contributor>> call, Throwable t) {
                    //if(mySwipeRefreshLayout!=null) mySwipeRefreshLayout.setRefreshing(false);

                    String msg = "Error in retrofit: "+t.toString();
                    Log.d("xxxx",msg);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
                }
            });

    }
}