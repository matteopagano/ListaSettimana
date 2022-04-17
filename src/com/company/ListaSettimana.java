package com.company;

import java.util.*;

public class ListaSettimana {
    private List<Persona> listaPersone;
    private List<GiornoLista> listaGiorni;

    public ListaSettimana(List<Persona> listaPersona) {
        this.listaPersone=listaPersona;
        this.listaGiorni=new ArrayList<>();

    }

    public void calcolaLista() {
        /*for (Persona p:listaPersone){
            System.out.println(p);
        }
        */
        List<Persona> lista=new ArrayList<>();
        for (Persona p:listaPersone){
            for (int i=0;i<p.getNumeroGiorni();++i)
            lista.add(p);
        }
        /*
        for(Persona p:lista){
            System.out.print(p.getAbbreviazione()+", ");
        }
        */

            Collections.shuffle(lista);
        /*
        System.out.println("MESCOLO LA LISTA:");
        for(Persona p:lista){
            System.out.print(p.getAbbreviazione()+", ");

        }
        */
            //System.out.println();
            Queue<Persona> coda= new LinkedList<>();
            coda.addAll(lista);
            while(!coda.isEmpty()){
                Persona personaDaAddare=coda.poll();
                //System.out.println();
                //System.out.println("HO estratto: "+personaDaAddare.getAbbreviazione());
                Iterator<GiornoLista> it=listaGiorni.iterator();
                boolean aggiunto=false;
                while(!aggiunto && it.hasNext()){
                    GiornoLista g=it.next();
                    if(!g.isFull()&&isAddable(personaDaAddare,g)&&!alreadyPresent(personaDaAddare,g)){
                        g.addPersona(personaDaAddare);
                        aggiunto=true;
                    }
                }
            }
            //stampaLista();
            //System.out.println();
            if(!isCorrectLista()){
                //System.out.println("LISTA CLEARATA!");
                //System.out.println("Lista clear...");
                clearGiorni();
                //System.out.println();
                //stampaLista();
                //System.out.println("RICALCOLANDO LISTA!");
                calcolaLista();
            }else {
                System.out.println();
                //System.out.println("CORRETTA");

                stampaLista();


            }
        }




    private void clearGiorni() {
        for (GiornoLista g:listaGiorni){
            g.getListaDiPersona().clear();
        }
    }

    private boolean isCorrectLista() {
        boolean bool=true;
        for (GiornoLista g:listaGiorni){
            if(g.getListaDiPersona().size()!=g.getNumeroGiorniMAx()){
                bool=false;
            }
        }
        return bool;
    }

    private boolean alreadyPresent(Persona personaDaAddare, GiornoLista g) {
        boolean bool=false;
        for(Persona g1:g.getListaDiPersona()){
            if(g1.getAbbreviazione().equals(personaDaAddare.getAbbreviazione())) bool=true;
        }
        return bool;
    }

    public static boolean isAddable(Persona personaDaAddare, GiornoLista g) {
        boolean bool=true;
        String s=g.getName();
        for(Giorno gP:personaDaAddare.getGiorniAssenza()){
            if(gP.getGiorno().equals(s)){
                bool=false;
            }
        }
        return bool;
    }

    public void stampaLista() {
        for (GiornoLista g:listaGiorni){
            System.out.println(g.getName()+": "+g.getListaDiPersona());
        }
    }

    public void add(List<GiornoLista> l) {
        this.listaGiorni.addAll(l);
    }

    public boolean isCorrectOccorrenzePersone(){
        boolean bool=true;
        for(Persona p: listaPersone){
            if(!p.isCorrect()){
                bool=false;
            }
        }
        return bool;
    }
}
