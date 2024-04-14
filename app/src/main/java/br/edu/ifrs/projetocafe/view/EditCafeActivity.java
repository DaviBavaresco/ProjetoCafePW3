package br.edu.ifrs.projetocafe.view;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.Calendar;

import br.edu.ifrs.projetocafe.R;
import br.edu.ifrs.projetocafe.dao.AppDatabase;
import br.edu.ifrs.projetocafe.dao.CafeDAO;
import br.edu.ifrs.projetocafe.entity.Cafe;

public class EditCafeActivity extends AppCompatActivity {
    AppCompatEditText editTextNome ;
    AppCompatEditText editTextDesc;
    AppCompatEditText editTextValor;
    AppCompatButton botaoEdit;

    Cafe cafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cafe);
        editTextNome = this.findViewById(R.id.editTextNome);
        editTextDesc =  this.findViewById(R.id.editTextDescricao);
        editTextValor =  this.findViewById(R.id.editTextValor);
        botaoEdit =  this.findViewById(R.id.buttonEdit);





        botaoEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                alterarCafe();
            }
        });

        Bundle extra =  this.getIntent().getExtras();
        cafe = (Cafe) getIntent().getSerializableExtra("cafe");
        editTextNome.setText(cafe.getNome());
        editTextDesc.setText(cafe.getDescricao());
        editTextValor.setText(cafe.getValor());
    }

    private void alterarCafe() {
        if (editTextNome.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Nome é obrigatório!", Toast.LENGTH_LONG).show();
            editTextNome.requestFocus();
        } else if (editTextDesc.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Descrição é obrigatório!", Toast.LENGTH_LONG).show();
            editTextDesc.requestFocus();
        } else if (editTextValor.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Valor é obrigatório!", Toast.LENGTH_LONG).show();
            editTextValor.requestFocus();
        } else {
            cafe.setNome(editTextNome.getText().toString().trim());
            cafe.setDescricao(editTextDesc.getText().toString().trim());
            cafe.setValor(editTextValor.getText().toString().trim());
            CafeDAO cafeDAO = AppDatabase.getInstance(getApplicationContext()).createCafeDAO();
            cafeDAO.update(cafe);
            exibeMensagem();
        }
    }

    public void exibeMensagem(){

        String msg = "Cafe alterado com sucesso! ";
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.app_name);
        alertDialog.setMessage(msg);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(getApplicationContext(), ListCafesActivity.class);
                startActivity(intent);
                finish();
            }
        });
        alertDialog.show();
    }
}
