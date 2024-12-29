package com.henaoandres.convertidordemonedas.principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.henaoandres.convertidordemonedas.modelos.MonedaApi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConvertidorDeMonedas {

    public void convertir(String moneda1, String moneda2, double valor, List<String> listaDeMonedas) {

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy
                .LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

        String api = "add198d4d40a7afa664fb9ca";

        String direccion = "https://v6.exchangerate-api.com/v6/" + api + "/pair/" + moneda1 +
                "/" + moneda2 + "/" + valor;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.
                create(direccion)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse
                    .BodyHandlers.ofString());

            String json = response.body();

            MonedaApi convertidorApi = gson.fromJson(json, MonedaApi.class);
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
            LocalDateTime momentoDeConversion = LocalDateTime.now();

            System.out.println(valor+" "+moneda1+" corresponden a "+
                    convertidorApi.conversion_result()+
                    " "+moneda2);

            listaDeMonedas.add("["+momentoDeConversion.format(formateador)+"] "+valor+" "+
                    moneda1+" = "+convertidorApi.conversion_result()+" "+moneda2);

        } catch (Exception e){
            throw new RuntimeException("Moneda no encontrada");
        }
    }
}
