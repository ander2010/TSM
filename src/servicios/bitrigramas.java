/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author letal
 */
public class bitrigramas {
    
    private Collection<String> corpusText;
    private Map<String, Integer> bigramCounts = new HashMap<String, Integer>();
    private Map<String, Integer> trigramCounts = new HashMap<String, Integer>();
    private Map<String, Integer> wordCounts = new HashMap<String, Integer>();
    private int cantpalabras=0;

    public Collection<String> getCorpusText() {
        return corpusText;
    }
    
    

    /**
     * @return the bigramCounts
     */
    public Map<String, Integer> getBigramCounts() {
        return bigramCounts;
    }

    /**
     * @return the trigramCounts
     */
    public Map<String, Integer> getTrigramCounts() {
        return trigramCounts;
    }

    /**
     * @return the wordCounts
     */
    public Map<String, Integer> getWordCounts() {
        return wordCounts;
    }

    /**
     * @return the cantpalabras
     */
    public int getCantpalabras() {
        return cantpalabras;
    }
    
    
    
    
    
//    metodos de acciones
    
      //Compute word counts
    private void computeWordCounts() {

        Integer count = null;
        Collection<String> auxCollection = transformPalabra(corpusText);
        for (String token : auxCollection) {
            //    for (String token : this.corpusText) {
//            token = token.replaceAll("[^a-zA-Z]", "");
            //  if (!token.contains( token)) {
            cantpalabras=cantpalabras+1;
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

        Collection<String> word = new ArrayList<>();

        for (String token : this.corpusText) {

            String[] aux = token.split(" ");

            for (int i = 0; i < aux.length; i++) {
                word.add(aux[i]);
            }

        }
          System.out.println(word.size()+"dsdfdsfsdfsd");
        return word;

    }
       
       
       
       
       
        //Compute bigram counts
    public void computeBigrams() {

        String word1 = null, word2 = null, bigram = null;

        Integer count = null;
        for (String tokenizers : this.corpusText) {
            // System.out.println(tokenizers + "  Sentencia actual");
            String[] aux = tokenizers.split(" ");
            Collection<String> coleccion = new ArrayList<>();

            for (int i = 0; i < aux.length; i++) {
                coleccion.add(aux[i]);
            }

            for (String token : coleccion) {

                if (token.length() > 0) {
                    if ((word1 == null && word2 == null)) {
                        word1 = token;
                    } else {
                        word2 = token;
                        bigram = word1 + " " + word2;
                        if (word2.equals("<s>") && (word1.equals("</s>"))) {
                            word1 = word2;
                            word2 = null;
                            continue;
                        }
                        if (bigramCounts.containsKey(bigram)) {
                            count = bigramCounts.get(bigram);
                            count = Integer.valueOf(count.intValue() + 1);
                            bigramCounts.put(bigram, count);
                        } else {
                            bigramCounts.put(bigram, Integer.valueOf(1));
                        }
                        word1 = word2;
                        word2 = null;
                    }
                }
            }
        }

    }

    //Compute trigram counts
    public void computeTrigrams() {

        String word1 = null, word2 = null, word3 = null, trigram = null;

        Integer count = null;
        for (String tokenizers : this.corpusText) {
            Collection<String> coleccion = new ArrayList<>();

            String[] aux = tokenizers.split(" ");

            for (int i = 0; i < aux.length; i++) {
                coleccion.add(aux[i]);
            }

            for (String token : coleccion) {

                if (token.length() > 0) {
                    if (word1 == null && word2 == null && word3 == null) {
                        word1 = token;
                    } else if (word2 == null && word3 == null) {
                        word2 = token;
                    } else {

                        word3 = token;

                        if (word3.equals("<s>") && (word2.equals("</s>"))) {
                            word1 = word3;
                            word2 = null;
                            word3 = null;
                            continue;

                        }
                        trigram = word1 + " " + word2 + " " + word3;
                        if (trigramCounts.containsKey(trigram)) {
                            count = trigramCounts.get(trigram);
                            count = Integer.valueOf(count.intValue() + 1);
                            trigramCounts.put(trigram, count);
                        } else {
                            trigramCounts.put(trigram, Integer.valueOf(1));
                        }
                        word1 = word2;
                        word2 = word3;
                        word3 = null;
                    }
                }
            }
        }

    }

    public double bigramSeqProb(String words) {

        String word1 = null, word2 = null, bigram = null;
        double word1Count = 0, bigramCount = 0, sumOfProbablityLogs = 0;

        if (words == null || words.length() == 0) {
            return 1.0;
        }

        String[] tokens = words.toLowerCase().split(" ");
        sumOfProbablityLogs = 0;
        for (String word : tokens) {
            if (word1 == null && word2 == null) {
                word1 = word;
            } else {
                word2 = word;
                bigram = word1 + " " + word2;
                bigramCount = 0;
                if (bigramCounts.containsKey(bigram)) {
                    bigramCount = bigramCounts.get(bigram).intValue();
                }
                //Smoothing
                bigramCount += 1;
                word1Count = 0;
                if (wordCounts.containsKey(word1)) {
                    word1Count = wordCounts.get(word1).intValue();
                }
                //Smoothing
                word1Count += wordCounts.size();
                sumOfProbablityLogs += Math.log10(bigramCount / word1Count);
                word1 = word2;
                word2 = null;
            }
        }
        return Math.pow(10, sumOfProbablityLogs);
    }

    public double trigramSeqProb(String words) {

        String word1 = null, word2 = null, word3 = null, prefix = null, trigram = null;
        double prefixCount = 0, trigramCount = 0, sumOfProbablityLogs = 0;

        if (words == null || words.length() == 0) {
            return 1.0;
        }

        String[] tokens = words.toLowerCase().split(" ");
        sumOfProbablityLogs = 0;
        for (String word : tokens) {
            if (word1 == null && word2 == null && word3 == null) {
                word1 = word;
            } else if (word2 == null && word3 == null) {
                word2 = word;
            } else {
                word3 = word;
                trigram = word1 + " " + word2 + " " + word3;
                trigramCount = 0;
                if (trigramCounts.containsKey(trigram)) {
                    trigramCount = trigramCounts.get(trigram).intValue();
                }
                //Smoothing
                trigramCount += 1;
                prefixCount = 0;
                prefix = word1 + " " + word2;
                if (bigramCounts.containsKey(prefix)) {
                    prefixCount = bigramCounts.get(prefix).intValue();
                }
                //Smoothing
                prefixCount += wordCounts.size();
                sumOfProbablityLogs += Math.log10(trigramCount / prefixCount);
                word1 = word2;
                word2 = word3;
                word3 = null;
            }
        }
        return Math.pow(10, sumOfProbablityLogs);

    }
    
    
    
    
     private void predecirPlabra(String worString) {

        String[] aux = worString.split(" ");
        if (aux.length == 2) {

            int val = bigramCounts.get(worString);

        }

    }

    public String  buscarSentencia(String sorte, int value) {
        int pos=0;
        

        Iterator itBifram = bigramCounts.keySet().iterator();
        String proximaword="";
        String proximawordFraseEntera="";
        double nuevaAux=0;
        double valueKey=0.0;
        String []auxVariablecorta;
        Iterator itTrigram = trigramCounts.keySet().iterator();

        if (value == 1) {

            while (itBifram.hasNext()) {
                pos++;
                String key = (String) itBifram.next();
                auxVariablecorta=key.split(" ");
                if (sorte.equals(auxVariablecorta[0])) {
                    nuevaAux=bigramCounts.get(key);
                    if (valueKey<nuevaAux) {
                        valueKey=nuevaAux;
                        proximaword=auxVariablecorta[1];
                    }
                    
                    
                    
                }
                
                System.out.println("Clave: " + key + " -> Valor: " + bigramCounts.get(key));
            }

        }
        if (value==2) {
            
            
             while (itTrigram.hasNext()) {
                pos++;
                String key = (String) itTrigram.next();
                auxVariablecorta=key.split(" ");
                if (sorte.equals(auxVariablecorta[0])) {
                    nuevaAux=trigramCounts.get(key);
                    if (valueKey<nuevaAux) {
                        valueKey=nuevaAux;
                        proximaword=auxVariablecorta[1]+" "+ auxVariablecorta[2];
                        proximawordFraseEntera=key;
                    }
                    
                    
                    
                }
                
                System.out.println("Clave: " + key + " -> Valor: " + trigramCounts.get(key));
            }
            
        }
        
        
         System.out.println("La frase completa es: " + proximawordFraseEntera);
        return proximaword;
    }

    public bitrigramas(Collection<String> corpusText) {
        this.corpusText = corpusText;
        computeWordCounts();
        computeBigrams();
        computeTrigrams();
        
        
        
        
    }
    
    
    

    
    
       
       
    
    
}
