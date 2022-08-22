package kelompok10pam.develops.pedulicovid19.Gejala;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;

import kelompok10pam.develops.pedulicovid19.R;

public class GejalaAdapter extends RecyclerView.Adapter<GejalaAdapter.GridItemViewHolder> {
    private List<Gejala> gejalaList;

    private Context c;

    public class GridItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvgejala, tvdetail;
        Button btnEdit, btnDelete;

        public GridItemViewHolder(View view) {
            super(view);
            tvgejala = view.findViewById(R.id.tv_judul);
            tvdetail = view.findViewById(R.id.tv_gejala1);
            btnEdit = view.findViewById(R.id.btn_edit);
            btnDelete = view.findViewById(R.id.btn_delete);
        }
    }

    public GejalaAdapter(Context c, List gejalaList) {
        this.c = c;
        this.gejalaList = gejalaList;
    }

    @Override
    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new GridItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GridItemViewHolder holder, int position) {
        final Gejala gejala = gejalaList.get(position);

        holder.tvgejala.setText(gejala.getJudul());
        holder.tvgejala.append(" ");

        holder.tvdetail.setText(gejala.getGejala1());

        holder.btnEdit.setTag(position);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(v.getTag().toString());

                Intent i = new Intent(c, AddGejalaActivity.class);
                i.putExtra("id", gejalaList.get(pos).getId());
                c.startActivity(i);
            }
        });

        holder.btnDelete.setTag(position);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = Integer.parseInt(v.getTag().toString());

                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setMessage("Are you sure you want to delete this gejala?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new DeleteGejala(c, gejalaList.remove(pos)).execute();
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return gejalaList.size();
    }

    static class DeleteGejala extends AsyncTask<Void, Void, Void> {
        private Gejala u;
        private WeakReference<Context> c;

        public DeleteGejala(Context c, Gejala u) {
            this.c = new WeakReference<>(c);
            this.u = u;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            GejalaDatabase ud = GejalaDatabase.getAppDatabase(c.get());
            ud.gejalaDao().delete(u);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(c.get(), "Gejala deleted successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
