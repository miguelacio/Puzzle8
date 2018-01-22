package mx.miguelacio.cuadritode9.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mx.miguelacio.cuadritode9.R;
import mx.miguelacio.cuadritode9.models.Player;

/**
 * Created by miguelacio on 20/01/18.
 */

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder>{

    ArrayList<Player> players = new ArrayList();

    public PlayersAdapter(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_player, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Player player = players.get(position);

        holder.textViewMovements.setText(player.getMovements());
        holder.textViewName.setText(player.getName());
        holder.textViewTime.setText(player.getTime());
        holder.textViewConfig.setText(player.getConfig());

    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewConfig, textViewMovements, textViewTime;
        public ViewHolder(View itemView) {
            super(itemView);

            this.textViewConfig = itemView.findViewById(R.id.text_view_config);
            this.textViewTime = itemView.findViewById(R.id.text_view_time);
            this.textViewName = itemView.findViewById(R.id.text_view_name);
            this.textViewMovements = itemView.findViewById(R.id.text_view_movements);
        }
    }
}
