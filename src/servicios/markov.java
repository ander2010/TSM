/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author letal
 */
public final class markov {

    private Map<String, Integer> wordCounts = new TreeMap<String, Integer>();
    private Map<String, Integer> wordCountstotales = new TreeMap<String, Integer>();
    private ArrayList<String> corpusText = new ArrayList<>();
    Map<Integer, List<Integer>> matriAzignacion = new TreeMap<Integer, List<Integer>>();
    Map<String, Integer> mapaDeCaracteres = new TreeMap<String, Integer>();
    int cantidadDePalabrasdiferentes=0;
     int tamanomatrizaleatroria=0;
   Collection<String> word = new ArrayList<>();

    public int getTamanomatrizaleatroria() {
        return tamanomatrizaleatroria;
    }

    public void setTamanomatrizaleatroria(int tamanomatrizaleatroria) {
        this.tamanomatrizaleatroria = tamanomatrizaleatroria;
    }

    public int getCantidadDePalabrasdiferentes() {
        return cantidadDePalabrasdiferentes;
    }

    public void setCantidadDePalabrasdiferentes(int cantidadDePalabrasdiferentes) {
        this.cantidadDePalabrasdiferentes = cantidadDePalabrasdiferentes;
    }
    private double[][] EstadoMatriz;

    ArrayList<String> auxiliarMOstrarNumeros = new ArrayList<>();
   
    private float [][] matrizAjustada= new float[tamanomatrizaleatroria][tamanomatrizaleatroria];
 private double[][] AleatoriaMatriz;
   
    
    
    
    DecimalFormat df = new DecimalFormat("##,00");
    
    

    public markov(Map<String, Integer> wordCounts,Map<String, Integer> wordCountsttal,ArrayList<String> corpusText,int palabrasdiferenetes,int valor) {

       // List<String> CargarInformacion = CargarInformacion();
        setCorpusText(corpusText);
        wordCountstotales=wordCountsttal;
         computeWordCounts();
        setCantidadDePalabrasdiferentes(palabrasdiferenetes);
        setTamanomatrizaleatroria(valor);
      
         
        EstadoMatriz = new double[wordCounts.size()][wordCounts.size()];
        AleatoriaMatriz = new double[wordCounts.size()][wordCounts.size()];

        LlenarMatrizEstado();
        
        MostrarMatriz(EstadoMatriz);
        mostrarAleatrorio();
        Ajustarfila();
        HacerMatrizdeAsignacion();
        mostrarPalabraContTodo();

    }

    public void HacerMatrizdeAsignacion() {
        ArrayList<Integer> aux;
        int valor = 0;
        Iterator it = wordCountstotales.keySet().iterator();
        while (it.hasNext()) {
            valor += 1;
            String key = (String) it.next();
            mapaDeCaracteres.put(key, valor);
//            System.out.println("Clave: " + key + " -> Valor: " + wordCounts.get(key));
        }

        for (int i = 0; i < corpusText.size(); i++) {

            String oracion = corpusText.get(i);
            String[] comparar = oracion.split(" ");

            aux = new ArrayList<>();

            for (int j = 0; j < comparar.length; j++) {

                Iterator ite = mapaDeCaracteres.keySet().iterator();
                while (ite.hasNext()) {

                    String key = (String) ite.next();
                //    System.out.println(key + "   asdsadsdasdsd   " + comparar[j]);
                    if (key.equals(comparar[j])) {

                        aux.add(mapaDeCaracteres.get(key));
                      //  System.out.print(mapaDeCaracteres.get(key) + "    valor de ");
                        break;
                    }

                }

            }
            
            matriAzignacion.put(i, aux);
              String auxe= "";
            for (int j = 0; j < aux.size(); j++) {
              
                auxe= auxe +aux.get(j)+",";
            }
            auxiliarMOstrarNumeros.add(auxe);
            

        }

     //   System.out.println("Probar");

    }

    public void mostrarPalabraContTodo() {

        for (int i = 0; i <corpusText.size() ; i++) {
            System.out.println(auxiliarMOstrarNumeros.get(i));
//              System.out.println("Oracion   " + i + "  "  + corpusText.get(i)+ " Cadena Numerica  " + auxiliarMOstrarNumeros.get(i));
        }

    }

    private void computeWordCounts() {

        Integer count = null;
        Collection<String> auxCollection = transformPalabra(corpusText);
        for (String token : auxCollection) {
            //    for (String token : this.corpusText) {
//            token = token.replaceAll("[^a-zA-Z]", "");
            //  if (!token.contains( token)) {
            if (wordCounts.containsKey(token)) {
                count = wordCounts.get(token);
                count = Integer.valueOf(count.intValue() + 1);
                wordCounts.put(token, count);
            } else {
                wordCounts.put(token, Integer.valueOf(1));
            }
            //  }
        }
    }

    public Collection<String> transformPalabra(Collection<String> col) {

        

        for (String token : this.corpusText) {

            String[] aux = token.split(" ");

            for (int i = 0; i < aux.length; i++) {
                word.add(aux[i]);
            }

        }

        return word;

    }

 
    public void LlenarMatrizEstado() {

        int nuevaAux = 0;
        int j = 0;
        for (int i = 0; i < wordCounts.size(); i++) {
            Iterator itTrigram = wordCounts.keySet().iterator();

            j = 0;

            while (itTrigram.hasNext()) {

                String key = (String) itTrigram.next();
               
                nuevaAux = wordCounts.get(key);
                 System.out.println(key+ "  " + nuevaAux) ;
              

             float valores = (float) nuevaAux / word.size();
//                System.out.println(valores + " ");
                EstadoMatriz[i][j] = valores;
                j++;
//                System.out.println("Clave: " + key + " -> Valor: " + wordCounts.get(key) + "Por donde va escribiendo" + i + j);
//            }

        }
 }
    }

