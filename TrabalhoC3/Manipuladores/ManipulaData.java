package TrabalhoC3.Manipuladores;

import java.util.Calendar;

public class ManipulaData {

    public static Calendar lerDataString (String texto) {

        String[] dado;
        int dia, mes, ano;
        Calendar data;
        
        dado = texto.split("/");
        dia = Integer.parseInt(dado[0]);
        mes = Integer.parseInt(dado[1]) - 1;
        ano = Integer.parseInt(dado[2]);

        data = Calendar.getInstance();
        data.set(ano, mes, dia);

        return data;
    }

}