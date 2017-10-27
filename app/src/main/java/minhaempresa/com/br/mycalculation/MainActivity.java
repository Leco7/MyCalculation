package minhaempresa.com.br.mycalculation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/*
* CRIAR O HISTÓRICO                                                 OK
*
             * FORMATAR OS NUMEROS QUESTÃO VIRGULA                  OK
*
               * IMPLEMENTAR O PERCENT                              OK
*
              * VERIFICAR DIVISÃO POR ZERO                          OK
*
* CRIAR CALCULO COM O ULTIMO VALOR                                  OK
*
* COMEÇAR OUTRA CONTA SEM APAGAR                                    OK
*
      *IMPOSSIBILICAR COLOCAR VÁRIAS VÍRGULAS                       OK
*
*
* LIMPAR O ZERO QUANDO CLICADO INICIALMENTE QUANDO DIGITAR          OK
* */
public class MainActivity extends AppCompatActivity {

    public static final String OPER_SOMA = "+";
    public static final String OPER_SUB = "-";
    public static final String OPER_MULT = "X";
    public static final String OPER_DIV = "÷";
    public static final String OPER_PORCENTAGEM = "%";
    private Button btnC, btnMaisMenos, btnPorcent, btn9, btn8, btn7, btn6, btn5, btn4, btn3, btn2, btn1, btn0, btnDiv, btnMult, btnSoma, btnSub, btnIgual, btnVirg;
    private TextView txtVisor;

    private String dados = "";
    private String historico ="";


    private List<Double> numeros;
    private List<String> operadores;


    private boolean somaAtivada = false;
    private boolean subAtivada = false;
    private boolean multipAtivada = false;
    private boolean divAtivada = false;
    private boolean virgAtivada = false;
    private boolean porcentAtivada = false;

    private boolean erro = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numeros = new ArrayList<>();
        operadores = new ArrayList<>();


        /*
        * ASSOCIAÇÃO DE VARIÁVEIS COM O LAYOUT*/

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



        /*
        * PEGA OS CLICKS*/
        btnC.setOnClickListener(new View.OnClickListener() {                                        //C
            @Override
            public void onClick(View v) {
                dados = "";
                txtVisor.setText("0");
                reiniciaTeclasAtivadas();
                reiniciaVirgula();
            }
        });

        btnMaisMenos.setOnClickListener(new View.OnClickListener() {                                //MAISMENOS
            @Override
            public void onClick(View v) {
                if(!dados.isEmpty()){
                    inverteSinal();
                    atualizaVisor();
                }
            }
        });

