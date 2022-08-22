package kelompok10pam.develops.pedulicovid19.Berita;

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

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.GridItemViewHolder> {
    private List<Berita> beritaList;

    private Context c;

    public class GridItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvberita, tvisiberita;
        Button btnEdit, btnDelete;

        public GridItemViewHolder(View view) {
            super(view);
            tvberita = view.findViewById(R.id.tv_judulberita);
            tvisiberita = view.findViewById(R.id.tv_isiberita);
            btnEdit = view.findViewById(R.id.btn_edit);
            btnDelete = view.findViewById(R.id.btn_delete);
        }
    }

    public BeritaAdapter(Context c, List beritalist) {
        this.c = c;
        this.beritaList = beritalist;
    }

    @Override
    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita, parent, false);
        return new GridItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GridItemViewHolder holder, int position) {
        final Berita berita = beritaList.get(position);

        holder.tvberita.setText(berita.getJudul());
        holder.tvberita.append(" ");

        holder.tvisiberita.setText(berita.getIsi());

        holder.btnEdit.setTag(position);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(v.getTag().toString());

                Intent i = new Intent(c, AddBeritaActivity.class);
                i.putExtra("id", beritaList.get(pos).getId());
                c.startActivity(i);
            }
        });

        holder.btnDelete.setTag(position);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = Integer.parseInt(v.getTag().toString());

                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setMessage("Are you sure you want to delete this news?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new DeleteBerita(c, beritaList.remove(pos)).execute();
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
        return beritaList.size();
    }

    static class DeleteBerita extends AsyncTask<Void, Void, Void> {
        private Berita u;
        private WeakReference<Context> c;

        public DeleteBerita(Context c, Berita u) {
            this.c = new WeakReference<>(c);
            this.u = u;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            BeritaDatabase ud = BeritaDatabase.getAppDatabase(c.get());
            ud.BeritaDao().delete(u);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(c.get(), "News deleted successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
