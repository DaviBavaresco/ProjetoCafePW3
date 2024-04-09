package br.edu.ifrs.projetocafe.view;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.edu.ifrs.projetocafe.R;
import br.edu.ifrs.projetocafe.adapter.LinhaConsultaAdapter;
import br.edu.ifrs.projetocafe.dao.AppDatabase;
import br.edu.ifrs.projetocafe.dao.CafeDAO;
import br.edu.ifrs.projetocafe.entity.Cafe;

public class ListCafesActivity extends AppCompatActivity {

    private ListView listCafes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cafes);
        listCafes = this.findViewById(R.id.listViewCafes);
        CafeDAO cafeDAO = AppDatabase.getInstance(getApplicationContext()).createCafeDAO();
        getAll(cafeDAO.getAllCafes());
    }
    protected  void getAll(List<Cafe> cafes){
        listCafes.setAdapter(new LinhaConsultaAdapter(this, cafes));
    }
}
