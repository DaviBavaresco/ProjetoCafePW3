package br.edu.ifrs.projetocafe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifrs.projetocafe.R;
import br.edu.ifrs.projetocafe.dao.AppDatabase;
import br.edu.ifrs.projetocafe.dao.CafeDAO;
import br.edu.ifrs.projetocafe.entity.Cafe;
import br.edu.ifrs.projetocafe.view.EditCafeActivity;
import br.edu.ifrs.projetocafe.view.EmailCafeActivity;
import br.edu.ifrs.projetocafe.view.ListCafesActivity;

public class LinhaConsultaAdapter extends BaseAdapter {

    private static LayoutInflater layoutInflater = null;
    List<Cafe> cafes =  new ArrayList<>();


    private ListCafesActivity listarAtividades;


    public LinhaConsultaAdapter(ListCafesActivity listarAtividades, List<Cafe> cafes ) {
        this.cafes       =  cafes;
        this.listarAtividades  =  listarAtividades;
        this.layoutInflater     = (LayoutInflater) this.listarAtividades.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return cafes.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha,null);

        AppCompatTextView textViewNome  =  viewLinhaLista.findViewById(R.id.textViewNome);
        AppCompatTextView textViewDescricao = viewLinhaLista.findViewById(R.id.textViewDescricao);
        AppCompatTextView textViewValor = viewLinhaLista.findViewById(R.id.textViewValor);
        AppCompatImageButton buttonCompartilhar = viewLinhaLista.findViewById(R.id.buttonCompartilhar);
        AppCompatImageButton buttonExcluir = viewLinhaLista.findViewById(R.id.buttonExcluir);
        AppCompatImageButton buttonEditar = viewLinhaLista.findViewById(R.id.buttonEditar);

        textViewNome.setText(cafes.get(position).getNome());
        textViewDescricao.setText(cafes.get(position).getDescricao());
        textViewValor.setText(cafes.get(position).getValor());


        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CafeDAO cafeDAO = AppDatabase.getInstance(listarAtividades.getApplicationContext()).createCafeDAO();
                List<Cafe> cafes = cafeDAO.getAllCafes();
                cafeDAO.delete(cafes.get(position));
                atualizaLista(position);
            }
        });


        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listarAtividades, EditCafeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("cafe",cafes.get(position));
                listarAtividades.startActivity(intent);
            }
        });

        buttonCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listarAtividades, EmailCafeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("cafe",cafes.get(position));
                listarAtividades.startActivity(intent);
            }
        });
        return viewLinhaLista;



    }


    public void atualizaLista(int position){
        String mensagem = "Café excluído com sucesso!";
        Toast.makeText(listarAtividades, mensagem, Toast.LENGTH_LONG).show();
        this.cafes.remove(position);
        this.notifyDataSetChanged();
    }

}