    public void MatrizAleatoria() {
        for (int i = 0; i < AleatoriaMatriz.length; i++) {

            for (int j = 0; j < AleatoriaMatriz.length; j++) {

            }

        }

    }

    public float[][] mostrarAleatrorio() {
        
        Scanner sc= new Scanner(System.in);
        float valor = 0;
        Random rnd = new Random();
       
        
        //float[][] auxiliarMartriz = new float[wordCounts.size()][wordCounts.size()];
float[][] auxiliarMartriz = new float[tamanomatrizaleatroria][tamanomatrizaleatroria];
//        System.out.println("Primera secuencia");
        for (int i = 0; i < tamanomatrizaleatroria; i++) {

            for (int j = 0; j < tamanomatrizaleatroria; j++) {
                valor = rnd.nextFloat();
//                System.out.print("\t" + valor);
                auxiliarMartriz[i][j] = valor;

            }

        }
//          MostrarMatriz(auxiliarMartriz);

        return auxiliarMartriz;

    }

    public void Ajustarfila() {
        double numero = 0.0;
        float uno = 0, dos = 0;
        float[][] arrglo = mostrarAleatrorio();
        float aux[] = new float[arrglo.length];
        float contador = 0;
        for (int i = 0; i < arrglo.length; i++) {

            for (int j = 0; j < arrglo.length; j++) {
                aux[j] = (float) arrglo[i][j];
                contador += arrglo[i][j];

            }
            double numeromayor = aux[0];
            int position = 0;

            while (contador != 1) {

//                System.out.println(contador + "*******************************************************************");

                if (contador > 1) {

                    for (int l = 0; l < aux.length; l++) {
//          System.out.println(nombres[i] + " " + sueldos[i]);
                        if (aux[l] > numeromayor) { // 
                            numeromayor = aux[l];
                            position = l;
//                            System.out.print(l);
//                            System.out.println(numeromayor);
                        }
                    }

                    aux[position] = (float) 0.01;

                    numeromayor = 0.0;
                } else {

                    uno = 0;
                    numeromayor = aux[0];
                    System.out.println(df.format(numeromayor));
                    numeromayor = (Math.round(numeromayor * 100.0) / 100.0);
                    for (int j = 1; j < aux.length; j++) {
                        dos = (float) (Math.round(aux[j] * 100.0) / 100.0);
//                             dos= Float.parseFloat(df.format(aux[j]));
                        aux[j] = dos;
                    }
                    uno = (float) ((float) (Math.round(contador * 100.0) / 100.0) - numeromayor);
                    aux[0] = (float) (1 - ((float) (Math.round(uno * 100.0)) / 100.0));


                }

                contador = (float) 0;

                for (int j = 0; j < aux.length; j++) {
                    contador = contador + aux[j];

                }
                contador = (float) (Math.round(contador * 100.0) / 100.0);
               // System.out.print(contador + " algggggggggggggggggg7777777777777777777777777777777777");

            }

            for (int j = 0; j < arrglo.length; j++) {
                arrglo[i][j] = aux[j];

            }

        }
        
        
        matrizAjustada =arrglo;
        MostrarMatriz(matrizAjustada);

    }

    public void MostrarMatriz(double matriz[][]) {

        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[x].length; y++) {
                System.out.print( matriz[x][y]+ " ");
            }
            System.out.println("\n");

        }

    }
    
    

    
    
       public void MostrarMatriz(float matriz[][]) {

        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[x].length; y++) {
                System.out.print( matriz[x][y]+ " ");
            }
            System.out.println("\n");

        }

    }
       
        public Map<String, Integer> getWordCounts() {
        return wordCounts;
    }

    public void setWordCounts(Map<String, Integer> wordCounts) {
        this.wordCounts = wordCounts;
    }

    public ArrayList<String> getCorpusText() {
        return corpusText;
    }

    public void setCorpusText(ArrayList<String> corpusText) {
        this.corpusText = corpusText;
    }

    public Map<Integer, List<Integer>> getMatriAzignacion() {
        return matriAzignacion;
    }

    public void setMatriAzignacion(Map<Integer, List<Integer>> matriAzignacion) {
        this.matriAzignacion = matriAzignacion;
    }

    public Map<String, Integer> getMapaDeCaracteres() {
        return mapaDeCaracteres;
    }

    public void setMapaDeCaracteres(Map<String, Integer> mapaDeCaracteres) {
        this.mapaDeCaracteres = mapaDeCaracteres;
    }

    public double[][] getEstadoMatriz() {
        return EstadoMatriz;
    }

    public void setEstadoMatriz(double[][] EstadoMatriz) {
        this.EstadoMatriz = EstadoMatriz;
    }

    public ArrayList<String> getAuxiliarMOstrarNumeros() {
        return auxiliarMOstrarNumeros;
    }

    public void setAuxiliarMOstrarNumeros(ArrayList<String> auxiliarMOstrarNumeros) {
        this.auxiliarMOstrarNumeros = auxiliarMOstrarNumeros;
    }

    public double[][] getAleatoriaMatriz() {
        return AleatoriaMatriz;
    }

    public void setAleatoriaMatriz(double[][] AleatoriaMatriz) {
        this.AleatoriaMatriz = AleatoriaMatriz;
    }

    public float[][] getMatrizAjustada() {
        return matrizAjustada;
    }

    public void setMatrizAjustada(float[][] matrizAjustada) {
        this.matrizAjustada = matrizAjustada;
    }

    public DecimalFormat getDf() {
        return df;
    }

    public void setDf(DecimalFormat df) {
        this.df = df;
    }

}
