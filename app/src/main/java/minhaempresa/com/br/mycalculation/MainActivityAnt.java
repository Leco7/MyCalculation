package minhaempresa.com.br.mycalculation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//GIT HUB CLAUDIO MONTEOLIVA

public class MainActivityAnt extends AppCompatActivity {

    public static final String OPER_SOMA = "+";
    public static final String OPER_SUB = "-";
    public static final String OPER_MULT = "X";
    public static final String OPER_DIV = "÷";

    private Button btnC, btnMaisMenos, btnPorcent, btn9, btn8, btn7, btn6, btn5, btn4, btn3, btn2, btn1, btn0, btnDiv, btnMult, btnSoma, btnSub, btnIgual, btnVirg;

    private TextView txtVisor;

    private boolean somaAtivada = false;
    private boolean subAtivada = false;
    private boolean multipAtivada = false;
    private boolean divAtivada = false;
    private boolean virgAtivada = false;

    List<Double> historico;                                                                                         //CRIAR HISTÓRICO

    String dados = "";
    String contaCompleta = "";

    List<Double> listaSoma;
    List<Double> listaSub;
    List<Double> listaDiv;
    List<Double> listaMult;

    double numAtual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaSoma = new ArrayList<>();
        listaSub = new ArrayList<>();
        listaDiv = new ArrayList<>();
        listaMult = new ArrayList<>();

        btnC = (Button) findViewById(R.id.btnC);
        btnMaisMenos = (Button) findViewById(R.id.btnMaisMenos);
        btnPorcent = (Button) findViewById(R.id.btnPercentual);
        btn9 = (Button) findViewById(R.id.btn9);
        btn8 = (Button) findViewById(R.id.btn8);
        btn7 = (Button) findViewById(R.id.btn7);
        btn6 = (Button) findViewById(R.id.btn6);
        btn5 = (Button) findViewById(R.id.btn5);
        btn4 = (Button) findViewById(R.id.btn4);
        btn3 = (Button) findViewById(R.id.btn3);
        btn2 = (Button) findViewById(R.id.btn2);
        btn1 = (Button) findViewById(R.id.btn1);
        btn0 = (Button) findViewById(R.id.btn0);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnMult = (Button) findViewById(R.id.btnMultip);
        btnSoma = (Button) findViewById(R.id.btnSoma);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnIgual = (Button) findViewById(R.id.btnIgual);
        btnVirg = (Button) findViewById(R.id.btnVirg);

        txtVisor = (TextView) findViewById(R.id.txtVisor);

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contaCompleta = "";
                dados = "";
                txtVisor.setText("0");
            }
        });

        btnMaisMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inverteSinal();
                atualizaVisor();
            }
        });

        btnPorcent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //   pegaPorcentagem();
                atualizaVisor();
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {                                      //DIV
            @Override
            public void onClick(View v) {
                if(!divAtivada && !dados.isEmpty()){


                    listaDiv.add(Double.valueOf(dados));

                    verificaSinaisAtivos();
                    contaCompleta += OPER_DIV;
                    reiniciaTeclasAtivadas();
                    divAtivada = true;
                    atualizaVisor();

                }
            }
        });

        btnMult.setOnClickListener(new View.OnClickListener() {                                     //MULT
            @Override
            public void onClick(View v) {
                if(!multipAtivada && !dados.isEmpty()){

                    listaMult.add(Double.valueOf(dados));

                    verificaSinaisAtivos();
                    contaCompleta += OPER_MULT;
                    reiniciaTeclasAtivadas();
                    multipAtivada = true;
                    atualizaVisor();

                }

            }
        });

        btnSoma.setOnClickListener(new View.OnClickListener() {                                     //SOMA
            @Override
            public void onClick(View v) {
                if(!somaAtivada && !dados.isEmpty()){

                    listaSoma.add(Double.valueOf(dados));

                    verificaSinaisAtivos();
                    contaCompleta += OPER_SOMA;
                    reiniciaTeclasAtivadas();                       //FAZ LÓGICA GRÁFICA
                    somaAtivada = true;
                    atualizaVisor();

                }
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {                                      //SUB
            @Override
            public void onClick(View v) {
                if(!subAtivada && !dados.isEmpty()){

                    numAtual = Double.valueOf(dados);

                    verificaSinaisAtivos();
                    contaCompleta += OPER_SUB;
                    reiniciaTeclasAtivadas();
                    subAtivada = true;
                    atualizaVisor();

                }
            }
        });

        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dados += " =";
           //     realizaCalculo();
                atualizaVisor();

            }
        });

        btnVirg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dados += ",";
                atualizaVisor();
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dados += 0;
                contaCompleta += 0;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dados += 1;
                contaCompleta += 1;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dados += 2;
                contaCompleta += 2;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dados += 3;
                contaCompleta += 3;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dados += 4;
                contaCompleta += 4;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dados += 5;
                contaCompleta += 5;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dados += 6;
                contaCompleta += 6;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dados += 7;
                contaCompleta += 7;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dados += 8;
                contaCompleta += 8;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dados += 9;
                contaCompleta += 9;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });
    }


    private void atualizaVisor(){
        txtVisor.setText(contaCompleta);
    }

    private double soma(double num1, double num2){
        return num1 + num2;
    }
    private double subtrai(double num1, double num2){
        return num1 - num2;
    }
    private double divide(double num1, double num2){
            return num1 / num2;
    }
    private double multiplica(double num1, double num2){
        return num1 * num2;
    }
    private double percentual(double num1, double num2){
        return num1 * num2;
    }


    private boolean verifica(double num1, double num2){
        if(num2 == 0){
            return false;
        }
        return true;
    }

    /*
    * MARCA FALSE EM TODAS AS TECLAS DE OPERAÇÃO*/
    private void reiniciaTeclasAtivadas(){
        divAtivada = false;
        multipAtivada = false;
        somaAtivada = false;
        subAtivada = false;
        virgAtivada = false;
    }

    private void inverteSinal() {
        contaCompleta = String.valueOf(Double.valueOf(contaCompleta)*(-1));
    }

    /*
  * APAGA OS SINAIS ATIVOS DA TELA E DE DADOS*/
    private void verificaSinaisAtivos() {
        if(divAtivada || subAtivada || multipAtivada || somaAtivada){
            contaCompleta = contaCompleta.substring(0, contaCompleta.length()-1);
        }else if(virgAtivada){
            contaCompleta += 0;                                                         // VERIFICAR QUESTÃO DE VÍRGULA
        }
    }

    /*
    * public void clearClick(View view){
    * seta 0 na tela
    * textVisor.setText("0);
    *
    * operadorPressionado
    * */




    /*
    public void numClick(View view){
    Button btn = (Button) view;

    setTextValor(btn.getText().toString();
    }
    * */

}
