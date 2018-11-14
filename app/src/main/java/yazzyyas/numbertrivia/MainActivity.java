package yazzyyas.numbertrivia;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView numberRecyclerView;
    FloatingActionButton fabAddNumber;
    private NumberAdapter numberAdapter;
    private List<NumberItem> numbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAddNumber = findViewById(R.id.fabAddNumber);
        fabAddNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });

        this.numberAdapter = new NumberAdapter(this, numbers);
        numberRecyclerView = findViewById(R.id.numberRecyclerView);
        numberRecyclerView.setAdapter(numberAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        numberRecyclerView.setLayoutManager(layoutManager);
        numberRecyclerView.setHasFixedSize(true);
    }

    private void requestData() {
        NumbersApiService service = NumbersApiService.retrofit.create(NumbersApiService.class);
        Call<NumberItem> call = service.getNumberQuote();
        call.enqueue(new Callback<NumberItem>() {
            @Override
            public void onResponse(Call<NumberItem> call, Response<NumberItem> response) {
                NumberItem quoteItem = response.body();
                numbers.add(0, new NumberItem(quoteItem.getText(), quoteItem.getNumber()));
                numberAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NumberItem> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
