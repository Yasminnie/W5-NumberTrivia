package yazzyyas.numbertrivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.ViewHolder> {

    private List<NumberItem> numberData;
    private LayoutInflater inflater;

    public NumberAdapter(Context context, List<NumberItem> numberData) {
        this.numberData = numberData;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.number_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        int number = numberData.get(position).getNumber();
        String quote = numberData.get(position).getText();
        viewHolder.number.setText(String.valueOf(number));
        viewHolder.quote.setText(quote);
    }

    @Override
    public int getItemCount() {
        return numberData.size();
    }

    public void setNumberData(List<NumberItem> numberData) {
        this.numberData = numberData;
        notifyDataSetChanged(); // zegt tegen UI dat je items kan updaten in recyclerview
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView number;
        TextView quote;

        ViewHolder(View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.number);
            quote = itemView.findViewById(R.id.quote);
        }
    }
}
