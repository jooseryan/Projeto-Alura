package main;

import com.google.gson.Gson;
import model.Conversor;
import model.TaxaConversao;
import model.Requisicao;
import UI.UI;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UI interfaceUsuario = new UI(scanner);
        Requisicao requisitor = new Requisicao();

        String urlBase = "https://v6.exchangerate-api.com/v6/937c73a5a479fef4269a0dbf/pair/";
        Map<String, String> moedas = new HashMap<>();
        moedas.put("real", "BRL");
        moedas.put("dolar", "USD");
        moedas.put("pesoArg", "ARS");
        moedas.put("pesoCol", "COP");

        boolean continuar = true;
        String opcao;

        while (continuar){
            interfaceUsuario.menu();
            opcao = interfaceUsuario.pegarOpcaoMenu();

            double valorConverter = 0;
            double valorConvertido = 0;

            switch (opcao){

                case "1":
                    valorConverter = interfaceUsuario.pegarOpcaoConverter();
                    valorConvertido = converterValor(
                            urlBase, moedas.get("dolar"), moedas.get("pesoArg"), valorConverter, requisitor);

                    interfaceUsuario.exibirConversao("Dólares", "Pesos Argentinos", valorConvertido
                            ,valorConverter);
                    break;

                case "2":
                    valorConverter = interfaceUsuario.pegarOpcaoConverter();
                    valorConvertido = converterValor(
                            urlBase, moedas.get("pesoArg"),moedas.get("dolar") , valorConverter, requisitor);

                    interfaceUsuario.exibirConversao("Pesos Argentinos", "Dólares", valorConvertido
                            ,valorConverter);
                    break;

                case "3":
                    valorConverter = interfaceUsuario.pegarOpcaoConverter();
                    valorConvertido = converterValor(
                            urlBase, moedas.get("dolar"), moedas.get("real"), valorConverter, requisitor);

                    interfaceUsuario.exibirConversao("Dólares", "Reais Brasileiros", valorConvertido
                            ,valorConverter);
                    break;

                case "4":
                    valorConverter = interfaceUsuario.pegarOpcaoConverter();
                    valorConvertido = converterValor(
                            urlBase, moedas.get("real"), moedas.get("dolar"), valorConverter, requisitor);

                    interfaceUsuario.exibirConversao("Reais Brasileiros", "Dólares", valorConvertido
                            ,valorConverter);
                    break;

                case "5":
                    valorConverter = interfaceUsuario.pegarOpcaoConverter();
                    valorConvertido = converterValor(
                            urlBase, moedas.get("dolar"), moedas.get("pesoCol"), valorConverter, requisitor);

                    interfaceUsuario.exibirConversao("Dólares", "Pesos Colombianos", valorConvertido
                            ,valorConverter);
                    break;

                case "6":
                    valorConverter = interfaceUsuario.pegarOpcaoConverter();
                    valorConvertido = converterValor(
                            urlBase, moedas.get("pesoCol"), moedas.get("dolar"), valorConverter, requisitor);

                    interfaceUsuario.exibirConversao("Pesos Colombianos", "Dólares", valorConvertido
                            ,valorConverter);
                    break;

                case "7":
                    continuar = false;
                    break;

                default:
                    interfaceUsuario.opcaoInvalida();
                    break;
            }
        }
    }

    public static double converterValor(String urlBase, String moedaBase, String moedaAlvo,
                                        double valorConverter, Requisicao requisitor){

        Gson gson = new Gson();
        String url =
                Conversor.conectarUrl(urlBase,moedaBase, moedaAlvo);
        String body = requisitor.response(url).body();
        TaxaConversao taxa = gson.fromJson(body, TaxaConversao.class);
        return Conversor.converterValor(taxa, valorConverter);
    }

}