        btnPorcent.setOnClickListener(new View.OnClickListener() {                                  //PORCENT
            @Override
            public void onClick(View v) {

                substituiVirOper();

                atualizaSinaisAtivos();
                dados += OPER_PORCENTAGEM;
                reiniciaTeclasAtivadas();
                reiniciaVirgula();
                porcentAtivada = true;
                atualizaVisor();



               /* atualizaVisor();
                reiniciaTeclasAtivadas();
                reiniciaVirgula();*/
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {                                      //DIV
            @Override
            public void onClick(View v) {
                if(!divAtivada && !dados.isEmpty()){

                    substituiVirOper();

                    atualizaSinaisAtivos();
                    dados += OPER_DIV;
                    reiniciaTeclasAtivadas();
                    reiniciaVirgula();
                    divAtivada = true;
                    atualizaVisor();

                }
            }
        });

        btnMult.setOnClickListener(new View.OnClickListener() {                                     //MULT
            @Override
            public void onClick(View v) {
                if(!multipAtivada && !dados.isEmpty()){

                    substituiVirOper();

                    atualizaSinaisAtivos();
                    dados += OPER_MULT;
                    reiniciaTeclasAtivadas();
                    reiniciaVirgula();
                    multipAtivada = true;
                    atualizaVisor();

                }

            }
        });

        btnSoma.setOnClickListener(new View.OnClickListener() {                                     //SOMA
            @Override
            public void onClick(View v) {
                if(!somaAtivada && !dados.isEmpty()){

                    substituiVirOper();

                    atualizaSinaisAtivos();
                    dados += OPER_SOMA;
                    reiniciaTeclasAtivadas();
                    reiniciaVirgula();
                    somaAtivada = true;
                    atualizaVisor();
                }
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {                                      //SUB
            @Override
            public void onClick(View v) {
                if(!subAtivada && !dados.isEmpty()){

                    substituiVirOper();

                    atualizaSinaisAtivos();
                    dados += OPER_SUB;
                    reiniciaTeclasAtivadas();
                    reiniciaVirgula();
                    subAtivada = true;
                    atualizaVisor();
                }
            }
        });

        btnIgual.setOnClickListener(new View.OnClickListener() {                                    //IGUAL
            @Override
            public void onClick(View v) {
                historico += dados + " = ";



                DecimalFormat formata = new DecimalFormat("0.###");

                String valorFormatado  = formata.format(iniciaCalculo(dados));

                dados = valorFormatado.replace(".",",");

                historico += dados + "\n";


                atualizaVisor();
                reiniciaTeclasAtivadas();
                reiniciaVirgula();


            }
        });

        btnVirg.setOnClickListener(new View.OnClickListener() {                                     //VIRG
            @Override
            public void onClick(View v) {
                logicaVirgula();
                atualizaVisor();

            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {                                        //0
            @Override
            public void onClick(View v) {

                if(dados.length() > 0){
                    dados += 0;
                    atualizaVisor();
                    reiniciaTeclasAtivadas();
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {                                        //1
            @Override
            public void onClick(View v) {

                dados += 1;
                atualizaVisor();
                reiniciaTeclasAtivadas();
                
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {                                        //2
            @Override
            public void onClick(View v) {

                dados += 2;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {                                        //3
            @Override
            public void onClick(View v) {

                dados += 3;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {                                        //4
            @Override
            public void onClick(View v) {

                dados += 4;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {                                        //5
            @Override
            public void onClick(View v) {

                dados += 5;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {                                        //6
            @Override
            public void onClick(View v) {

                dados += 6;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {                                        //7
            @Override
            public void onClick(View v) {

                dados += 7;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {                                        //8
            @Override
            public void onClick(View v) {

                dados += 8;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {                                        //9
            @Override
            public void onClick(View v) {

                dados += 9;
                atualizaVisor();
                reiniciaTeclasAtivadas();
            }
        });
    }


    private void substituiVirOper() {
        if(dados.charAt(dados.length()-1) == ','){
            dados = dados.substring(0, dados.length()-1);
        }
    }


    /*
    * FUNÇÃO QUE REALIZA O CALCULO DE FATO
    * É AQUI ONDE SE UTILIZA A REGRA DE PRIORIDADE DE OPERAÇÕES*/
    private double calcula(){
        double resultado = 0;

        int cont = 0;
        while(cont < operadores.size()){

            if(operadores.get(cont).equals(OPER_MULT)){

                resultado = numeros.get(cont) * numeros.get(cont +1);
                numeros.remove(cont +1);
                operadores.remove(cont);
                numeros.set(cont, resultado);
                cont = -1;
            }else if (operadores.get(cont).equals(OPER_DIV)) {

                if(numeros.get(cont + 1) != 0) {
                    resultado = numeros.get(cont) / numeros.get(cont + 1);
                    numeros.remove(cont + 1);
                    operadores.remove(cont);
                    numeros.set(cont, resultado);
                    cont = -1;
                }else{
                    erro = true;
                }
            }
            cont++;
        }
        cont = 0;
        while(cont < operadores.size()){

            if (operadores.get(cont).equals(OPER_SOMA)) {

                resultado = numeros.get(cont) + numeros.get(cont + 1);
                numeros.remove(cont + 1);
                operadores.remove(cont);
                numeros.set(cont, resultado);
                cont = -1;

            }else if (operadores.get(cont).equals(OPER_SUB)) {

                resultado = numeros.get(cont) - numeros.get(cont + 1);
                numeros.remove(cont + 1);
                operadores.remove(cont);
                numeros.set(cont, resultado);
                cont = -1;
            }
            cont++;
        }

        resultado = numeros.get(0);
        numeros.clear();
        operadores.clear();

        return resultado;

    }


    /*
    * FUNÇÃO QUE LÊ A STRING DADOS
    * E SEPARA NUMEROS DE OPERADORES E DUAS LISTAS
    * AO FINAL REALIZA A CONTA SE HOUVER DADOS SUFICIENTES
    * */
    private double iniciaCalculo(String conta){
        String numero = "";
        String numeroAnterior = "";
        String oper = "";
        char c = 0;
        double numeroAdd = 0;



        if(dados.isEmpty()){
            return 0;
        }

        /*23 + 15 % = 26.45
        23 + (15/100*23)    */

        for(int i = 0; i<conta.length(); i++){
            c = conta.charAt(i);

            if(Character.isDigit(c)){
                numero += c;

            }else if(c == ','){
                numero += ".";
            }else if(c == '%') {
                if(!numeroAnterior.isEmpty()) {
                    numero = calculaPorcentagem(numero, numeroAnterior);
                    numeroAnterior = "";
                }
            }else{
                numeroAnterior = numero;
                numeroAdd = Double.valueOf(numero);
                numero = "";
                oper = String.valueOf(c);


                numeros.add(numeroAdd);
                operadores.add(oper);
            }
        }

        if(!numero.isEmpty()) {
            numeroAdd = Double.valueOf(numero);
            numeros.add(numeroAdd);
            if(numeros.size() == operadores.size()){
                operadores.remove(operadores.size()-1);
            }

            return calcula();
        }else if(numeros.size() == 1){
            numeros.clear();
            return Double.valueOf(dados);
        }else{
            return 0;
        }
    }


    /*
    * CALCULA PORCENTAGEM*/
    private String calculaPorcentagem(String numero, String numeroAnterior) {
        double numAtual = Double.valueOf(numero);
        double numAnterior = Double.valueOf(numeroAnterior);
        double res;
        String operAtual = operadores.get(operadores.size()-1);

        res = (numAtual/100);

        if(operAtual.equals(OPER_SOMA) || operAtual.equals(OPER_SUB)){
            res *= numAnterior;
        }

        //  100*2%=2
        //100/2% = 5000

        return String.valueOf(res);
    }

    /*
    * APAGA OS SINAIS ATIVOS DE DADOS SE A VIRGULA NÃO ESTIVER ATIVADA
    */
    private void atualizaSinaisAtivos() {
        if(divAtivada || subAtivada || multipAtivada || somaAtivada || porcentAtivada){
            dados = dados.substring(0, dados.length()-1);
        }

    }
    /*
    * PEGA O VALOR DOUBLE DE DADOS
    * INVERTE O SINAL
    * E TRANSFORMA PARA STRING PARA DEVOLVER PARA DADOS*/
    private void inverteSinal() {
        dados = String.valueOf(Double.valueOf(dados)*(-1));
    }

    /*
    * MOSTRA O VALOR DA VARIÁVEL DADOS*/
    private void atualizaVisor(String mensagem){
        txtVisor.setText(mensagem+"\n" + dados);
    }


    /*
    * JOGA O VALOR DE DADOS NA TELA
    * SE ERRO FOR TRUE SIGNIFICA QUE O USUÁRIO TENTOU DIVIDIR ALGUM NUMERO POR ZERO
    * ASSIM EXIBE A MENSAGEM DE ERRO*/
    private void atualizaVisor(){
        if(!erro){
            txtVisor.setTextColor(Color.BLACK);
            if(!historico.isEmpty()){
                String linha = "-------------------------\n";
                txtVisor.setText(historico+linha+dados);
            }else{
                txtVisor.setText(dados);
            }
        }else{
            txtVisor.setTextColor(Color.RED);
            txtVisor.setText("ERRO AO DIVIDIR POR ZERO");
            erro = false;
        }

    }

    /*
    * MARCA FALSE EM TODAS AS TECLAS DE OPERAÇÃO*/
    private void reiniciaTeclasAtivadas(){
        divAtivada = false;
        multipAtivada = false;
        somaAtivada = false;
        subAtivada = false;
        porcentAtivada = false;

    }

    private void reiniciaVirgula(){
        virgAtivada = false;
    }

    /*SE A VIRGULA NÃO ESTIVER ATIVADA E DADOS NÃO ESTIVEREM VAZIOS
        E NÃO TIVER NENHUM OPERADOR ATIVO
            ATIVA A VIRGULA
     */
    private void logicaVirgula() {
        if(!virgAtivada){
            if(divAtivada || multipAtivada || somaAtivada || subAtivada || dados.isEmpty()){
                dados += "0,";
            }else {
                dados += ",";
            }
            virgAtivada = true;
            reiniciaTeclasAtivadas();

        }
        //     return false;

        /*if(dados.isEmpty() || divAtivada || multipAtivada || somaAtivada || subAtivada){
            dados += "0,";
        }
        if(!virgAtivada && !dados.isEmpty()){
            if(divAtivada || multipAtivada || somaAtivada || subAtivada){
                dados += "0,";
                return false;
            }else{
                return true;
            }

        }
        return false;*/
    }

    //está vazio    está ativada    não tem sinal ativo



    private void exibe (Object s){
        Toast.makeText(this, s+"", Toast.LENGTH_LONG).show();
    }


    private void pegaPorcentagem() {
        double num1 = Double.valueOf(dados);
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