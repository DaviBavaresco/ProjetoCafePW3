package br.edu.ifrs.projetocafe.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import br.edu.ifrs.projetocafe.R;
import br.edu.ifrs.projetocafe.dao.AppDatabase;
import br.edu.ifrs.projetocafe.dao.CafeDAO;
import br.edu.ifrs.projetocafe.entity.Cafe;

public class EmailCafeActivity extends AppCompatActivity {

    AppCompatEditText emailTextNome ;
    AppCompatEditText emailTextDesc;
    AppCompatEditText emailTextValor;
    AppCompatEditText emailTextEmail;
    AppCompatButton botaoEnvio;

    Cafe cafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_cafe);
        emailTextNome = this.findViewById(R.id.emailTextNome);
        emailTextDesc =  this.findViewById(R.id.emailTextDescricao);
        emailTextValor =  this.findViewById(R.id.emailTextValor);
        emailTextEmail =  this.findViewById(R.id.emailTextEmail);
        botaoEnvio =  this.findViewById(R.id.buttonEnvio);




        //cria evento para o botão editar
        botaoEnvio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                carregaCafe();
                String Nome,Descri,Para,Valor;
                Nome= emailTextNome.getText().toString();
                Descri= emailTextDesc.getText().toString();
                Valor= emailTextValor.getText().toString();
                Para= emailTextEmail.getText().toString();
                sendEmail(Nome,Descri,Valor,Para);
            }
        });
        //Pega o objeto que foi passado como parâmetro
        Bundle extra =  this.getIntent().getExtras();
        cafe = (Cafe) getIntent().getSerializableExtra("cafe");
        emailTextNome.setText(cafe.getNome());
        emailTextDesc.setText(cafe.getDescricao());
        emailTextValor.setText(cafe.getValor());
    }

    public void sendEmail(String Nome, String Descri,String Valor, String Para){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{Para});
        intent.putExtra(Intent.EXTRA_SUBJECT,"Quero compartilhar com voce meu cafe favorito");
        intent.putExtra(Intent.EXTRA_TEXT, "Nome: "+ Nome+" Descrição: "+Descri+" Valor: " + Valor);
        //intent.putExtra(Intent.EXTRA_TEXT, Valor);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose email client:"));

    }

    private void carregaCafe() {

            cafe.setNome(emailTextNome.getText().toString().trim());
            cafe.setDescricao(emailTextDesc.getText().toString().trim());
            cafe.setValor(emailTextValor.getText().toString().trim());
           AppDatabase.getInstance(getApplicationContext()).createCafeDAO();



    }
}
