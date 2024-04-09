package br.edu.ifrs.projetocafe.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import br.edu.ifrs.projetocafe.R;
import br.edu.ifrs.projetocafe.dao.AppDatabase;
import br.edu.ifrs.projetocafe.entity.Cafe;

public class CadCafeActivity extends AppCompatActivity {

    AppCompatEditText nome;
    AppCompatEditText descricao;
    AppCompatEditText valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cafe);
        Button botao = findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = findViewById(R.id.editTextNome);
                descricao =  findViewById((R.id.editTextDescricao));
                valor =  findViewById(R.id.editTextValor);
                Cafe cafe = new Cafe();
                cafe.setNome(nome.getText().toString());
                cafe.setDescricao(descricao.getText().toString());
                cafe.setValor(valor.getText().toString());
                AppDatabase.getInstance(getApplicationContext()).createCafeDAO().insert(cafe);
                Toast.makeText(getApplicationContext(), "Seu Cafe foi salvo com sucesso!", Toast.LENGTH_LONG).show();
                limparCampos();
            }
        });
    }


    private void limparCampos(){
        nome = findViewById(R.id.editTextNome);
        descricao = findViewById((R.id.editTextDescricao));
        valor = findViewById(R.id.editTextValor);
        nome.setText("");
        descricao.setText("");
        valor.setText("");
    }
}
